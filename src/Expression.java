// Copyright (C) 2019 Matthias Denu & Steven Than
// Just don't plagiarize us for your homework assignment.

/**
 * This interface represents a math expression that consists of binary operators and operands.
 */
public interface Expression {
  /**
   * Evaluates and returns the result of an expression.
   *
   * @return result of expression.
   * @throws ArithmeticException if expression cannot be evaluated.
   */
  double evaluate() throws ArithmeticException;
}
