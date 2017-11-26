package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import seminar1.collections.LinkedStack;

/**
 * ( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) ) = 101
 * ( 1 + ( 5 * ( 4 * 5 ) ) )
 * ( 1 + ( 5 * 20 ) ) = 101
 * ( 1 + 100 ) = 101
 * <p>
 * 1 + ( 2 + 3 ) * 4 * 5 = 101
 * 1 + 5 * 4 * 5 = 101
 * 1 + 5 * 20 = 101
 * 1 + 100 = 101
 * 20 / 4 = 5
 * (101 - 1) / 5 = 20
 * <p>
 * Считаем, что операции деления на ноль отсутствуют
 */
public class SolverExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN = '(';
    private static final char RIGHT_PAREN = ')';
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char TIMES = '*';
    private static final char DIVISION = '/';

    private static double evaluate(String[] values) {
        LinkedStack<Double> numeric = new LinkedStack<>();
        LinkedStack<Character> operand = new LinkedStack<>();
        for (String value : values) {
            char tmp = 0;
            switch (value.charAt(0)) {
            case LEFT_PAREN:
                operand.push(LEFT_PAREN);
                break;
            case RIGHT_PAREN:
                tmp = operand.pop();
                while (tmp != LEFT_PAREN) {
                    double b = numeric.pop();
                    double a = numeric.pop();
                    numeric.push(calculate(a, b, tmp));
                    tmp = operand.pop();
                }
                break;
            case PLUS:
                if (operand.isEmpty()) {
                    operand.push(PLUS);
                } else {
                    tmp = operand.pop();
                    if (tmp != LEFT_PAREN) {
                        double b = numeric.pop();
                        double a = numeric.pop();
                        numeric.push(calculate(a, b, tmp));
                    } else {
                        operand.push(tmp);
                    }
                    operand.push(PLUS);
                }
                break;
            case MINUS:
                if (operand.isEmpty()) {
                    operand.push(MINUS);
                } else {
                    tmp = operand.pop();
                    if (tmp != LEFT_PAREN) {
                        double b = numeric.pop();
                        double a = numeric.pop();
                        numeric.push(calculate(a, b, tmp));
                    } else {
                        operand.push(tmp);
                    }
                    operand.push(MINUS);
                }
                break;
            case TIMES:
                if (operand.isEmpty()) {
                    operand.push(TIMES);
                } else {
                    tmp = operand.pop();
                    if ((tmp != LEFT_PAREN) && (tmp != PLUS) && (tmp != MINUS)) {
                        double b = numeric.pop();
                        double a = numeric.pop();
                        numeric.push(calculate(a, b, tmp));
                    } else {
                        operand.push(tmp);
                    }
                    operand.push(TIMES);
                }
                break;
            case DIVISION:
                if (operand.isEmpty()) {
                    operand.push(DIVISION);
                } else {
                    tmp = operand.pop();
                    if ((tmp != LEFT_PAREN) && (tmp != PLUS) && (tmp != MINUS)) {
                        double b = numeric.pop();
                        double a = numeric.pop();
                        numeric.push(calculate(a, b, tmp));
                    } else {
                        operand.push(tmp);
                    }
                    operand.push(DIVISION);
                }
                break;
            default:
                numeric.push(Double.valueOf(value));
                break;
            }
        }
        while (!operand.isEmpty()) {
            char tmp = operand.pop();
            double b = numeric.pop();
            double a = numeric.pop();
            numeric.push(calculate(a, b, tmp));
        }
        return numeric.pop();
        //return numeric.pop();
    }

    private static double calculate(double a, double b, Character c) {
        switch (c) {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            return a / b;
        }
        return 0;
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(evaluate(sequence.split(" ")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
