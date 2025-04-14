package com.zhiizhaabot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import com.zhiizhaabot.LiquidCounter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZhiizhaaBot extends TelegramLongPollingBot {

    private final Map<Long, String> userState = new HashMap<>(); // Запоминаем состояние пользователя

    @Override
    public String getBotUsername() {
        return Config.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return Config.getBotToken();
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        ZhiizhaaBot bot = new ZhiizhaaBot();
        botsApi.registerBot(bot);
        bot.registerBotCommands(); // Регистрируем команды
        System.out.println("✅ Бот запущен!");
    }

    public void registerBotCommands() {
        List<BotCommand> commandList = new ArrayList<>();
        commandList.add(new BotCommand("/start", "Запустить бота"));
        commandList.add(new BotCommand("/calculate", "Округлить число"));
        commandList.add(new BotCommand("/list", "Просмотреть справочник"));
        commandList.add(new BotCommand("/chaser", "Инфо о Chaser"));
        commandList.add(new BotCommand("/alchemist", "Инфо о Alchemist"));
        commandList.add(new BotCommand("/nova", "Инфо о NOVA"));
        commandList.add(new BotCommand("/deadhorse", "Инфо о Dead Horse"));
        commandList.add(new BotCommand("/fl350mini", "Инфо о FL350 Mini"));
        commandList.add(new BotCommand("/flavorlab", "Инфо о Flavorlab"));
        commandList.add(new BotCommand("/flavorlabdisposablepuff", "Инфо о Disposable Puff"));
        commandList.add(new BotCommand("/fluffypuff", "Инфо о Fluffy Puff"));
        commandList.add(new BotCommand("/octobar", "Инфо о Octobar"));
        commandList.add(new BotCommand("/wickwire", "Инфо о Wick & Wire"));
        commandList.add(new BotCommand("/lucky", "Инфо о Lucky"));
        commandList.add(new BotCommand("/vapeshot", "Инфо о Vape Shot"));
        commandList.add(new BotCommand("/punch7ml", "Инфо о Punch 7ml"));
        commandList.add(new BotCommand("/punch14ml", "Инфо о Punch 14ml"));
        commandList.add(new BotCommand("/marvellous7ml", "Инфо о Flamingo 7ml"));
        commandList.add(new BotCommand("/marvellous15ml", "Инфо о Flamingo 15ml"));
        commandList.add(new BotCommand("/steampuff", "Инфо о SteamPuff"));
        commandList.add(new BotCommand("/inbottle", "Инфо о InBottle"));

        try {
            this.execute(new SetMyCommands(commandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText().trim();
            Long chatId = update.getMessage().getChatId();

            String state = userState.getOrDefault(chatId, "menu");

            if ("/start".equals(messageText)) {
                userState.put(chatId, "menu");
                System.out.println("Команда `/start` получена. Статус: " + userState.get(chatId));

                String menuText = MenuManager.getMainMenu();
                System.out.println("Текст меню: " + menuText);

                sendTextMessage(chatId, "Привет, Миша! 👋\n" + menuText);
            } else if ("/count_liquids".equals(messageText)) {
                userState.put(chatId, "count_liquids");
                sendTextMessage(chatId, "📊 **Режим подсчета жидкостей** активирован\nВведите список жидкостей, и я их подсчитаю.");
            } else if ("count_liquids".equals(state)) {
                List<String> lines = List.of(messageText.split("\n"));
                int result = LiquidCounter.countLiquids(lines);
                sendTextMessage(chatId, "🔢 **Общее количество жидкостей: " + result + "**\nВведите новый список или используйте `/start`, чтобы выйти.");
            } else {
                String response = CommandProcessor.getCommandResponse(messageText);
                if (response != null) {
                    sendTextMessage(chatId, response);
                } else {
                    sendTextMessage(chatId, "❌ Неизвестная команда. Введите `/list` для справочника.");
                }
            }
        }
    }

    private void sendTextMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());

        // 🚀 Экранируем спецсимволы для Telegram MarkdownV2
        text = text.replace("!", "\\!")
                .replace(".", "\\.")
                .replace("-", "\\-")
                .replace("_", "\\_")
                .replace("[", "\\[")
                .replace("]", "\\]")
                .replace("(", "\\(")
                .replace(")", "\\)");

        message.setText(text);
        message.setParseMode("MarkdownV2");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}