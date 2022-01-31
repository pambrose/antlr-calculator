package calculator

import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import parser.CalculationListener
import repl.Repl

class ListenerBasedCalculator : Calculator {
  private val symbolTable = mutableMapOf<String, Double>()

  override fun eval(input: String): Double {
    val calculator = CalculationListener(symbolTable)
    val tree: ParseTree = parseTree(input)
    val walker = ParseTreeWalker().apply { walk(calculator, tree) }
    return calculator.result
  }

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      Repl(ListenerBasedCalculator()).start()
    }
  }
}