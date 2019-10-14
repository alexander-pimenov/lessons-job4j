package ru.job4j.calculator;
import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
public class FitTest {
    @Test
    public void maleWeight() {
        Fit fit = new Fit();
        double weight = fit.maleWeight(186);
        assertThat(weight, closeTo(98.9, 0.1));
    }
    @Test
    public void femaleWeight() {
        Fit fit = new Fit();
        double weight = fit.famaleWeight(159);
        assertThat(weight, closeTo(56.35, 0.1));
    }
}