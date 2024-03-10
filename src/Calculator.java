import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculator {
    static final Font font1 = new Font("Anonymous Pro", Font.BOLD, 36);

    public static void main(String[] args) {
        final String[] buttons = {
                "%", "CE", "C", "E", "1/X", "X^2", "√x",
                "/", "7", "8", "9", "*", "4", "5", "6",
                "-", "1", "2", "3", "+", "+/-", "0", ".", "="};
        final int[] valueButtons = {4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16
                , 17, 18, 19, 21, 22};
        //CALCULATOR WINDOW
        JFrame assignmentII = new JFrame("Calculator");
        assignmentII.setSize(400, 430);
        assignmentII.setLayout(null);
        assignmentII.setDefaultCloseOperation(assignmentII.DISPOSE_ON_CLOSE);
        assignmentII.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Closing");
                AssignmentII.main(new String[]{});
                assignmentII.dispose();
            }
        });
        //ASSIGN BUTTONS THEIR TEXT VALUES
        int boundX = -100, boundY = 20, count = -1;
        final int width = 100, height = 50;
        JTextField textField = new JTextField();
        textField.setBounds(0, 0, 400, 70);
        for (int j = 0; j < 6; j++) {
            boundY += height;
            for (int i = 0; i < 4; i++) {
                count++;
                boolean isVal = false;
                for (int val : valueButtons) {
                    if (count == val) {
                        isVal = true;
                        break;
                    }
                    ;
                }
                boundX += width;
                JButton btn = new JButton();
                btn.setBounds(boundX, boundY, width, height);
                btn.setText(buttons[count]);
                //IF BUTTON IS VALUE CONTAINER
                if (isVal) {
                    btn.addActionListener(e -> {
                                int caretPos = textField.getCaretPosition();
                                String firstText =
                                        textField.getText().substring(0,
                                                caretPos);
                                System.out.println(btn.getText());
                                String lastText =
                                        textField.getText().substring(caretPos);

                                if (btn.getText().equalsIgnoreCase("x^2")) {
                                    textField.setText(firstText + "^2" + lastText);
                                } else if (btn.getText().equalsIgnoreCase("1" +
                                        "/X")) {
                                    textField.setText("1/" + firstText + lastText);
                                    textField.setCaretPosition(textField.getText().length());
                                }
                                else {
                                    textField.setText(firstText +
                                            btn.getText() + lastText);
                                }
                                textField.setCaretPosition(caretPos + 1);
                                textField.requestFocus();
                            }
                    );
                }
                //IF IT IS CLEAR BUTTON
                if (btn.getText().equalsIgnoreCase("C")) {
                    btn.addActionListener(e -> {
                        textField.setText("");
                    });
                }
                //IF IT IS CALCULATE BUTTON
                if (btn.getText().equalsIgnoreCase("=")) {
                    btn.setBackground(Color.darkGray);
                    btn.setForeground(Color.WHITE);
                    btn.addActionListener(e -> {
                        StringBuilder expr =
                                new StringBuilder(textField.getText());
                        System.out.println(expr);
                        String [] variableValueString = splitString(expr.toString());
                        ArrayList<Double> valuesOfExpr = new ArrayList<>();
                        int y = 0;
                        for (String strValue: variableValueString) {
                            valuesOfExpr.add(Double.parseDouble(strValue));
                            variableValueString[y++] = "x";

                        }
                        System.out.println(valuesOfExpr);
                        for (int p = 0; p < 10; p++) {
                            System.out.println("==========");
                        }
                        for (String val: variableValueString) {
                            System.out.print(val + " | ");
                        }
                        for (int p = 0; p < 10; p++) {
                            System.out.println("==========");
                        }
                        String[] operators = expr.toString().split("[\\d]+");
                        for (String g: operators) {
                            System.out.println(g);
                        }
                        String[] repValueString = splitString(expr.toString());
                        ArrayList<String> operStr = new ArrayList<>();
                        for (int k = 0; k < expr.length(); k++) {
                            if (isOperator(expr.charAt(k))) {
                                operStr.add(String.valueOf(expr.charAt(k)));
                            }
                        }
                        ArrayList<String[]> hashMap = new ArrayList<>();
                        String[] hashKeys = {"a", "0"};
                        for (int k = 0; k < repValueString.length; k++) {
                            String[] mapKeys = new String[2];
                            //create key
                            mapKeys[0] = hashKeys[0] + hashKeys[1];
                            //create value associated with key
                            mapKeys[1] = repValueString[k];
                            incrementHashKey(hashKeys);
                            //get new key hash instead of actual value
                            repValueString[k] = mapKeys[0];
                            hashMap.add(mapKeys);
                        }
                        expr = new StringBuilder();
                        for (int k = 0; k < repValueString.length; k++) {
                            System.out.println(repValueString[k]);
                            expr.append("x");
                            if (k < repValueString.length - 1) {
                                expr.append(String.valueOf(operStr.get(k)));
                            }
                        }
                        //FULLY READY INFIX EXPRESSION (expr)
                        System.out.println("+++++++++++++++++++++++++++");
                        System.out.println(expr);
                        //CONVERT TO POSTFIX FORM FOR COMPUTATION
                        String postFixExpr = infixToPostfix(expr.toString());
                        System.out.println(postFixExpr);
                        textField.setText(evaluatePostFixExpr(postFixExpr,
                                valuesOfExpr));
                        System.out.println(evaluatePostFixExpr(postFixExpr,
                                valuesOfExpr));
                    });
                }
                assignmentII.add(btn);
                System.out.println("Added " + btn.getText());
            }
            boundX = -100;
        }
        //EQUAL BUTTON
        Component[] comps = assignmentII.getRootPane().getComponents();
        System.out.println(comps[1].getClass());
        //RESULT PANE
        textField.setForeground(Color.BLACK);
        System.out.println(infixToPostfix("22+43-87"));
        textField.setFont(font1);
        textField.getCaretPosition();
        textField.setCaretColor(Color.BLUE);
        assignmentII.add(textField);
        assignmentII.setVisible(true);
    }

    static int getValue(String key, ArrayList<String[]> hashMap) {
        for (String[] cols : hashMap) {
            if (cols[0].equalsIgnoreCase(key)) {
                return Integer.parseInt(cols[1]);
            }
        }
        return 0;
    }

    static void incrementHashKey(String[] hashKey) {
        if (hashKey[1].equals("9")) {
            hashKey[1] = "0";
            hashKey[0] =
                    String.valueOf((char) ((int) hashKey[0].charAt(0) + 1));
        } else {
            int intVal = Integer.parseInt(hashKey[1]) + 1;
            hashKey[1] = String.valueOf(intVal);
        }
    }

    public static String[] splitString(String input) {
        String operators = "+-/*%"; // Define your operators here
        String regex = "(?<=[" + operators + "])|(?=[" + operators + "])";
        String[] parts = input.split(regex);
        List<String> filteredParts = new ArrayList<>();
        for (String part : parts) {
            if (!part.matches("[" + operators + "]")) {
                filteredParts.add(part);
            }
        }
        return filteredParts.toArray(new String[0]);
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '/' || ch == '*' || ch == '%' || ch == '^';
    }

    static int prec(char c) {
        if (c == '^')
            return 4;
        else if (c == '√')
            return 3;
        else if (c == '/' || c == '*')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }

    static char associativity(char c) {
        if (c == '^')
            return 'R';
        return 'L';
    }

    static String infixToPostfix(String s) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0'
                    && c <= '9')) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && (prec(s.charAt(i)) < prec(stack.peek()) ||
                        prec(s.charAt(i)) == prec(stack.peek()) &&
                                associativity(s.charAt(i)) == 'L')) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    static String evaluatePostFixExpr(String postFix,
                                      ArrayList<Double> values) {
        Stack<Double> stack = new Stack<>();
//        System.out.println(postFix);
        for (int i = 0, count = 0; i < postFix.length(); i++) {
            char c = postFix.charAt(i);
            if (!isOperator(c)) {
                String key = postFix.substring(i, i + 2);
//                System.out.println("KEY: " + key);
//                System.out.println(getValue(key, hashMap));
                stack.push(values.get(count++));
            } else {
//                System.out.println("in stack");
                double val1 = stack.pop();
//                System.out.println(val1);
                double val2 = stack.pop();
//                System.out.println(val2);
                switch (c) {
                    case '+':
                        stack.push(val2 + val1);
                        break;
                    case '-':
                        stack.push(val2 - val1);
                        break;
                    case '/':
                        stack.push(val2 / val1);
                        break;
                    case '*':
                        stack.push(val2 * val1);
                        break;
                    case '^':
                        stack.push(Math.pow(val2, val1));
                        break;
                    case '√':
                        stack.push(Math.sqrt(val1));
                    case '%':
                        stack.push(val2 % val1);
                        break;
                }
            }
        }
        String answer = stack.getFirst().toString();
        if (answer.contains("E")) {
            int power = Integer.parseInt(answer.split("E")[1]);
            System.out.println("Power is " + power);
            answer = answer.replace("E", "");
            Double b = Double.parseDouble(answer);
            for (int i = 0; i < power; i++) {
                b *= 9;
            }
            System.out.println(b);
            System.out.println(answer);
            answer = String.valueOf(Double.parseDouble(answer) * Math.pow(10, power));
        }
        System.out.println(answer);
//        double ans = stack.getFirst().toString()
//                .replace("E", "").replace(".", "")
                //double value = Double.parseDouble(scientificNotation.replace("E", "E").replace(".", ""))
        return answer;
    }
}