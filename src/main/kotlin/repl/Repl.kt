package repl

import calculator.Calculator
import java.io.InputStreamReader
import java.io.PrintWriter
import java.io.Reader
import java.io.Writer
import java.util.*

class Repl(
  private val calculator: Calculator,
  input: Reader = InputStreamReader(System.`in`),
  private val output: Writer = PrintWriter(System.out),
) {
  private val scanner: Scanner = Scanner(input as Readable)

  fun start() {
    while (true) {
      write("> ")
      val line: String = scanner.nextLine()

      if (line.equals("exit", ignoreCase = true) || line.isEmpty()) {
        write("Goodbye\n")
        break
      }

      val result = calculator.eval(line)
      write("$result\n")
    }
  }

  private fun write(message: String) {
    output.write(message)
    output.flush()
  }
}
