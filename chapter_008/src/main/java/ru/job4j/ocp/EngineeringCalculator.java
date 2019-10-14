package ru.job4j.ocp;

import ru.job4j.srp.InteractCalc;

import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EngineeringCalculator extends InteractCalc {
    private final Pattern pattern = Pattern.compile("[cosin]{3}\\d*+");

    public EngineeringCalculator() {
        super();
    }

    @Override
    protected void loudOperations() {
        super.loudOperations();
        super.getOperations().put("cos", cos());
        super.getOperations().put("sin", sin());
    }

    private BiConsumer<Double, Double> cos() {
        return (first, second) -> {
            super.setResult(Math.cos(Math.toRadians(first)));
        };
    }

    private BiConsumer<Double, Double> sin() {
        return (first, second) -> {
            super.setResult(Math.sin(Math.toRadians(first)));
        };
    }

    public static void main(String[] args) {
        InteractCalc calc = new EngineeringCalculator();
        calc.runCal();
    }

    @Override
    protected boolean calculate(String input) {
        boolean checker = checkInput(input);
        if (checker && (input.contains("cos") || input.contains("sin"))) {

            Pattern pattern = Pattern.compile("[cosin]{3}");
            Matcher matcher = pattern.matcher(input);
            matcher.find();
            int indexStart = matcher.start();
            int indexEnd = matcher.end();

            String operator = input.substring(indexStart, indexEnd).trim();
            String value = input.substring(indexEnd).trim();
            Double doubleValue = Double.valueOf(value);

            super.getOperations().get(operator).accept(doubleValue, null);
            System.out.println(super.getResult());
        } else {
            super.calculate(input);
        }
        return checker;
    }

    @Override
    protected boolean checkInput(String input) {
        boolean result = false;
        if (input.contains("cos") || input.contains("sin")) {
            Matcher matcher = pattern.matcher(input);
            result = matcher.find();
        } else {
            result = super.checkInput(input);
        }
        return result;
    }
}
