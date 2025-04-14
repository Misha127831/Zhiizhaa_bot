package com.zhiizhaabot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ZhiizhaaBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if ("/start".equals(messageText)) {
                sendTextMessage(chatId, "Привет! Я Zhiizhaa Bot. Выберите действие:\n" +
                        "1️⃣ /round_number - Округлить число и добавить 3%");
            } else if ("/round_number".equals(messageText)) {
                sendTextMessage(chatId, "Отправьте мне число, и я его обработаю!");
            } else if (messageText.matches("\\d+(\\.\\d+)?")) {
                double input = Double.parseDouble(messageText);
                double result = RoundNumber.processNumber(input);
                sendTextMessage(chatId, "Результат: " + result);
            } else {
                sendTextMessage(chatId, "Неизвестная команда или некорректное число. Введите /start для меню.");
            }
        }
    }

    @Override
    public String getBotUsername() {
        return Config.getBotUsername(); // Имя из конфигурации
    }

    @Override
    public String getBotToken() {
        return Config.getBotToken(); // Токен из конфигурации
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
}