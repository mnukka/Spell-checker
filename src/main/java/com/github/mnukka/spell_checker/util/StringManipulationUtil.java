package com.github.mnukka.spell_checker.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * String manipulation util.
 *
 * @author Miko Nukka
 */
final public class StringManipulationUtil {
    /**
     * Process list with [^\sa-zA-Z�'] regex.
     *
     * @param listToManipulate the list to manipulate
     * @return processed string list
     */
    public static List<String> processListWithAzRegex(List<String> listToManipulate) {
        return listToManipulate.stream()
                .map(p -> processStringWithAzRegex(p).collect(Collectors.joining(" ")))
                .collect(Collectors.toList());
    }

    /**
     * Process string with [^\sa-zA-Z�'] regex.
     *
     * @param input the input
     * @return the stream
     */
    public static Stream<String> processStringWithAzRegex(String input) {
        return Arrays.stream(
                input.replaceAll("[^\\sa-zA-Z�']", "")
                        .replaceAll("^\\s+", "")
                        .split("\\s"));
    }
}
