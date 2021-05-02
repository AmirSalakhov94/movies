package tech.itpark.service.parser;

import java.io.File;
import java.io.InputStreamReader;

public interface Parser<T> {

    T parse(File file);

    T parse(InputStreamReader inputStreamReader);
}
