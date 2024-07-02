package string.processor.service;

import java.util.List;
import string.processor.model.Data;

public interface Parser {
    Data toData(List<String> lines);

    String toAnswer(List<Integer> answers);
}
