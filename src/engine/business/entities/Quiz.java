package engine.business.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Quiz {
    private static long nextId = 1;
    private Long id;
    private String title;
    private String text;
    private String[] options;

    @JsonIgnore
    private int answer;

    public Quiz() {
    }

    public Quiz(Long id, String title, String text, String[] options, int answer) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public static long getNextId() {
        return nextId++;
    }
}
