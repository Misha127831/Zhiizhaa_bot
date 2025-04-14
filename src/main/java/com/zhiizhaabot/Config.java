package com.zhiizhaabot;

import java.io.IOException;
import java.util.Properties;

public class Config {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(Config.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось загрузить файл конфигурации!");
        }
    }

    public static String getBotToken() {
        return properties.getProperty("telegram.bot.token");
    }

    public static String getBotUsername() {
        return properties.getProperty("telegram.bot.username");
    }
}