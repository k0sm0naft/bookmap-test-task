package test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import test.model.Data;
import test.model.Tuple;
import test.service.Parser;

public class ParserImpl implements Parser {

    private static final int SIZE_OF_BASE_INFO = 2;
    private static final int LENGTH_INDEX = 0;
    private static final int QUEUES_INDEX = 1;
    private static final int SEQUENCE_INDEX = 1;
    private static final int BASE_INFO_LINES_SIZE = 2;
    private static final int BASE_INFO_INDEX = 0;
    private static final int SIZE_OF_QUERY = 3;
    private static final String REGEX = "[^A-B]";

    @Override
    public Data toData(List<String> lines) {
        Data data = new Data();
        setBaseInfo(lines, data);
        setQueries(lines, data);
        return data;
    }

    @Override
    public String toAnswer(List<Integer> answers) {
        return answers.stream()
                      .map(String::valueOf)
                      .collect(Collectors.joining(System.lineSeparator()));
    }

    private void setBaseInfo(List<String> lines, Data data) {
        String base = lines.get(BASE_INFO_INDEX);
        String sequence = lines.get(SEQUENCE_INDEX);
        data.setSequence(sequence);
        if (data.getSequence().matches(REGEX)) {
            throw new IllegalArgumentException("Sequence contains invalid characters");
        }

        String[] split = base.split(" ");
        if (split.length != SIZE_OF_BASE_INFO) {
            throw new IllegalArgumentException("Wrong number of columns in line " + base);
        }

        data.setLength(Integer.parseInt(split[LENGTH_INDEX]));
        data.setNumberOfQueries(Integer.parseInt(split[QUEUES_INDEX]));

        if (data.getSequence().length() != data.getLength()) {
            throw new IllegalArgumentException(
                    "Wrong length of sequence, expected " + data.getLength()
                            + " but was " + data.getSequence().length());
        }
    }

    private void setQueries(List<String> lines, Data data) {
        validateQueries(lines, data);
        List<Tuple> queries = new ArrayList<>(data.getNumberOfQueries());
        for (int i = SIZE_OF_BASE_INFO; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] splitedLine = line.split(" ");
            validateTuple(line, splitedLine.length);
            int start = Integer.parseInt(splitedLine[0]) - 1;
            int end = Integer.parseInt(splitedLine[1]) - 1;
            int item = start + Integer.parseInt(splitedLine[2]) - 1;
            queries.add(new Tuple(start, end, item));
        }
        data.setQueries(queries);
    }

    private static void validateTuple(String line, int length) {
        if (length != SIZE_OF_QUERY) {
            throw new IllegalArgumentException("Wrong number of columns in line " + line);
        }
    }

    private void validateQueries(List<String> lines, Data data) {
        int numberOfQueriesFromLines = lines.size() - BASE_INFO_LINES_SIZE;
        if (data.getNumberOfQueries() != numberOfQueriesFromLines) {
            throw new IllegalArgumentException(
                    "Wrong number of queues, expected " + data.getNumberOfQueries()
                            + " but was " + numberOfQueriesFromLines);
        }
    }
}
