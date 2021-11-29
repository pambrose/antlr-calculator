package parser

import CalculatorBaseVisitor
import CalculatorParser

class CalculationVisitor(val symbolTable: MutableMap<String, Double>) : CalculatorBaseVisitor<Double>() {

  override fun visitAssign(ctx: CalculatorParser.AssignContext): Double {
    val variableName = ctx.VARIABLE().text
    val number = visit(ctx.right)
    symbolTable[variableName] = number
    return number
  }

  override fun visitVariable(ctx: CalculatorParser.VariableContext): Double {
    val variableName = ctx.VARIABLE().text
    return symbolTable[variableName] ?: error("Variable $variableName is not defined")
  }

  /**
   * Numbers are non-terminal.
   * Although less precise, a double makes it
   * easier to deal with division in this toy application.
   *
   */
  override fun visitNumber(ctx: CalculatorParser.NumberContext) = ctx.NUMBER().text.toDouble()

  override fun visitParentheses(ctx: CalculatorParser.ParenthesesContext) = visit(ctx.inner)

  override fun visitPower(ctx: CalculatorParser.PowerContext) = Math.pow(visit(ctx.left), visit(ctx.right))

  override fun visitModulo(ctx: CalculatorParser.ModuloContext) = visit(ctx.left) % visit(ctx.right)

  override fun visitMultiplicationOrDivision(ctx: CalculatorParser.MultiplicationOrDivisionContext) =
    if (ctx.operator.text == "*")
      visit(ctx.left) * visit(ctx.right)
    else
      visit(ctx.left) / visit(ctx.right)

  override fun visitAdditionOrSubtraction(ctx: CalculatorParser.AdditionOrSubtractionContext) =
    if (ctx.operator.text == "+")
      visit(ctx.left) + visit(ctx.right)
    else
      visit(ctx.left) - visit(ctx.right)
}