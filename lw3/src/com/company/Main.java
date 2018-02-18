package com.company;

import Calculators.Calculator;
import Calculators.ICalculator;
import Creators.*;
import Shapes.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> defineArgs(String[] args) {
        List<String> definedArgs = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            String[] strArgs = args[i].split(",");
            for (int arg = 0; arg < strArgs.length; arg++) {
                definedArgs.add(strArgs[arg]);
            }
        }

        return definedArgs;
    }

    public static void main(String[] args) {
        ICalculator calculator = new Calculator();

        File input = new File("input.txt");
        File output = new File("output.txt");

        CreatorsFactory factory = new CreatorsFactory();

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             FileWriter writer = new FileWriter(output)) {
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                String[] strArr = inputLine.split(" ");
                List<String> arguments = defineArgs(strArr);

                IShapeCreator creator = factory.create(strArr[0], arguments);
                IShape shape = creator.create();
                if (shape == null) {
                    continue;
                }

                writer.write(shape.getName() + ": \n");
                writer.write("Area: " + calculator.calculateArea(shape) + "\n");
                writer.write("Perimeter: " + calculator.calculatePerimeter(shape) + "\n" + "\n");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
}