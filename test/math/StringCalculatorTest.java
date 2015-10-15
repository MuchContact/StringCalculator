package math;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringCalculatorTest {
    @Test
    public void should_add_handle_empty_string() throws Exception {
        StringCalculator calc = new StringCalculator("");
        assertThat(calc.result(), is(0));
    }
}
