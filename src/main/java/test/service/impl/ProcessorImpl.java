package test.service.impl;

import java.util.List;
import java.util.stream.IntStream;
import test.model.Data;
import test.model.Tuple;
import test.service.Processor;

public class ProcessorImpl implements Processor {
    @Override
    public List<Integer> process(Data data) {
        return data.getQueries().stream()
                   .map(q -> calculate(q, data.getSequence()))
                   .toList();
    }

    private int calculate(Tuple tuple, String sequence) {
        char ch = sequence.charAt(tuple.item());
        int firstCharCount = countCharInSubstring(sequence, ch, tuple.start(), tuple.item());
        char switchedCh = ch == 'A' ? 'B' : 'A';
        int indexOfOccurrence =
                getIndexOfOccurrence(sequence, switchedCh, tuple.start(), tuple.end(),
                        firstCharCount);
        return indexOfOccurrence < 0 ? -1 : indexOfOccurrence + 1 - tuple.start();
    }

    public int countCharInSubstring(String sequence, char ch, int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > sequence.length() || startIndex > endIndex) {
            throw new IllegalArgumentException("Invalid start or end index");
        }
        return (int) IntStream.range(startIndex, endIndex + 1)
                              .filter(i -> sequence.charAt(i) == ch)
                              .count();
    }

    public int getIndexOfOccurrence(String sequence, char ch, int startIndex, int endIndex,
            int occurrence) {
        return IntStream.range(startIndex, endIndex + 1)
                        .filter(i -> sequence.charAt(i) == ch)
                        .skip(occurrence - 1)
                        .findFirst()
                        .orElse(-1);
    }
}
