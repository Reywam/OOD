package com.company;
import BigNumbers.BigNumber;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    BigNumber number1 = new BigNumber("0");
        BigNumber number2 = new BigNumber("1");
        BigNumber num = number1.Sub(number2);

        System.out.println(num.toString());
    }
}