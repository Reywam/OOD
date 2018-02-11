package com.company;

import BigNumbers.BigNumber;
import Helpers.ArgumentsParser;
import Helpers.OperationData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
    static BigNumber executeOperation(BigNumber firstArg, BigNumber secondArg, String operation) {
        BigNumber result = null;
        switch (operation) {
            case "+":
                result = firstArg.Add(secondArg);
                break;
            case "-":
                result = firstArg.Sub(secondArg);
                break;
            case "*":
                result = firstArg.Multiply(secondArg);
                break;
            case "/":
                result = firstArg.Divide(secondArg);
                break;
            default:
                throw new IllegalArgumentException("unknown operation");
        }
        return result;
    }

    public static void main(String[] args) {
        File input = new File("input.txt");
        File output = new File("output.txt");

        ArgumentsParser parser = new ArgumentsParser();

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             FileWriter writer = new FileWriter(output)) {

            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                OperationData data = parser.parseString(inputLine);
                BigNumber result = executeOperation(data.getFirstArg(), data.getSecondArg(), data.getOperation());
                String resultString = inputLine + " = " + result.toString() + '\n';
                writer.write(resultString);
            }

        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}