package calculator

import CalculatorLexer
import CalculatorParser
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.Lexer
import org.antlr.v4.runtime.tree.ParseTree

interface Calculator {
  fun eval(input: String): Double

  fun parseTree(input: String): ParseTree {
    val chars: CharStream = CharStreams.fromString(input)
    val lexer: Lexer = CalculatorLexer(chars)
    val tokens = CommonTokenStream(lexer)
    val parser = CalculatorParser(tokens)
    return parser.start()
  }
}