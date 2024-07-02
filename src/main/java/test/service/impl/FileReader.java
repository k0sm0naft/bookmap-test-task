package test.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import test.service.Reader;

public class FileReader implements Reader {
    private final String fileName = "input.txt";

    @Override
    public List<String> read() {
        Path path = Path.of(fileName);
        try (Stream<String> stream = Files.lines(path)) {
            return stream
                    .filter(s -> !s.isBlank())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file: " + fileName, e);
        }
    }
}
