import calculator.*
import io.kotest.core.spec.style.*
import io.kotest.matchers.*

class CalculatorTest : StringSpec(
  {
    val calculators = listOf(ListenerBasedCalculator(), VisitorBasedCalculator())

    "Values" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { value ->
          calculator.eval(value.toString()) shouldBe value
        }
      }
    }

    "Addition" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { value ->
          calculator.eval("$value + $value") shouldBe value + value
          calculator.eval("$value + $value + $value") shouldBe value + value + value
        }
      }
    }

    "Subtraction" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { val0 ->
          val val1 = val0 + 1
          calculator.eval("$val0 - $val1") shouldBe val0 - val1
          calculator.eval("$val0 - $val1 - $val0") shouldBe val0 - val1 - val0
        }
      }
    }

    "Multiplication" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { val0 ->
          val val1 = val0 + 1
          calculator.eval("$val0 * $val1") shouldBe val0 * val1
          calculator.eval("$val0 * $val1 * $val0") shouldBe val0 * val1 * val0
        }
      }
    }

    "Division" {
      calculators.forEach { calculator ->
        (-1000..1000).forEach { val0 ->
          if (val0 != 0) {
            val val1 = val0 + 1
            calculator.eval("$val1 / $val0") shouldBe val1.toDouble() / val0.toDouble()
          }
        }
      }
    }

    "Power" {
      calculators.forEach { calculator ->
        (-100..100).forEach { val0 ->
          val val1 = val0 + 1
          calculator.eval("$val0 ^ $val1") shouldBe Math.pow(val0.toDouble(), val1.toDouble())
        }
      }
    }

    "Modulo" {
      calculators.forEach { calculator ->
        (-100..100).forEach { val0 ->
          val val1 = val0 + 1
          calculator.eval("$val0 % $val1") shouldBe val0.toDouble() % val1.toDouble()
        }
      }
    }

    "Assignment" {
      calculators.forEach { calculator ->
        (-100..100).forEach { value ->
          calculator.eval("a = $value") shouldBe value
          calculator.eval("a") shouldBe value
        }
      }
    }
  })