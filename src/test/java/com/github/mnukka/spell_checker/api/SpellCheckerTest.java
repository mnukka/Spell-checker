package com.github.mnukka.spell_checker.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import com.github.mnukka.spell_checker.util.FileLoaderUtil;
import com.github.mnukka.spell_checker.util.StringManipulationUtil;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class SpellCheckerTest {

    @Test
    void isWordOk_correctWord_returnsTrue() {
        SpellChecker spellChecker = new SpellChecker();
        Assertions.assertTrue(spellChecker.isWordOk("speed"));
    }

    @Test
    void isWordOk_read10kSentencesFromFile_findsFaultyWords() throws IOException, URISyntaxException {
        SpellChecker spellChecker = new SpellChecker();
        List<String> sentencesToSpellCheck = loadFileAndSplitWordsByWhitespace();
        sentencesToSpellCheck = sentencesToSpellCheck.stream().flatMap(p ->
                Arrays.stream(p.split(" "))
                .filter(w -> w.length() > 1 && !spellChecker.isWordOk(w.toLowerCase()))
                ).collect(Collectors.toList());
        List<String> expectedFaultyWords = FileLoaderUtil.loadFile("faulty_words.txt");
        Assertions.assertTrue(expectedFaultyWords.containsAll(sentencesToSpellCheck));
    }

    @Test
    void isSentenceOk_correctSentence_returnsZeroElements() {
        SpellChecker spellChecker = new SpellChecker();
        List<String> incorrectWords = spellChecker.verifySentence("This is ok sentence");
        Assertions.assertEquals(incorrectWords.size(), 0);
    }

    @Test
    void isSentenceOk_invalidSentence_returnsTwoElements() {
        SpellChecker spellChecker = new SpellChecker();
        List<String> incorrectWords = spellChecker.verifySentence("Thiss not okk sentence");
        Assertions.assertEquals(incorrectWords.size(), 2);
    }

    private static List<String> loadFileAndSplitWordsByWhitespace() throws IOException, URISyntaxException {
        List<String> sentenceList = FileLoaderUtil.loadFile("eng_sentences.txt");
        return StringManipulationUtil.processListWithAzRegex(sentenceList);
    }
}