package com.fii.laboratory_2.models;

public class Record {
    private String Category;
    private String Key;
    private String Value;

    public Record(String category, String key, String value) {
        Category = category;
        Key = key;
        Value = value;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
