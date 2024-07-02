package test.model;

import java.util.List;

public final class Data {
    private int length;
    private int numberOfQueries;
    private String sequence;
    private List<Tuple> queries;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getNumberOfQueries() {
        return numberOfQueries;
    }

    public void setNumberOfQueries(int numberOfQueries) {
        this.numberOfQueries = numberOfQueries;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public List<Tuple> getQueries() {
        return queries;
    }

    public void setQueries(List<Tuple> queries) {
        this.queries = queries;
    }
}
