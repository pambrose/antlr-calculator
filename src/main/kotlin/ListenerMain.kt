import calculator.ListenerBasedCalculator
import repl.Repl
import java.io.InputStreamReader
import java.io.PrintWriter

object ListenerMain {
  @JvmStatic
  fun main(args: Array<String>) {
    val repl = Repl(
      InputStreamReader(System.`in`),
      ListenerBasedCalculator(),
      PrintWriter(System.out)
    )
    repl.start()
  }
}