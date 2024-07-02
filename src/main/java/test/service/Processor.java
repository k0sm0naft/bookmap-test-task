package test.service;

import java.util.List;
import test.model.Data;

public interface Processor {
    List<Integer> process(Data data);
}
