package test.service;

import java.util.List;
import test.model.Data;

public interface Parser {
    Data toData(List<String> lines);

    String toAnswer(List<Integer> answers);
}
