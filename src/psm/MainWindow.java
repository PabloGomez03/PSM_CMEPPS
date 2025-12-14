/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package psm;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    
    private GameEngine engine;
    private WordProcessor currentProcessor;
    private int wrongAttempts;
    
    
    private JLabel lblStatus;
    private JLabel lblWord;
    private JLabel lblScore;
    private HangmanPanel drawingPanel;
    private JTextField txtLetter;
    private JButton btnGuess;

    public MainWindow() {
        engine = new GameEngine();
        setupUI();
        startRound();
    }

    private void setupUI() {
        setTitle("Juago del Ahorcado");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        
        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        lblScore = new JLabel(engine.getScoreText(), SwingConstants.CENTER);
        lblScore.setFont(new Font("Arial", Font.BOLD, 18));
        lblStatus = new JLabel("Welcome", SwingConstants.CENTER);
        infoPanel.add(lblScore);
        infoPanel.add(lblStatus);
        add(infoPanel, BorderLayout.NORTH);

        
        JPanel centerPanel = new JPanel(new BorderLayout());
        drawingPanel = new HangmanPanel();
        drawingPanel.setBackground(Color.WHITE);
        centerPanel.add(drawingPanel, BorderLayout.CENTER);
        
        lblWord = new JLabel("...", SwingConstants.CENTER);
        lblWord.setFont(new Font("Monospaced", Font.BOLD, 32));
        lblWord.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        centerPanel.add(lblWord, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        
        JPanel southPanel = new JPanel();
        txtLetter = new JTextField(4);
        txtLetter.setFont(new Font("Arial", Font.PLAIN, 20));
        btnGuess = new JButton("Guess");
        
        
        btnGuess.addActionListener(e -> processAttempt());
        txtLetter.addActionListener(e -> processAttempt()); 

        southPanel.add(new JLabel("Introduce la palabra:"));
        southPanel.add(txtLetter);
        southPanel.add(btnGuess);
        add(southPanel, BorderLayout.SOUTH);
    }

    private void startRound() {
        if (engine.isSeriesOver()) {
            JOptionPane.showMessageDialog(this, "GAME OVER!\nGanador: " + engine.getSeriesWinner().getName());
            System.exit(0);
        }

        wrongAttempts = 0;
        drawingPanel.setErrors(0);
        lblScore.setText(engine.getScoreText());
        
        String challenger = engine.getChallenger().getName();
        String guesser = engine.getGuesser().getName();
        lblStatus.setText("Turno: " + guesser + " está adivinando.");

        
        JPasswordField pf = new JPasswordField();
        int option = JOptionPane.showConfirmDialog(this, pf, 
                challenger + ", introduce la palabra a adivinar:", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option != JOptionPane.OK_OPTION) System.exit(0);

        String word = new String(pf.getPassword()).trim();
        if (word.isEmpty() || !word.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this, "Palabra invalida. Solo letras.");
            startRound(); 
            return;
        }

        currentProcessor = new WordProcessor(word);
        lblWord.setText(currentProcessor.getMaskedWord());
    }

    private void processAttempt() {
        String text = txtLetter.getText();
        if (text.isEmpty()) return;
        
        char letter = text.charAt(0);
        
        if(text.length() > 1 || !Character.isLetter(letter)){
            
            JOptionPane.showMessageDialog(this, "Por favor, introduce una letra válida (A-Z)");
            txtLetter.setText("");
            txtLetter.requestFocus();
            return;
            
        }
        
        txtLetter.setText("");
        txtLetter.requestFocus();

        boolean hit = currentProcessor.guessLetter(letter);

        if (!hit) {
            wrongAttempts++;
            drawingPanel.setErrors(wrongAttempts);
        }

        lblWord.setText(currentProcessor.getMaskedWord());

        checkRoundEnd();
    }

    private void checkRoundEnd() {
        if (currentProcessor.isComplete()) {
            JOptionPane.showMessageDialog(this, "CORRECTO! Adivinaste la palabra.");
            engine.registerGuesserWin();
            endTurn();
        } else if (wrongAttempts >= 6) {
            JOptionPane.showMessageDialog(this, "AHORCADO!\nLa palabra era: " + currentProcessor.getActualWord());
            engine.registerChallengerWin();
            endTurn();
        }
    }

    private void endTurn() {
        engine.switchTurns();
        startRound();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}