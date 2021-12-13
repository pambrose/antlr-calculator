package parser

import CalculatorBaseListener
import CalculatorParser
import java.util.*

class CalculationListener(val symbolTable: MutableMap<String, Double>) : CalculatorBaseListener() {
  /**
   * A stack is used to track what numbers
   * we have seen last. This is our only
   * means of communicating values.
   *
   * The right number in
   * an operation is always the last one seen or
   * calculated for that operation. Because a stack
   * works via Last In First Out,
   * the right number should be "popped" off first.
   */
  private val stack = Stack<Double>()

  override fun exitAssign(ctx: CalculatorParser.AssignContext) {
    val variableName = ctx.VARIABLE().text
    val number = stack.pop()
    symbolTable[variableName] = number
    stack.push(number)
  }

  override fun exitVariable(ctx: CalculatorParser.VariableContext) {
    val variableName = ctx.VARIABLE().text
    val number = symbolTable[variableName] ?: error("Variable $variableName is not defined")
    stack.push(number)
  }

  override fun exitNumber(ctx: CalculatorParser.NumberContext) {
    val number = ctx.NUMBER().text.toDouble()
    stack.push(number)
  }

  override fun exitPower(ctx: CalculatorParser.PowerContext?) {
    val right = stack.pop()
    val left = stack.pop()
    stack.push(Math.pow(left, right))
  }

  override fun exitModulo(ctx: CalculatorParser.ModuloContext?) {
    val right = stack.pop()
    val left = stack.pop()
    stack.push(left % right)
  }

  override fun exitMultiplicationOrDivision(ctx: CalculatorParser.MultiplicationOrDivisionContext) {
    val right = stack.pop()
    val left = stack.pop()
    if (ctx.operator.text == "*")
      stack.push(left * right)
    else
      stack.push(left / right)
  }

  override fun exitAdditionOrSubtraction(ctx: CalculatorParser.AdditionOrSubtractionContext) {
    val right = stack.pop()
    val left = stack.pop()
    if (ctx.operator.text == "+")
      stack.push(left + right)
    else
      stack.push(left - right)
  }

  /**
   * The last value on the stack is the result of all
   * applied calculations.
   *
   * @return Double
   */
  val result: Double
    get() = stack.pop()
}