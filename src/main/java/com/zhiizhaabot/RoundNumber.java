package com.zhiizhaabot;

public class RoundNumber {
    public static long process(double number) {
        double increased = number * 1.03; // Прибавляем 3%
        return Math.round(increased); // Округляем
    }
}