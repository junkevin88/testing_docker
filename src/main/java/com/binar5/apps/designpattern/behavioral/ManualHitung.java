package com.binar5.apps.designpattern.behavioral;

public class ManualHitung {
    public static void main(String[] args) {
        System.out.println(calculateInsurance(10000.0));
    }

    public static double calculateInsurance(double income) {
        if (income <= 10000) {
            return income*0.365;
        } else if (income <= 30000) {
            return (income-10000)*0.2+35600;
        } else if (income <= 60000) {
            return (income-30000)*0.1+76500;
        } else {
            return (income-60000)*0.02+105600;
        }

    }
}
