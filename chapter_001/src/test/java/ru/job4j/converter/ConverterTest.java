package ru.job4j.converter;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test.
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ConverterTest {
    /**
     * Test rubleToEuro().
     */
    @Test
    public void whenRateOfEuroToEuroThen1() {
        Converter converter = new Converter();
        double result = converter.rubleToEuro(converter.rateOfEuro);
        assertThat(result, is(1d));
    }
    /**
     * Test rubleToDollar().
     */
    @Test
    public void whenRateOfDollarToDollarThen1() {
        Converter converter = new Converter();
        double result = converter.rubleToDollar(converter.rateOfDollar);
        assertThat(result, is(1d));
    }
}