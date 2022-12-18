package funsets

object Main extends App {
  import FunSets._

  val S1: Set = union(union(singletonSet(-9), singletonSet(3)),union(singletonSet(4), singletonSet(13)));
  val S2: Set = union(union(singletonSet(1), singletonSet(3)),union(singletonSet(7), singletonSet(13)));
  val S3: Set = union(union(singletonSet(0), singletonSet(13)),union(singletonSet(14), singletonSet(14)));
  def P1(a: Int): Boolean = a match{
    case 1 => true
    case 3 => true
    case 7 => true
    case 13 => true
    case _ => false
  }
  def P2(a: Int): Boolean = a match {
    case -10 => true
    case -9 => true
    case -8 => true
    case 7 => true
    case _ => false
  }
  def P3(a: Int): Boolean = (a > 0)
  def F1(a: Int): Int = a * a
  def F2(a: Int): Int = a + 10

  /** TESTING **/
  print("Built-in test: ")
  println(contains(singletonSet(1), 1))

  println("\nMy tests:")
  print("Set 1: ")
  printSet(S1)
  print("Set 2: ")
  printSet(S2)
  print("Set 3: ")
  printSet(S3)
  print("\nP1 on N as Set: ")
  printSet(P1)
  print("P2 on N as Set: ")
  printSet(P2)
  println("P3 on N as Set: {1,2,3,4,5...}")
  println("\nF1 is squaring the value")
  println("F2 adds 10 to the value")

  println("\nBasic functions test")
  print("Union of three sets: ")
  printSet(union(union(S1, S2),S3))
  print("Intersection of S1 & S2: ")
  printSet(intersect(S1,S2))
  print("Difference of S1 & S2: ")
  printSet(diff(S1,S2))
  print("Difference of S2 & S1: ")
  printSet(diff(S2, S1))
  print("Filter P2 on S1: ")
  printSet(filter(S1, P2))
  print("Sets are functions! Filter S2 on S1, same as Intersection: ")
  printSet(filter(S1, S2))
  print("Filter P1 on union of all three sets. Then filter P2: ")
  printSet(filter(filter(union(union(S1, S2),S3), P1), P2))

  print("\nIterative functions test:\n")
  print("ForAll S1 and P3: ")
  println(forall(S1, P3))
  print("ForAll S2 and P3: ")
  println(forall(S2, P3))
  print("ForAll S2 and P1: ")
  println(forall(S2, P1))
  print("Exists S1 and P3: ")
  println(exists(S1, P3))
  print("Exists S3 and P2: ")
  println(forall(S1, P3))

  print("\nMap test:\n")
  print("Map of S1 with F1: ")
  printSet(map(S1, F1))
  print("Map of S2 with F1: ")
  printSet(map(S2, F1))
  print("Map of S1 with F2: ")
  printSet(map(S1, F2))
  print("Map of S3 with F2 then F1: ")
  printSet(map(map(S3, F2), F1))
}
