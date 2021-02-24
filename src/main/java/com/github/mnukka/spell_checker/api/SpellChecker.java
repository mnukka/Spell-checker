package com.github.mnukka.spell_checker.api;

import com.github.mnukka.memory_bloomer.api.IMemoryCache;
import com.github.mnukka.memory_bloomer.api.MemoryCacheFactory;
import com.github.mnukka.spell_checker.util.FileLoaderUtil;
import com.github.mnukka.spell_checker.util.StringManipulationUtil;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Spell checker.
 *
 * Provides methods to verify correctness of words against built-in wordlist.
 * Under the hood this library implements Bloom filter, see <a href="https://github.com/mnukka/memory-bloomer">memory-bloomer</a>
 *
 * @author Miko Nukka
 */
public class SpellChecker {
    private IMemoryCache<String> dict;

    /**
     * Instantiates a new Spell checker.
     */
    public SpellChecker() {
        try {
            dict = createDictionary();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks whether word is present in the wordlist
     *
     * @param word to check against the wordlist
     * @return false if word is not present in wordlist against which the check is done
     */
    boolean isWordOk(String word) {
        return dict.isKeyPresent(word.toLowerCase());
    }

    /**
     * Goes through each individual word in the sentence and matches it against with isWordOk method
     * All faulty words are returned within a list
     *
     * @param sentence the sentence which user wants to check
     * @return the list of words which were not present in the wordlist
     */
    List<String> verifySentence(String sentence) {
        return StringManipulationUtil.processStringWithAzRegex(sentence).filter(p -> !isWordOk(p)).collect(Collectors.toList());
    }

    private static IMemoryCache createDictionary() throws IOException, URISyntaxException {
        List<String> wordList = FileLoaderUtil.loadFile("wordlist.txt");
        return MemoryCacheFactory.createImmutableCache(wordList);
    }

}
