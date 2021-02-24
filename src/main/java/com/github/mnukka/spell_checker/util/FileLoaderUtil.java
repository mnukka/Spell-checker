package com.github.mnukka.spell_checker.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * File loader util.
 *
 * @author Miko Nukka
 */
final public class FileLoaderUtil {
    /**
     * Loads file into java List<String> and replaces all letters to lowercase
     *
     * @param filename the filename
     * @return the list
     * @throws IOException        the io exception
     * @throws URISyntaxException the uri syntax exception
     */
    public static List<String> loadFile(String filename) throws IOException, URISyntaxException {
        var path = Paths.get(Objects.requireNonNull(FileLoaderUtil.class.getClassLoader().getResource(filename)).toURI());
        try (Stream<String> lines = Files.lines(path)) {
            return lines.map(String::toLowerCase).collect(Collectors.toList());
        }
    }
}
