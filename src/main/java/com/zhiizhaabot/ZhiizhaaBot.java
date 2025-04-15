package com.zhiizhaabot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class ZhiizhaaBot extends TelegramLongPollingBot {

    private final Map<Long, String> userState = new HashMap<>(); // Состояние пользователя
    private final Map<Long, List<String>> userLiquidData = new HashMap<>(); // Хранилище жидкостей

    @Override
    public String getBotUsername() {
        return Config.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return Config.getBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage() != null ? update.getMessage().getChatId() : update.getCallbackQuery().getMessage().getChatId();

        if (update.hasMessage() && update.getMessage().hasText()) {
            handleMessage(update.getMessage().getText().trim(), chatId, update.getMessage().getFrom().getFirstName());
        } else if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery().getData(), chatId, update.getCallbackQuery().getFrom().getFirstName());
        }
    }

    private void handleMessage(String messageText, Long chatId, String userName) {
        if ("calculate".equals(userState.get(chatId))) {
            if ("/exit".equals(messageText)) {
                userState.remove(chatId);
                sendTextMessage(chatId, "🚪 Режим округления выключен. Возвращаюсь в главное меню.");
                sendMenuMessage(chatId, userName);
            } else {
                handleCalculation(messageText, chatId);
            }
        } else if ("count_liquids".equals(userState.get(chatId))) {
            handleLiquidCount(messageText, chatId);
        } else if ("/start".equals(messageText)) {
            userState.remove(chatId);
            sendMenuMessage(chatId, userName);
        } else {
            String response = CommandProcessor.getCommandResponse(messageText); // Получаем ответ команды
            sendTextMessage(chatId, response);
        }
    }

    private void handleCallback(String callbackData, Long chatId, String userName) {
        switch (callbackData) {
            case "/calculate":
                userState.put(chatId, "calculate");
                sendTextMessage(chatId, "🔢 Введите число для округления.\n✏ Вы можете вводить числа, пока не выйдете командой /exit.");
                break;
            case "/count_liquids":
                userState.put(chatId, "count_liquids");
                userLiquidData.put(chatId, new ArrayList<>());
                sendTextMessage(chatId, "📊 Введите список жидкостей (одно на строку), затем напишите /done.");
                break;
            case "/list":
                String categories = CommandProcessor.getCategories(); // Получаем текст справочника
                if (categories != null && !categories.isEmpty()) {
                    sendTextMessage(chatId, categories);
                } else {
                    sendTextMessage(chatId, "❌ Справочник пуст или не загружен. Проверьте код.");
                }
                break;
            case "/start":
                userState.remove(chatId);
                sendMenuMessage(chatId, userName);
                break;
        }
    }

    private void handleLiquidCount(String messageText, Long chatId) {
        if ("/done".equals(messageText)) {
            List<String> userInput = userLiquidData.get(chatId);
            int totalLiquids = LiquidCounter.countLiquids(userInput);
            sendTextMessage(chatId, "📊 Итоговый подсчёт жидкостей: " + totalLiquids);
            userLiquidData.remove(chatId); // Очищаем данные после подсчёта
            userState.remove(chatId); // Выходим из режима
        } else {
            userLiquidData.get(chatId).add(messageText);
            sendTextMessage(chatId, "✅ Добавлено: " + messageText + "\n✏ Введите следующую жидкость или напишите /done.");
        }
    }

    private void handleCalculation(String messageText, Long chatId) {
        try {
            double number = Double.parseDouble(messageText);
            long roundedResult = RoundNumber.process(number);
            sendTextMessage(chatId, "🔢 Число после прибавления 3% и округления: " + roundedResult + "\n✏ Введите следующее число или /exit для выхода.");
        } catch (NumberFormatException e) {
            sendTextMessage(chatId, "❌ Ошибка: введите корректное число.");
        }
    }

    private void sendTextMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMenuMessage(Long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText("👋 Привет, " + userName + "! Выберите действие:");

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        rows.add(List.of(createButton("Округлить число", "/calculate"), createButton("Справочник", "/list")));
        rows.add(List.of(createButton("📊 Подсчитать жидкости", "/count_liquids")));

        markup.setKeyboard(rows);
        message.setReplyMarkup(markup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private InlineKeyboardButton createButton(String text, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text);
        button.setCallbackData(callbackData);
        return button;
    }
}