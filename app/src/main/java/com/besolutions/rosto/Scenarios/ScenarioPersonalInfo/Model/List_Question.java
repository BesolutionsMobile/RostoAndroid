package com.besolutions.rosto.Scenarios.ScenarioPersonalInfo.Model;

public class List_Question {

    String title, description;
    int question_id;

    public List_Question(String title, String description, int question_id) {
        this.title = title;
        this.description = description;
        this.question_id = question_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }
}
