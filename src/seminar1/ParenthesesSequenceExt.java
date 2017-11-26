package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import seminar1.collections.LinkedStack;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 *      взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 *      к которой приписана слева или справа правильная скобочная последовательность
 *      — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequenceExt {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';
    private static final char LEFT_BRACE     = '{';
    private static final char RIGHT_BRACE    = '}';
    private static final char LEFT_BRACKET   = '[';
    private static final char RIGHT_BRACKET  = ']';

    // sequence = "()()" | "(({}[]))[[[" | "{}" | ...
    private static boolean isBalanced(String sequence) {
        LinkedStack<Character> stack = new LinkedStack<>();
        for (int i = 0; i < sequence.length(); i++) {
            char tmp;
            switch (sequence.charAt(i)) {
            case LEFT_PAREN:
                stack.push(LEFT_PAREN);
                break;
            case RIGHT_PAREN:
                if (stack.isEmpty()) {
                    return false;
                }
                tmp = stack.pop();
                if (tmp != LEFT_PAREN) {
                    return false;
                }
                break;
            case LEFT_BRACE:
                stack.push(LEFT_BRACE);
                break;
            case RIGHT_BRACE:
                if (stack.isEmpty()) {
                    return false;
                }
                tmp = stack.pop();
                if (tmp != LEFT_BRACE) {
                    return false;
                }
                break;
            case LEFT_BRACKET:
                stack.push(LEFT_BRACKET);
                break;
            case RIGHT_BRACKET:
                if (stack.isEmpty()) {
                    return false;
                }
                tmp = stack.pop();
                if (tmp != LEFT_BRACKET) {
                    return false;
                }
                break;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
