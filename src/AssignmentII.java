import javax.swing.*;
import java.awt.*;

public class AssignmentII {
    static final Font font1 = new Font("Anonymous Pro", Font.BOLD, 30);
    public static void main(String[] args) {
        System.out.println("IN MAIN");
        JFrame assignmentWindow = new JFrame("Assignment-II");
        assignmentWindow.setSize(500, 580);
        assignmentWindow.setBounds(500, 150, 500, 580);
        assignmentWindow.setLayout(null);
        assignmentWindow.setDefaultCloseOperation(assignmentWindow.DISPOSE_ON_CLOSE);
        JButton q1Calc = new JButton("Calculator");
        q1Calc.addActionListener(e -> {
            Calculator.main(new String[] {});
            assignmentWindow.dispose();
        });
        JButton q2NumberGuess = new JButton("Number Guesser");
        q2NumberGuess.addActionListener(e -> {
            NumberGuesser.main(new String[] {});
            assignmentWindow.dispose();
        });
        JButton q4Archiver = new JButton("File Archiver");
        q4Archiver.addActionListener(e -> {
            Archiver.main(new String[] {});
            assignmentWindow.dispose();
        });
        q1Calc.setBounds(100, 100, 300, 100);
        q1Calc.setFont(font1);
        q2NumberGuess.setBounds(100, 220, 300, 100);
        q2NumberGuess.setFont(font1);
        q4Archiver.setBounds(100, 340, 300, 100);
        q4Archiver.setFont(font1);
        assignmentWindow.add(q1Calc);
        assignmentWindow.add(q2NumberGuess);
        assignmentWindow.add(q4Archiver);
        assignmentWindow.setVisible(true);
    }
}
