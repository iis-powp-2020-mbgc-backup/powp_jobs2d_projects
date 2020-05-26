package edu.kis.powp.jobs2d.command.analyzer;

import java.util.HashMap;

/**
 * This class was created to store and update statistics double data, in future it could be template class for any type.
 */
public class Statistics {
    private HashMap<String, Double> data;

    public Statistics() {
        data = new HashMap<>();
    }

    /**
     * Tries to obtain value stored under provided key
     * @param key key, it has to be present in underlying collection, otherwise method will throw RuntimeException
     * @return a demanded value
     */
    public Double getData(String key) {
        if (!data.containsKey(key)) throw new RuntimeException("Cannot return record, since provided key not exists!");
        return data.get(key);
    }

    /**
     * This method adds record to underlying collection of statistics data.
     * @param key key, it has to be not present in underlying collection, otherwise method will throw RuntimeException
     * @param value value to put
     */
    public void addRecord(String key, double value) {
        if (data.containsKey(key)) throw new RuntimeException("Cannot add record, since provided key exists!");
        data.put(key, value);
    }

    /**
     * This method updates record in underlying collection of statistics data.
     * @param key key, it has to be present in underlying collection, otherwise method will throw RuntimeException
     * @param value updated value.
     */
    public void updateRecord(String key, double value) {
        if (!data.containsKey(key)) throw new RuntimeException("Cannot update record, since provided key not exists!");
        data.replace(key, value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        data.forEach((s, aDouble) -> builder.append('[').append(s).append(']').append(" = ").append(aDouble).append('\n'));
        return builder.toString();
    }
}
