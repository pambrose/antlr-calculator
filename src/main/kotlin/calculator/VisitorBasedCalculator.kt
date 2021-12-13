package calculator

import CalculatorLexer
import CalculatorParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Lexer
import org.antlr.v4.runtime.tree.ParseTree
import parser.CalculationVisitor

class VisitorBasedCalculator : Calculator {
  private val symbolTable = mutableMapOf<String, Double>()

  override fun eval(input: String): Double {
    val chars: CharStream = CharStreams.fromString(input)
    val lexer: Lexer = CalculatorLexer(chars)
    val tokens = CommonTokenStream(lexer)
    val parser = CalculatorParser(tokens)
    val tree: ParseTree = parser.start()
    val visitor = CalculationVisitor(symbolTable)
    return visitor.visit(tree)
  }
}