import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.atomic.AtomicInteger;
public class NumberGuesser implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    static final Font font1 = new Font("Anonymous Pro", Font.BOLD, 36);
    public static void main(String[] args) {
        JFrame numGuesser = new JFrame("Number Guess");
        numGuesser.setSize(500, 700);
        numGuesser.setLayout(null);
        numGuesser.setDefaultCloseOperation(numGuesser.DISPOSE_ON_CLOSE);
        numGuesser.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AssignmentII.main(new String[]{});
                numGuesser.dispose();
            }
        });
        JTextPane numTitle = new JTextPane();
        numTitle.setCaretColor(Color.WHITE);
        StyledDocument doc = numTitle.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        numTitle.setText("Guess the Number");
        numTitle.setFont(font1);
        numTitle.setBounds(0, 20, 500, 100);
        AtomicInteger num = new AtomicInteger((int) (Math.random() * 100));
        TextField numField = new TextField();
        numField.setBounds(150, 200, 200, 100);
        numField.setFont(font1);
        JTextPane userScoreString = new JTextPane();
        userScoreString.setText("SCORE: ");
        JTextPane userScore = new JTextPane();
        JTextPane numRange = new JTextPane();
        numRange.setBounds(175, 550, 150, 50);
        JButton guessButton = getjButton(numField, num, userScore, numRange);
        numField.addKeyListener(new NumberGuesser() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == 10) {
                    guessButton.doClick();
                }
            }
        });
        guessButton.setBounds(200, 350, 100, 50);
        userScoreString.setBounds(100, 430, 150, 40);
        userScoreString.setFont(font1);
        userScore.setBounds(250, 430, 100, 40);
        userScore.setFont(font1);
        userScore.setText("0");
        numGuesser.add(userScoreString);
        numGuesser.add(numTitle);
        numGuesser.add(numField);
        numGuesser.add(guessButton);
        numGuesser.add(userScore);
        numGuesser.add(numRange);
        numGuesser.setVisible(true);
    }

    private static JButton getjButton(TextField numField, AtomicInteger num,
                                      JTextPane userScore, JTextPane numRange) {
        JButton guessButton = new JButton();
        guessButton.setText("GUESS!");
        guessButton.addActionListener(e -> {
            if (numField.getText().isEmpty()) {
                return;
            }
            int guess = Integer.parseInt(numField.getText());
            numField.setText("");
            if (guess == num.get()) {
                int score = Integer.parseInt(userScore.getText());
                numRange.setText("CORRECT!");
                num.set((int) (Math.random() * 100));
                score++;
                userScore.setText(String.valueOf(score));
            } else if (guess < num.get()) {
                numRange.setText("You guessed lower");
            } else {
                numRange.setText("You guessed higher");
            }
        });
        guessButton.setBounds(100, 500, 100, 100);
        return guessButton;
    }
}
