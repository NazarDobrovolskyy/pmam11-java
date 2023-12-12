trait Monoid[A] {
  def combine(x: A, y: A): A
  def identity: A
}

object Monoid {
  implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
    def combine(x: Int, y: Int): Int = x + " " + y
    def identity: Int = 0
  }

  implicit val stringMonoid: Monoid[String] = new Monoid[String] {
    def combine(x: String, y: String): String = x + y
    def identity: String = ""
  }
}

// Example usage:
import Monoid._

val sumMonoidExample = intMonoid.combine(3, 5) // Output: 8
val stringConcatMonoidExample = stringMonoid.combine("Hello, ", "World!") // Output: "Hello, World!"
