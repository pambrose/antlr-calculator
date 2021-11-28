import calculator.VisitorBasedCalculator
import repl.Repl
import java.io.InputStreamReader
import java.io.PrintWriter

object VisitorMain {
  @JvmStatic
  fun main(args: Array<String>) {
    val repl = Repl(
      InputStreamReader(System.`in`),
      VisitorBasedCalculator(),
      PrintWriter(System.out)
    )
    repl.start()
  }
}