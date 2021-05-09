package tech.itpark.service;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tech.itpark.dto.CsvParserData;
import tech.itpark.service.parser.OpenCsvParser;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class OpenCsvParserTest {

    private static File FILE;

    @BeforeAll
    static void beforeAll() {
        URL url = OpenCsvParserTest.class.getResource("/movies45000.csv");
        FILE = new File(url.getFile());
    }

    @Test
    void testParseCSV() {
        OpenCsvParser csvParser = new OpenCsvParser();
        CsvParserData parse = csvParser.parse(FILE);
        assertNotNull(parse);
    }
}