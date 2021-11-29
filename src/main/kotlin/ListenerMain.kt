import calculator.ListenerBasedCalculator

object ListenerMain {
  @JvmStatic
  fun main(args: Array<String>) {
    ListenerBasedCalculator().repl.start()
  }
}