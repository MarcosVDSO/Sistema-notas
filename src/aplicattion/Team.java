package aplicattion;

import java.util.ArrayList;

public class Team {
    private String name;
    private Professor professor;
    private ArrayList<Student> students = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public void insertStudent(Student student) {
        this.students.add(student);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < students.size(); i++) {
            sb.append(i+1 + ". ");
            sb.append(students.get(i));
            sb.append("\n");
        }

        return "Turma: " + name + "\n" +
                "professor: " + professor.toString() + "\n" +
                "Alunos: " + "\n" + sb

                ;
    }


}
