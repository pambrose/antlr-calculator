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
  override fun calculate(input: String): Double {
    val chars: CharStream = CharStreams.fromString(input)
    val lexer: Lexer = CalculatorLexer(chars)
    val tokens = CommonTokenStream(lexer)
    val parser = CalculatorParser(tokens)
    val tree: ParseTree = parser.start()
    val calculator = CalculationVisitor()
    return calculator.visit(tree)
  }
}