package string.processor.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import string.processor.model.Data;
import string.processor.model.Tuple;
import string.processor.service.Processor;

public class ProcessorImpl implements Processor {

    public static final char CHAR_A = 'A';

    @Override
    public List<Integer> process(Data data) {
        int[] prefixA = buildPrefixArray(data.getSequence(), CHAR_A);
        int[] prefixB = buildPrefixArray(data.getSequence(), 'B');
        return data.getQueries().stream()
                   .map(query -> calculate(query, data.getSequence(), prefixA, prefixB))
                   .toList();
    }

    private int[] buildPrefixArray(String sequence, char ch) {
        int length = sequence.length();
        int[] prefix = new int[length + 1];
        IntStream.range(0, length)
                 .forEach(i -> prefix[i + 1] = prefix[i] + (sequence.charAt(i) == ch ? 1 : 0));
        return prefix;
    }

    private int calculate(Tuple query, String sequence, int[] prefixA, int[] prefixB) {
        int start = query.start();
        int end = query.end();
        int item = query.item();

        char originalChar = sequence.charAt(item);
        int firstCharCount;
        if (originalChar == CHAR_A) {
            firstCharCount = prefixA[item + 1] - prefixA[start];
        } else {
            firstCharCount = prefixB[item + 1] - prefixB[start];
        }

        int occurrenceIndex;
        if (originalChar == CHAR_A) {
            occurrenceIndex = findKthOccurrence(prefixB, start, end, firstCharCount);
        } else {
            occurrenceIndex = findKthOccurrence(prefixA, start, end, firstCharCount);
        }

        return occurrenceIndex < 0 ? -1 : occurrenceIndex - start;
    }

    private int findKthOccurrence(int[] prefix, int start, int end, int k) {
        int low = start + 1;
        int high = end + 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int count = prefix[mid] - prefix[start];
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return (low <= end + 1 && prefix[low] - prefix[start] == k) ? low : -1;
    }
}
