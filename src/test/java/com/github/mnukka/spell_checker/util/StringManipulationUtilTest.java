package com.github.mnukka.spell_checker.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class StringManipulationUtilTest {

    @Test
    void processListWithRegex_withNonAlphaBetChars_returnsOnlyAlphabetChar() {
        List<String> singleItemList = Collections.singletonList("AAA B## C; D' .???");
        List<String> result = StringManipulationUtil.processListWithAzRegex(singleItemList);
        Assertions.assertEquals("AAA B C D'", result.get(0));
    }

    @Test
    void processListWithRegex_whitespaceInBeginning_returnsOnlyAlphabetChar() {
        List<String> singleItemList = Collections.singletonList("  AAA B C D �tourderie");
        List<String> result = StringManipulationUtil.processListWithAzRegex(singleItemList);
        Assertions.assertEquals("AAA B C D �tourderie", result.get(0));
    }
}