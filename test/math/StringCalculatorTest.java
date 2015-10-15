package math;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StringCalculatorTest {

    private final StringCalculator calc = new StringCalculator();
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void should_add_empty_string() throws Exception {
        assertThat(calc.add(""), is(0));
    }

    @Test
    public void should_add_string_with_one_number() throws Exception {
        assertThat(calc.add("3"), is(3));
    }

    @Test
    public void should_get_12_when_add_string_with_two_nums_4_8() throws Exception {
        assertThat(calc.add("4,8"), is(12));
    }

    @Test
    public void should_get_5_when_add_string_with_two_nums_2_3() throws Exception {
        assertThat(calc.add("2,3"), is(5));
    }

    @Test
    public void should_get_6_when_add_three_nums_1_2_3() throws Exception {
        assertThat(calc.add("1,2,3"), is(6));
    }

    @Test
    public void should_handle_multi_lines() throws Exception {
        assertThat(calc.add("1\n2,3"), is(6));
    }

    @Test(expected = InvalidParameterException.class)
    public void should_give_exception_when_error_multi_lines() throws Exception {
        calc.add("1,\n2,3");
    }

    @Test
    public void should_use_another_delimiter() throws Exception {
        assertThat(calc.add("//;\n2;3"), is(5));
        assertThat(calc.add("//;\n6;0"), is(6));
    }

    @Test
    public void should_check_negative_1() throws Exception {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("negatives not allowed [-1]");
        calc.add("-1");
    }

    @Test
    public void should_check_negative_2() throws Exception {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("negatives not allowed [-2]");
        calc.add("-2");
    }

    @Test
    public void should_check_multi_negatives() throws Exception {
        exception.expect(InvalidParameterException.class);
        exception.expectMessage("negatives not allowed [-2, -1]");
        calc.add("3,-2,-1");
    }
}
