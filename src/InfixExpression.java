// Copyright (C) 2019 Matthias Denu & Steven Than
// Just don't plagiarize us for your homework assignment.

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents a infix expression.
 */
public class InfixExpression extends PartialExpression {
  private String exp;

  /**
   * Constructs a infix expression given a non-empty string.
   * The string should only have operands, operators and parentheses separated by spaces.
   *
   * @param exp expression
   * @throws IllegalArgumentException if above requirements are not met
   */
  public InfixExpression(String exp) {
    super();
    this.validOperators.put("(", -1);
    this.validOperators.put(")", -1);
    verifyAllValidTokens(exp);
    this.exp = exp;
    this.toPostfix();
  }

  private void verifyAllValidTokens(String exp) {
    exp = exp.replaceAll("\\(", "").replaceAll("\\)","");
    Scanner sc = new Scanner(exp);
    String token;
    boolean operand = false;

    while (sc.hasNext()) {
      token = sc.next();
      if (isOperand(token) && !operand) {
        operand = true;
      } else if (isOperator(token) && operand) {
        operand = false;
      } else {
        throw new IllegalArgumentException("Two operands/operators cannot be next to each other");
      }
    }
  }

  /**
   * Converts this expression to a postfix expression and returns it.
   *
   * @return this as a postfix expression.
   */
  public PostfixExpression toPostfix() {
    Scanner sc = new Scanner(this.exp);
    MyStack<String> opStack = new MyStack<>();
    StringBuilder result = new StringBuilder();
    String token;

    while (sc.hasNext()) {
      token = sc.next();

      if (super.isOperand(token)) {
        result.append(" ").append(token);
      } else if (opStack.isEmpty() || token.equals("(")) {
        opStack.push(token);
      } else if (token.equals(")")) {
        while (!opStack.isEmpty() && !opStack.top().equals("(")) {
          result.append(" ").append(opStack.pop());
        }
        try {
          opStack.pop();
        } catch (NoSuchElementException e) {
          throw new IllegalArgumentException("Parentheses was not closed correctly.");
        }
      } else {
        while (!opStack.isEmpty() && (precedenceLessOrEqual(token, opStack.top()))) {
          result.append(" ").append(opStack.pop());
        }
        opStack.push(token);
      }
    }

    while (!opStack.isEmpty()) {
      result.append(" ").append(opStack.pop());
    }

    return new PostfixExpression(result.toString().trim());
  }

  private boolean precedenceLessOrEqual(String op1, String op2) {
    return this.validOperators.get(op1) <= this.validOperators.get(op2);
  }

  /**
   * Evaluates and returns the result of a infix expression.
   *
   * @return result of expression.
   * @throws ArithmeticException if expression cannot be evaluated.
   */
  @Override
  public double evaluate() throws ArithmeticException {
    return this.toPostfix().evaluate();
  }

  /**
   * Returns a string representation of the expression.
   *
   * @return a string representation of the expression.
   */
  @Override
  public String toString() {
    return this.exp;
  }
}
