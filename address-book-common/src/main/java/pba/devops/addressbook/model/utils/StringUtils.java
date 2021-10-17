package pba.devops.addressbook.model.utils;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringUtils {

    static final public void main(String[] args) {
        System.out.println(
            "--".concat(fixedSizeString("1234567HHHHHHHHHHHHHHH", 15)).concat("--"));
    }

    static public <DISPLAYABLE extends Object> String fixedSizeString(DISPLAYABLE displayable, Integer fixedSize) {

        final Integer minSize = fixedSize < 3 ? 3 : fixedSize;
        final String truncate = "...";

        String string =
            displayable == null
                ? ""
                : displayable.toString().length() > minSize
                    ? displayable.toString().substring(0, minSize - truncate.length()).concat(truncate)
                    : displayable.toString();
        return
            String.format("%1$-"+ minSize + "s", string);
    }

    static public Boolean isInteger(String string) {
        return
            string.matches("-?[0-9]+");
    }

    static public String nTimesCharacters(String string, Integer numberOfCharacter) {
        return IntStream.range(0, numberOfCharacter)
            .mapToObj(i -> string).collect(Collectors.joining(""));
    }
}
