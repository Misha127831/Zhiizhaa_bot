package com.zhiizhaabot;

import java.util.List;

public class LiquidCounter {
    public static int countLiquids(List<String> lines) {
        int total = 0;

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                continue; // Пропускаем пустые строки
            }

            String[] words = line.split("\\s+"); // Разбиваем строку на слова
            boolean hasNumber = false;

            for (String word : words) {
                try {
                    int number = Integer.parseInt(word);
                    total += number; // Если нашли число — прибавляем
                    hasNumber = true;
                } catch (NumberFormatException ignored) {
                    // Игнорируем ошибки парсинга
                }
            }

            if (!hasNumber) {
                total += 1; // Если числа нет, но есть текст — добавляем 1
            }
        }

        return total;
    }
}