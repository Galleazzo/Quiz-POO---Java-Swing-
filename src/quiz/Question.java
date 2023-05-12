package quiz;

import java.util.Arrays;
import java.util.List;

public class Question {
    private String question;
    private List<String> options;
    private int correctAnswer;

    public Question(String question, String option1, String option2, String option3, String option4) {
        this.question = question;
        this.options = Arrays.asList(option1, option2, option3, option4);
        this.correctAnswer = 0; // Índice da opção correta (começando de 0)
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}
