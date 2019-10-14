package ru.job4j.srp;

import ru.job4j.calculator.Calculator;

import java.util.HashMap;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InteractCalc {
    //private final Pattern pattern = Pattern.compile("-?\\d*+[+\\/\\*\\-]+\\d+");
    private final Pattern pattern = Pattern.compile("(-?\\d+\\.?\\d*)?\\s*(\\S)\\s*(-?\\d+\\.?\\d*)", Pattern.CASE_INSENSITIVE);
    private Calculator calculator;
    private Scanner scanner;

    public Double getResult() {
        return this.result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    private Double result;

    public HashMap<String, BiConsumer<Double, Double>> getOperations() {
        return this.operations;
    }

    public void setOperations(HashMap<String, BiConsumer<Double, Double>> operations) {
        this.operations = operations;
    }

    private HashMap<String, BiConsumer<Double, Double>> operations;

    public InteractCalc() {
        this.calculator = new Calculator();
        this.scanner = new Scanner(System.in);
        this.operations = new HashMap<>();
        this.loudOperations();
    }

    protected void loudOperations() {
        this.operations.put("+", add());
        this.operations.put("-", subtract());
        this.operations.put("*", multiply());
        this.operations.put("/", div());
    }

    private BiConsumer<Double, Double> add() {
        return calculator::add;
    }

    private BiConsumer<Double, Double> subtract() {
        return calculator::subtract;
    }

    private BiConsumer<Double, Double> div() {
        return calculator::div;
    }

    private BiConsumer<Double, Double> multiply() {
        return calculator::multiply;
    }

    public static void main(String[] args) {
        InteractCalc calc = new InteractCalc();
        calc.runCal();
    }

    public void runCal() {
        System.out.println("Hello!This is simple calculator! Enjoy ;) Type \"Exit\" in order to exit.");
        String input = scanner.next();
        while (!"Exit".equalsIgnoreCase(input)) {
            boolean checker = calculate(input);
            if (!checker) {
                System.out.println("error");
            }
            input = scanner.next();
        }
        System.out.println("Exit.");
    }

    protected boolean calculate(String input) {
        boolean checker = checkInput(input);
        if (checker) {
            //System.out.println("ok");
            Pattern pattern = Pattern.compile("[\\+\\/\\*\\-]+");
            //Pattern pattern = Pattern.compile("(-?\\d+\\.?\\d*)?\\s*(\\S)\\s*(-?\\d+\\.?\\d*)");
            Matcher matcher = pattern.matcher(input);
            matcher.find();

            int indexS = matcher.start();
            int indexE = matcher.end();
            var operator = input.substring(indexS, indexS + 1).trim();

            if (indexS != 0) {
                var getValue = input.substring(0, indexS).trim();
                double firstValue = Double.valueOf(getValue);
                getValue = input.substring(indexE).trim();
                var secondValue = Double.valueOf(getValue);
                this.operations.get(operator).accept(firstValue, secondValue);
                this.result = calculator.getResult();
            }
            if (indexS == 0 && this.result != null) {
                var getValue = input.substring(indexE).trim();
                var value = Double.valueOf(getValue);
                this.operations.get(operator).accept(this.result, value);
                this.result = calculator.getResult();
            }
            System.out.println(this.result);
        }
        return checker;
    }

    protected boolean checkInput(String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
