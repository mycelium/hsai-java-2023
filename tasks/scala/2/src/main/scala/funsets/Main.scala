package funsets

object Main extends App {

  import FunSets._

  println(contains(singletonSet(1), 1))
  val s1 = singletonSet(2)
  val s2 = singletonSet(3)
  val s3 = singletonSet(4)

  val u1 = union(s1, s2)
  val u2 = union(union(s1, s2), s3)
  printSet(u1)
  printSet(u2)
  printSet(intersect(u1, u2))
  printSet(diff(u2, u1))

  val s4 = Set(-1, 0, 4, -5, 2, 3)
  printSet(filter(s4, x => x < 0))
  println(forall(s4, x => x > -10))
  println(exists(s4, x => x % 2 == 0))

  val m = map(u2, x => x * x)
  printSet(m)
}
