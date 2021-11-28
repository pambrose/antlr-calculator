import calculator.ListenerBasedCalculator
import calculator.VisitorBasedCalculator
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class GrammarTest : StringSpec(
  {
    val calculators = listOf(ListenerBasedCalculator(), VisitorBasedCalculator())

    "Single values" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { value ->
          calculator.calculate(value.toString()) shouldBe value
        }
      }
    }

    "Addition" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { value ->
          calculator.calculate("$value + $value") shouldBe value + value
          calculator.calculate("$value + $value + $value") shouldBe value + value + value
        }
      }
    }

    "Subtraction" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { value ->
          calculator.calculate("$value - $value") shouldBe value - value
          calculator.calculate("$value - $value - $value") shouldBe value - value - value
        }
      }
    }

    "Multiplication" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { value ->
          calculator.calculate("$value * $value") shouldBe value * value
          calculator.calculate("$value * $value * $value") shouldBe value * value * value
        }
      }
    }

    "Division" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { value ->
          if (value != 0) {
            calculator.calculate("$value / $value") shouldBe value.toDouble() / value.toDouble()
            calculator.calculate("$value / $value / $value") shouldBe value.toDouble() / value.toDouble() / value.toDouble()
          }
        }
      }
    }
  })