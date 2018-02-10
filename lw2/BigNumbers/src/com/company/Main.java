package com.company;
import BigNumbers.BigNumber;

public class Main {

    public static void main(String[] args) {
	    BigNumber number1 = new BigNumber("8963459789");
        BigNumber number2 = new BigNumber("6666666666");
        BigNumber num = number1.Multiply(number2);

        System.out.println(num.toString());
    }
}