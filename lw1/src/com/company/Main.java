package com.company;

import Calculators.Calculator;
import Calculators.ICalculator;
import Shapes.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static IShape defineShape(String name, List<String> args) {
        IShape shape = null;
        switch (name) {
            case "TRIANGLE": {
                Point vertex1 = new Point(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
                Point vertex2 = new Point(Integer.parseInt(args.get(2)), Integer.parseInt(args.get(3)));
                Point vertex3 = new Point(Integer.parseInt(args.get(4)), Integer.parseInt(args.get(5)));

                shape = new Triangle(vertex1, vertex2, vertex3);
                break;
            }
            case "RECTANGLE": {
                Point vertex1 = new Point(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)));
                Point vertex2 = new Point(Integer.parseInt(args.get(2)), Integer.parseInt(args.get(3)));
                shape = new Rectangle(vertex1, vertex2);
                break;
            }
            case "CIRCLE": {
                int R = Integer.parseInt(args.get(2));
                shape = new Circle(new Point(0, 0), R);
                break;
            }
        }

        return shape;
    }

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
        Triangle triangle = new Triangle(new Point(100, 100), new Point(200, 200), new Point(150, 150));
        Rectangle rect = new Rectangle(new Point(200, 200), new Point(300, 300));
        Circle circle = new Circle(new Point(100, 100), 50);

        ICalculator calculator = new Calculator();

        File input = new File("input.txt");
        File output = new File("output.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(input));
             FileWriter writer = new FileWriter(output)) {
            String inputLine = "";
            while ((inputLine = reader.readLine()) != null) {
                String[] strArr = inputLine.split(" ");

                IShape shape = defineShape(strArr[0], defineArgs(strArr));
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