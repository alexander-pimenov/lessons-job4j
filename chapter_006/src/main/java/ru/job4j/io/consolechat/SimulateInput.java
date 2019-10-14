package ru.job4j.io.consolechat;

public class SimulateInput implements Input {
    String[] arrayOfSimulateInput;
    int count = 0;

    public SimulateInput(String[] arrayOfSimulateInput) {
        this.arrayOfSimulateInput = arrayOfSimulateInput;
    }

    @Override
    public String ask() {
        String result = "";
        if (count < arrayOfSimulateInput.length) {
            result = arrayOfSimulateInput[count++];
        }
        return result;
    }
}
