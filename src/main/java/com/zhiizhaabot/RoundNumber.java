package com.zhiizhaabot;

public class RoundNumber {

    public static double processNumber(double input) {
        input = Math.round(input); // Округляем до целого
        double result = input + (input * 0.03); // Добавляем 3%
        return Math.round(result); // Округляем результат
    }
}