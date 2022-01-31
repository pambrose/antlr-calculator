package calculator

import org.antlr.v4.runtime.tree.ParseTree
import parser.CalculationVisitor
import repl.Repl

class VisitorBasedCalculator : Calculator {
  private val symbolTable = mutableMapOf<String, Double>()

  override fun eval(input: String): Double {
    val visitor = CalculationVisitor(symbolTable)
    val tree: ParseTree = parseTree(input)
    return visitor.visit(tree)
  }

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      Repl(VisitorBasedCalculator()).start()
    }
  }
}