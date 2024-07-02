package string.processor;

import java.util.List;
import string.processor.service.Processor;
import string.processor.service.Reader;
import string.processor.service.Writer;
import string.processor.model.Data;
import string.processor.service.Parser;
import string.processor.service.impl.FileReader;
import string.processor.service.impl.FileWriter;
import string.processor.service.impl.ParserImpl;
import string.processor.service.impl.ProcessorImpl;

public class Main {
    private static final Reader READER = new FileReader();
    private static final Parser PARSER = new ParserImpl();
    private static final Processor PROCESSOR  = new ProcessorImpl();
    private static final Writer WRITER  = new FileWriter();

    public static void main(String[] args) {
        List<String> input = READER.read();

        Data data = PARSER.toData(input);

        List<Integer> answers = PROCESSOR.process(data);

        String output = PARSER.toAnswer(answers);

        WRITER.write(output);
    }
}