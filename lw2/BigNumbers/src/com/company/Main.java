package com.company;
import BigNumbers.BigNumber;
public class Main {

    public static void main(String[] args) {
	    BigNumber number1 = new BigNumber("95689");
        BigNumber number2 = new BigNumber("1234567");
        BigNumber num = number1.Add(number2);
        System.out.println(num.toString());
    }
}