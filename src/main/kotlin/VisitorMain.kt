import calculator.VisitorBasedCalculator

object VisitorMain {
  @JvmStatic
  fun main(args: Array<String>) {
    VisitorBasedCalculator().repl.start()
  }
}