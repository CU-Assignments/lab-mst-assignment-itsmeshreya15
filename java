4. A string containing only parentheses is balanced if the following is true: 1. if it is an empty string 2. if A and B are correct, AB is correct, 3. if A is correct, (A) and {A} and [A] are also correct.
Examples of some correctly balanced strings are: "{}()", "[{()}]", "({()})"
Examples of some unbalanced strings are: "{}(", "({)}", "[[", "}{" etc.
Given a string, determine if it is balanced or not.
Input Format
There will be multiple lines in the input file, each having a single non-empty string. You should read input till end-of-file.
Output Format
For each case, print 'true' if the string is balanced, 'false' otherwise

CODE:-
import java.util.*;
import java.io.*;

public class BalancedBrackets {

    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                System.out.println(isBalanced(line));
            }
        }

        scanner.close();
    }
}











5. Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
•	'?' Matches any single character.
•	'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).
 
CODE:-
public class WildcardMatcher {

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty string and empty pattern are a match
        dp[0][0] = true;

        // Handle patterns with * at the beginning
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '?' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }

        return dp[m][n];
    }

    // Test the function
    public static void main(String[] args) {
        WildcardMatcher wm = new WildcardMatcher();

        // Test cases
        System.out.println(wm.isMatch("aa", "a"));       // false
        System.out.println(wm.isMatch("aa", "*"));       // true
        System.out.println(wm.isMatch("cb", "?a"));      // false
        System.out.println(wm.isMatch("adceb", "*a*b")); // true
        System.out.println(wm.isMatch("acdcb", "a*c?b")); // false
    }
}
