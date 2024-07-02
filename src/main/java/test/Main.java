package test;

import java.util.List;
import test.model.Data;
import test.service.Parser;
import test.service.Processor;
import test.service.Reader;
import test.service.Writer;
import test.service.impl.FileReader;
import test.service.impl.FileWriter;
import test.service.impl.ParserImpl;
import test.service.impl.ProcessorImpl;

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