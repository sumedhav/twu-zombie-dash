package com.zombiedash.app.model;

public class Option {
    private int id, question_id;
    private String text;
    private boolean isCorrect;

    public Option(int id, int question_id, String text, String correct) {
        this.id = id;
        this.question_id = question_id;
        this.text = text;
        this.isCorrect = correct.equalsIgnoreCase("true") ? true : false;
    }
}
