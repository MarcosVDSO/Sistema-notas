
import aplicattion.Student;
import aplicattion.Team;
import aplicattion.Professor;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static DecimalFormat df = new DecimalFormat("#.0");

    public static void main(String[] args) {
        System.out.println("Bem vindo ao sistema de notas");

        Team team = createTeam();
        Professor professor = createProfessor();
        team.setProfessor(professor);
        System.out.println("Seja bem-vindo professor " + professor);

        int numberStudents = getNumberOfStudents();
        addStudentsToTeam(team, numberStudents);

        manageStudentScores(team, professor, numberStudents);

        scanner.close();
    }

    private static Team createTeam() {
        System.out.println("Insira o nome da Turma");
        String teamName = scanner.nextLine();
        return new Team(teamName);
    }

    private static Professor createProfessor() {
        System.out.println("Insira o nome do professor");
        String professorName = scanner.nextLine();
        return new Professor(professorName);
    }

    private static int getNumberOfStudents() {
        Integer numberStudents = null;
        while (numberStudents == null) {
            try {
                System.out.println("Quantos alunos tem em sua turma? (Máximo 5)");
                numberStudents = scanner.nextInt();
                if (numberStudents < 1 || numberStudents > 5) {
                    throw new IllegalArgumentException("O número de alunos deve ser entre 1 e 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                numberStudents = null;
            }
        }
        scanner.nextLine();
        return numberStudents;
    }

    private static void addStudentsToTeam(Team team, int numberStudents) {
        for (int i = 1; i <= numberStudents; i++) {
            System.out.println("Insira o nome do aluno " + i);
            String studentName = scanner.nextLine();

            int studentAge = getStudentAge(i);

            Student student = new Student(studentName, studentAge);
            team.insertStudent(student);
        }
    }

    private static int getStudentAge(int studentNumber) {
        Integer studentAge = null;
        while (studentAge == null) {
            try {
                System.out.println("Insira a idade do aluno " + studentNumber);
                studentAge = scanner.nextInt();
                if (studentAge <= 0) {
                    throw new IllegalArgumentException("A idade deve ser um número positivo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido para a idade.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                studentAge = null;
            }
        }
        scanner.nextLine();
        return studentAge;
    }

    private static void manageStudentScores(Team team, Professor professor, int numberStudents) {
        boolean continueChoosing = true;
        while (continueChoosing) {
            System.out.println(team);
            int chosenPosition = getChosenPosition(numberStudents);

            Student chosenStudent = team.getStudents().get(chosenPosition - 1);
            ArrayList<Float> scores = getStudentScores();
            professor.assignScore(chosenStudent, scores);
            professor.calculateAverage(chosenStudent);

            if (chosenStudent.getAprovation()) {
                System.out.println("O aluno " + chosenStudent.getName() + " foi aprovado com média " + df.format(chosenStudent.getAverage()));
            } else {
                handleReposition(professor, chosenStudent);
            }

            continueChoosing = shouldContinue();
        }
    }

    private static int getChosenPosition(int numberStudents) {
        Integer chosenPosition = null;
        while (chosenPosition == null) {
            try {
                System.out.println("Escolha o aluno que deseja acessar:");
                chosenPosition = scanner.nextInt();
                if (chosenPosition < 1 || chosenPosition > numberStudents) {
                    throw new IllegalArgumentException("Escolha uma posição válida entre 1 e " + numberStudents);
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                chosenPosition = null;
            }
        }
        return chosenPosition;
    }

    private static ArrayList<Float> getStudentScores() {
        ArrayList<Float> scores = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Float score = null;
            while (score == null) {
                try {
                    System.out.println("Insira a nota " + i);
                    score = scanner.nextFloat();
                    if (score < 0 || score > 10) {
                        throw new IllegalArgumentException("A nota deve ser entre 0 e 10.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, insira um valor numérico válido para a nota.");
                    scanner.next(); // Limpa o scanner
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    score = null;
                }
            }
            scores.add(score);
        }
        return scores;
    }

    private static void handleReposition(Professor professor, Student student) {
        System.out.println("O aluno " + student.getName() + " obteve média " + df.format(student.getAverage()) + " e terá que fazer reposição");

        Float repositionScore = null;
        while (repositionScore == null) {
            try {
                System.out.println("Insira a nota obtida na prova de reposição");
                repositionScore = scanner.nextFloat();
                if (repositionScore < 0 || repositionScore > 10) {
                    throw new IllegalArgumentException("A nota deve ser entre 0 e 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um valor numérico válido para a nota de reposição.");
                scanner.next(); // Limpa o scanner
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                repositionScore = null;
            }
        }

        professor.recalculateAverage(student, repositionScore);
        if (student.getAprovation()) {
            System.out.println("O aluno " + student.getName() + " foi aprovado após a recuperação com média " + df.format(student.getAverage()));
        } else {
            System.out.println("O aluno " + student.getName() + " continuou reprovado, com média " + df.format(student.getAverage()) + ", mesmo após a reposição");
        }
    }

    private static boolean shouldContinue() {
        Integer response = null;
        while (response == null) {
            try {
                System.out.println("Deseja acessar outro aluno? (1 para sim, 2 para não)");
                response = scanner.nextInt();
                if (response != 1 && response != 2) {
                    throw new IllegalArgumentException("Escolha uma opção válida: 1 para sim, 2 para não.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, insira um valor numérico válido.");
                scanner.next();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                response = null;
            }
        }
        return response == 1;
    }
}
