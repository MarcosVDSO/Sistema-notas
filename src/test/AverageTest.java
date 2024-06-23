package test;
import aplicattion.Student;
import aplicattion.Professor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class AverageTest {
    private static DecimalFormat df = new DecimalFormat("#.0");
    @Test
    public void CalculateAverageTest() {
        Professor professor = new Professor("Cleiton");
        Student student = new Student("Marcos", 21);
        ArrayList<Float> scores = new ArrayList<>();
        scores.add(7.5f);
        scores.add(8.5f);
        scores.add(7.5f);
        professor.assignScore(student, scores);
        professor.calculateAverage(student);
        String result = df.format(student.getAverage());
        Assertions.assertEquals("7,8", result);

    }

    @Test
    public void recalculateAverageTest() {
        Professor professor = new Professor("Cleiton");
        Student student = new Student("Marcos", 21);
        ArrayList<Float> scores = new ArrayList<>();
        scores.add(5.0f);
        scores.add(7.5f);
        scores.add(7.5f);
        professor.assignScore(student, scores);
        professor.recalculateAverage(student, 9f);
        String result = df.format(student.getAverage());
        Assertions.assertEquals("8,0", result);
    }

    @Test
    public void ckecksApprovalTeste() {
        Professor professor = new Professor("Cleiton");
        Student student = new Student("Marcos", 21);
        ArrayList<Float> scores = new ArrayList<>();
        scores.add(7.5f);
        scores.add(4f);
        scores.add(7.5f);
        professor.assignScore(student, scores);
        professor.calculateAverage(student);
        Boolean result = professor.ckecksApproval(student.getAverage());
        Assertions.assertEquals(false, result);
    }
}