package string.processor.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import string.processor.model.Data;
import string.processor.model.Tuple;
import string.processor.service.Parser;

public class ParserImpl implements Parser {

    private static final int SIZE_OF_BASE_INFO = 2;
    private static final int LENGTH_INDEX = 0;
    private static final int QUEUES_INDEX = 1;
    private static final int SEQUENCE_INDEX = 1;
    private static final int BASE_INFO_INDEX = 0;

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

        String[] baseParts  = base.split(" ");

        data.setLength(Integer.parseInt(baseParts [LENGTH_INDEX]));
        data.setNumberOfQueries(Integer.parseInt(baseParts [QUEUES_INDEX]));
    }

    private void setQueries(List<String> lines, Data data) {
        List<Tuple> queries = new ArrayList<>(data.getNumberOfQueries());
        int size = lines.size();
        for (int i = SIZE_OF_BASE_INFO; i < size; i++) {
            String line = lines.get(i);
            String[] queryParts = line.split(" ");
            int start = Integer.parseInt(queryParts[0]) - 1;
            int end = Integer.parseInt(queryParts[1]) - 1;
            int item = start + Integer.parseInt(queryParts[2]) - 1;
            queries.add(new Tuple(start, end, item));
        }
        data.setQueries(queries);
    }
}
