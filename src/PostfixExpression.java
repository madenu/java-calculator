// Copyright (C) 2019 Matthias Denu & Steven Than
// Just don't plagiarize us for your homework assignment.

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class represents a single postfix expression.
 */
public class PostfixExpression extends PartialExpression {
  private List<String> exp;

  /**
   * Constructs a postfix expression given a non-empty string.
   * The string should have operands and operators separated by spaces.
   * spaces.
   *
   * @param exp expression
   * @throws IllegalArgumentException if above requirements are not met
   */
  public PostfixExpression(String exp) throws IllegalArgumentException {
    super();
    this.validOperators.put("(", 0);
    this.validOperators.put(")", 0);
    this.exp = this.verifyExp(exp);
  }

  /**
   * Verifies input expression is valid.
   * A valid expression consists of 2 operands per operator, trailing/leading spaces are ok.
   *
   * @param exp the string expression to be verified
   * @return the expression broken down into tokens, stored as ArrayList
   * @throws IllegalArgumentException when the criteria is not met
   */
  private List<String> verifyExp(String exp) throws IllegalArgumentException {
    Scanner sc = new Scanner(exp);
    String token;
    List<String> result = new ArrayList<>();
    MyStack<String> temp = new MyStack<>();

    /*
      Generates a list of valid operators and operands from string expression. At the same, uses
      a stack to try adding in and popping out tokens so that the stack only has 1 token left
      at the end.
    */
    while (sc.hasNext()) {
      token = sc.next();

      if (super.isOperand(token)) {
        temp.push(token);
      } else if (super.isOperator(token)) {
        try {
          temp.pop();
          temp.pop();
        } catch (NoSuchElementException e) {
          throw new IllegalArgumentException("Mismatched amount of operands and operators");
        }
        temp.push("0");
      } else {
        throw new IllegalArgumentException("Expression contains invalid tokens");
      }
      result.add(token);
    }

    if (temp.size() != 1) {
      throw new IllegalArgumentException("Mismatched amount of operands and operators");
    }
    return result;
  }

  /**
   * Evaluates and returns the result of a postfix expression.
   *
   * @return result of expression.
   * @throws ArithmeticException if expression cannot be evaluated.
   */
  @Override
  public double evaluate() throws ArithmeticException {
    MyStack<String> temp = new MyStack<>();
    for (String token : this.exp) {
      if (super.isOperand(token)) {
        temp.push(token);
      } else {
        temp.push(Double.toString(super.calculate(temp.pop(), temp.pop(), token)));
      }
    }
    return Double.parseDouble(temp.pop());
  }

  /**
   * Returns a string representation of the expression.
   *
   * @return a string representation of the expression.
   */
  @Override
  public String toString() {
    return String.join(" ", this.exp);
  }
}
