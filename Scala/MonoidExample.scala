object MonoidExample {
  def combineList[A](list: List[A])(implicit monoid: Monoid[A]): A = {
    list.foldLeft(monoid.identity)(monoid.combine)
  }

  def main(args: Array[String]): Unit = {
    val intList = List(1, 2, 3, 4, 5)
    val stringList = List("I'm", "not", "a", "fan", "of", "functional programming")

    val combinedInt = combineList(intList)
    val combinedString = combineList(stringList)

    println(s"Combined Integers: $combinedInt") // Output: Combined Integers: 15
    println(s"Combined Strings: $combinedString") // Output: Combined Strings: I'm not a fan of functional programming
  }
}
