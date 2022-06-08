package calculator

import CalculatorLexer
import CalculatorParser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.*

interface Calculator {
  fun eval(input: String): Double

  fun parseTree(input: String): ParseTree {
    val chars: CharStream = CharStreams.fromString(input)
    val lexer: Lexer = CalculatorLexer(chars)
    val tokens = CommonTokenStream(lexer)
    return CalculatorParser(tokens)
      .run {
        errorHandler = BailErrorStrategy()
        start()
      }
  }
}