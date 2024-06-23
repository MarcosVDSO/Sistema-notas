package aplicattion;

import java.util.ArrayList;
import java.util.Collections;

public class Professor {
    private String name;
    private static final float APPROVINGAVERAGE = 7f;

    public Professor(String name) {
        this.name = name;

    }

    public void assignScore(Student student, ArrayList<Float> scores) {
        student.setScores(scores);
    }

    public void calculateAverage(Student student) {
        Float sumScores = 0f;
        for (Float score: student.getScores() ) {
            sumScores += score;
        }

        Float average = sumScores/student.getScores().size();

        student.setAverage(average);
        student.setAprovation(ckecksApproval(average));

    }

    public Boolean ckecksApproval(Float average) {
        if(average >= APPROVINGAVERAGE) {
            return true;
        }
        return false;
    }

    public void recalculateAverage(Student student, Float newScore) {
        Collections.sort(student.getScores());
        Float minValue = student.getScores().get(0);
        if(newScore > minValue) {
            student.getScores().set(0, newScore);
            calculateAverage(student);
        }

    }



    @Override
    public String toString() {
        return name;
    }
}
