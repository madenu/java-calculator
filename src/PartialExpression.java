import java.util.HashMap;

/**
 * This class represents an incomplete expression.
 */
public abstract class PartialExpression implements Expression {
  protected HashMap<String, Integer> validOperators;

  protected PartialExpression() {
    this.validOperators = new HashMap<>();
    validOperators.put("+", 0);
    validOperators.put("-", 0);
    validOperators.put("*", 1);
    validOperators.put("/", 1);
  }

  /**
   * Checks if token is an operator.
   * Valid operators: + - * /.
   *
   * @param token token to be checked
   * @return true if token is an operator, false otherwise
   */
  protected boolean isOperator(String token) {
    return this.validOperators.keySet().contains(token);
  }

  /**
   * Checks if token is an operand.
   * Valid operands: numbers, characters
   *
   * @param token to check.
   * @return true if operand, otherwise false.
   */
  protected boolean isOperand(String token) {
    if (token.isEmpty()) {
      return false;
    }
    for (int i = 0; i < token.length(); i++) {
      char ch = token.charAt(i);
      if (!(Character.isLetterOrDigit(ch) || ch == '.' || (ch == '-' && token.length() > 1))) {
        return false;
      }
    }
    return true;
  }

  /**
   * Parse operands into double and send them off for calculation.
   *
   * @param num2 the first operand as String
   * @param num1 the second operand as String
   * @param op the operator
   * @return calculated result as double
   * @throws ArithmeticException when the two operands cannot be parsed to double
   */
  protected double calculate(String num2, String num1, String op) throws ArithmeticException {
    try {
      return calculate(Double.parseDouble(num2), Double.parseDouble(num1), op);
    } catch (NumberFormatException e) {
      throw new ArithmeticException("The operand being evaluated is not a number");
    }
  }

  /**
   * Calculates result of 2 operands using provided operator.
   *
   * @param num2 the first operand
   * @param num1 the second operand
   * @param op the operator
   * @return calculated result as double
   */
  protected double calculate(double num2, double num1, String op) {
    double result;

    switch (op) {
      case "+":
        result = num1 + num2;
        break;

      case "-":
        result = num1 - num2;

        break;

      case "*":
        result = num1 * num2;
        break;

      case "/":
        result = num1 / num2;
        break;

      default:
        throw new ArithmeticException("Unable to calculate");
    }
    return result;
  }
}
