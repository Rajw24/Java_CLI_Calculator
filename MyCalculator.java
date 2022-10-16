// Java calculator
import java.util.Scanner;
import java.util.Stack;

public class MyCalculator {

  // Method to evaluate value of a postfix expression
  static int evaluatePostfix(String exp) {
    //create a stack
    Stack<Integer> stk = new Stack<Integer>();
    // Scan all characters one by one
    for (int i = 0; i < exp.length(); i++) {
      char c = exp.charAt(i);
      // If the scanned character is an operand (number here),
      // push it to the stack.
      if (Character.isDigit(c)) stk.push(c - '0');
      // If the scanned character is an operator, pop two
      // elements from stack apply the operator
      else {
        int val1 = stk.pop();
        int val2 = stk.pop();
        switch (c) {
          case '+':
            stk.push(val2 + val1);
            break;
          case '-':
            stk.push(val2 - val1);
            break;
          case '/':
            stk.push(val2 / val1);
            break;
          case '*':
            stk.push(val2 * val1);
            break;
        }
      }
    }
    return stk.pop();
  }

  static String infixToPostfix(String exp) {
    // initializing empty String for result
    String result = new String("");
    // initializing empty stack
    Stack<Character> stk = new Stack<Character>();
    for (int i = 0; i < exp.length(); i++) {
      char c = exp.charAt(i);
      // If the scanned character is an
      // operand, add it to output.
      if (Character.isLetterOrDigit(c)) result += c;
      // If the scanned character is an '(',
      // push it to the stack.
      else if (c == '(') stk.push(c);
      //  If the scanned character is an ')',
      // pop and output from the stack
      // until an '(' is encountered.
      else if (c == ')') {
        while (!stk.isEmpty() && stk.peek() != '(') {
          result += stk.peek();
          stk.pop();
        }
        stk.pop();
      } else { // an operator is encountered
        while (!stk.isEmpty() && Prec(c) <= Prec(stk.peek())) {
          result += stk.peek();
          stk.pop();
        }
        stk.push(c);
      }
    }
    // pop all the operators from the stack
    while (!stk.isEmpty()) {
      if (stk.peek() == '(') return "Invalid Expression";
      result += stk.peek();
      stk.pop();
    }
    return result;
  }

  static int Prec(char ch) {
    switch (ch) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      case '^':
        return 3;
    }
    return -1;
  }

  // Driver program to test above functions
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println(
      "This is command line calculator.\nYou can do any calculations"
    );
    System.out.print(">>> ");
    String inf = scan.nextLine();
    String pfx = infixToPostfix(inf);
    System.out.println(">>> " + evaluatePostfix(pfx));
    scan.close();
  }
}
