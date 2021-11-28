package parser

import CalculatorBaseVisitor
import CalculatorParser

class CalculationVisitor : CalculatorBaseVisitor<Double>() {
  /**
   * Numbers are non-terminal.
   * Although less precise, a double makes it
   * easier to deal with division in this toy application.
   *
   * @return Double
   */
  override fun visitNumber(ctx: CalculatorParser.NumberContext): Double {
    return ctx.NUMBER().text.toDouble()
  }

  /**
   * Parentheses are used to give precedence to
   * the expression around which they are wrapped.
   *
   * This precedence is caused elsewhere,
   * in the grammar, via the order in which
   * the rules are defined (ANTLR4).
   *
   * @return Double
   */
  override fun visitParentheses(ctx: CalculatorParser.ParenthesesContext): Double {
    return this.visit(ctx.inner)
  }

  /**
   * @return Double
   */
  override fun visitPower(ctx: CalculatorParser.PowerContext): Double {
    return Math.pow(this.visit(ctx.left), this.visit(ctx.right))
  }

  override fun visitMultiplicationOrDivision(ctx: CalculatorParser.MultiplicationOrDivisionContext): Double {
    return if (ctx.operator.text.equals("*")) {
      this.visit(ctx.left) * this.visit(ctx.right)
    } else this.visit(ctx.left) / this.visit(ctx.right)
  }

  override fun visitAdditionOrSubtraction(ctx: CalculatorParser.AdditionOrSubtractionContext): Double {
    return if (ctx.operator.text.equals("+")) {
      this.visit(ctx.left) + this.visit(ctx.right)
    } else this.visit(ctx.left) - this.visit(ctx.right)
  }
}