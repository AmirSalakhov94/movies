package tech.itpark.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

class ParserImplTest {

    private static File FILE;

    @BeforeAll
    static void beforeAll() {
        URL url = ParserImplTest.class.getResource("/movies45000.csv");
        FILE = new File(url.getFile());
    }

    @Test
    void testParseCSV() {
        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleParser<List<MovieDto>> movies = new SimpleParser(objectMapper);
//        List<MovieDto> parse = movies.parse(FILE);
//        assertNotNull(parse);
    }
}