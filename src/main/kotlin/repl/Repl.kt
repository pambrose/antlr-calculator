package repl

import calculator.Calculator
import java.io.Reader
import java.io.Writer
import java.util.*

class Repl(input: Reader, private val calculator: Calculator, private val output: Writer) {
  private val scanner: Scanner = Scanner(input as Readable)

  fun start() {
    while (true) {
      write("> ")
      val line: String = scanner.nextLine()

      if (line.equals("exit", ignoreCase = true) || line.isEmpty()) {
        write("Goodbye\n")
        break
      }

      val result = calculator.calculate(line)
      write("$result\n")
    }
  }

  private fun write(message: String) {
    output.write(message)
    output.flush()
  }
}