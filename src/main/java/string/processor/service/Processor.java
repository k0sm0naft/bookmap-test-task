package string.processor.service;

import java.util.List;
import string.processor.model.Data;

public interface Processor {
    List<Integer> process(Data data);
}
