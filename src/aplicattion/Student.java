package aplicattion;

import java.util.ArrayList;

public class Student {
    private String  name;
    private Integer age;
    private ArrayList<Float> Scores;
    private Float average;
    private Boolean aprovation;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ArrayList<Float> getScores() {
        return Scores;
    }

    public void setScores(ArrayList<Float> scores) {
        Scores = scores;
    }

    public Float getAverage() {
        return average;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public Boolean getAprovation() {
        return aprovation;
    }

    public void setAprovation(Boolean aprovation) {
        this.aprovation = aprovation;
    }

    @Override
    public String toString() {
        return name;
    }
}
