package com.zhiizhaabot;

import java.util.List;

public class LiquidCounter {
    public static int countLiquids(List<String> lines) {
        int total = 0;

        for (String line : lines) {
            if (line == null || line.trim().isEmpty()) {
                total += 1; // Пустая строка считается как одна жидкость
                continue;
            }

            String[] words = line.split("\\s+"); // Разбиваем строку на слова
            boolean hasNumber = false;

            for (String word : words) {
                try {
                    int number = Integer.parseInt(word); // Если найдено число
                    total += number; // Добавляем это число в общий подсчёт
                    hasNumber = true;
                } catch (NumberFormatException ignored) {
                    // Если не число, пропускаем
                }
            }

            if (!hasNumber) {
                total += 1; // Если числа нет, считаем строку как одну жидкость
            }
        }

        return total;
    }
}