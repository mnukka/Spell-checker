package com.github.mnukka.spell_checker.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

class FileLoaderUtilTest {

    @Test
    void loadFile_fileNameInResources_loadsAllStringsFromFile() throws IOException, URISyntaxException {
        List<String> testFile = FileLoaderUtil.loadFile("test.txt");
        Assertions.assertEquals("second row", testFile.get(1));
    }
}