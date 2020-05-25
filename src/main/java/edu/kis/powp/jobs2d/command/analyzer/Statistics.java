package edu.kis.powp.jobs2d.command.analyzer;

import java.util.HashMap;

public class Statistics {
    private HashMap<String, Double> data;

    public Statistics() {
        data = new HashMap<>();
    }

    public Double getData(String key) {
        if (!data.containsKey(key)) throw new RuntimeException("Cannot return record, since provided key not exists!");
        return data.get(key);
    }

    public void addRecord(String key, double value) {
        if (data.containsKey(key)) throw new RuntimeException("Cannot add record, since provided key exists!");
        data.put(key, value);
    }

    public void updateRecord(String key, double value) {
        if (!data.containsKey(key)) throw new RuntimeException("Cannot update record, since provided key not exists!");
        data.replace(key, value);
    }
}
