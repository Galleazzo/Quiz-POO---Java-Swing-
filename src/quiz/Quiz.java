package quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quiz extends JFrame {
    private JLabel questionLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private ButtonGroup buttonGroup;
    private JButton nextButton;
    private JButton restartButton;
    private int currentQuestionIndex;
    private int score;
    private List<Question> questions;

    public Quiz() {
        // Inicialização das perguntas
        questions = new ArrayList<>();
        questions.add(new Question("O que é um objeto em Programação Orientada a Objetos?",
                "Uma instância de uma classe",
                "Um tipo de dado primitivo",
                "Uma estrutura de controle",
                "Um operador"));
        questions.add(new Question("O que é herança em Programação Orientada a Objetos?",
                "Um mecanismo que permite a criação de novas classes a partir de classes existentes",
                "Uma forma de armazenar dados",
                "Um processo de otimização de código",
                "Um tipo de operador"));
        questions.add(new Question("O que é encapsulamento em Programação Orientada a Objetos?",
                "Ocultar detalhes internos de um objeto e expor apenas a interface",
                "Transformar um objeto em uma instância de classe",
                "Atribuir um valor padrão a um objeto",
                "Armazenar dados em uma variável"));
        questions.add(new Question("O que é polimorfismo em Programação Orientada a Objetos?",
                "A capacidade de um objeto ser referenciado por várias classes base",
                "Atribuir múltiplos valores a uma variável",
                "Acessar múltiplos objetos ao mesmo tempo",
                "Definir múltiplas classes em um arquivo"));
        questions.add(new Question("O que é um construtor em Programação Orientada a Objetos?",
                "Um método especial para inicializar objetos",
                "Uma classe que herda de outra classe",
                "Um tipo de variável",
                "Uma forma de encapsulamento"));

        // Configurações da janela
        setTitle("Quiz de Programação Orientada a Objetos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(8, 1));

        // Componentes da janela
        questionLabel = new JLabel("Pergunta");
        option1 = new JRadioButton("Opção 1");
        option2 = new JRadioButton("Opção 2");
        option3 = new JRadioButton("Opção 3");
        option4 = new JRadioButton("Opção 4");
        buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);
        nextButton = new JButton("Próxima");
        restartButton = new JButton("Reiniciar");

        // Adicionando componentes à janela
        add(questionLabel);
        add(option1);
        add(option2);
        add(option3);
        add(option4);
        add(nextButton);

        // Configurando ação do botão Próxima
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    showQuestion();
                } else {
                    showScore();
                }
            }
        });

        // Configurando ação do botão Reiniciar
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartQuiz();
            }
        });
    }

    // Exibe a pergunta atual
    private void showQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionLabel.setText(currentQuestion.getQuestion());
        option1.setText(currentQuestion.getOptions().get(0));
        option2.setText(currentQuestion.getOptions().get(1));
        option3.setText(currentQuestion.getOptions().get(2));
        option4.setText(currentQuestion.getOptions().get(3));
        buttonGroup.clearSelection();
    }

    // Verifica se a resposta selecionada está correta
    private void checkAnswer() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        int selectedOption = -1;

        if (option1.isSelected()) {
            selectedOption = 0;
        } else if (option2.isSelected()) {
            selectedOption = 1;
        } else if (option3.isSelected()) {
            selectedOption = 2;
        } else if (option4.isSelected()) {
            selectedOption = 3;
        }

        if (selectedOption == currentQuestion.getCorrectAnswer()) {
            score++;
        }
    }

    // Exibe a pontuação final e o botão de reiniciar
    private void showScore() {
        questionLabel.setText("Pontuação final: " + score + "/" + questions.size());
        option1.setVisible(false);
        option2.setVisible(false);
        option3.setVisible(false);
        option4.setVisible(false);
        nextButton.setEnabled(false);
        restartButton.setVisible(true);
        add(restartButton);
    }

    // Reinicia o quiz
    private void restartQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        option1.setVisible(true);
        option2.setVisible(true);
        option3.setVisible(true);
        option4.setVisible(true);
        nextButton.setEnabled(true);
        restartButton.setVisible(false);
        remove(restartButton);
        showQuestion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Quiz().setVisible(true);
            }
        });
    }
}