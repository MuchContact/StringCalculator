package math;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class StringCalculator {

    public StringCalculator() {
    }

    public int add(String source) {

        if (source.trim().equals(""))
            return 0;

        String delimiter = ",";
        final String starter = "//;\n";
        if (source.startsWith(starter)) {
            delimiter = ";";
            source = source.substring(starter.length());
        }
        final String newLine = "\n";
        if (source.contains(delimiter + newLine)) {
            throw new InvalidParameterException("Invalid input contains '," + newLine + "'");
        }
        source = source.replace(newLine, delimiter);

        if (hasNegatives(source, delimiter)) {
            throw new InvalidParameterException("negatives not allowed " + getNegativeString(source, delimiter));
        }
        return Arrays.stream(source.split(delimiter)).mapToInt(Integer::valueOf).sum();
    }

    private String getNegativeString(String source, String delimiter) {
        return Arrays.toString(
                Arrays.stream(source.split(delimiter)).filter(p -> p.contains("-")).toArray());
    }

    private boolean hasNegatives(String source, String delimiter) {
        return Arrays.stream(source.split(delimiter)).filter(p -> p.contains("-")).count() > 0;
    }
}
