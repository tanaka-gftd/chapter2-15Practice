//2-15 中級問題 分からなかった
case class Course(id: Int)
case class Driver(name: String)
object NoDriver extends Driver("no-driver")

trait Ridable {
  def ride(driver: Driver): Unit
}

trait Runnable {
  def run(course: Course): Unit
}

class Bicycle extends Ridable with Runnable {

  //現在のドライバーを記録する変数(型はDriver)を作り、最初はNoDriverを格納する
  private[this] var currentDriver: Driver = NoDriver

  //現在のドライバーを記録する変数に、取得したドライバー名を格納する(最初に格納されているNoDriverを上書きする)
  def ride(driver: Driver): Unit = currentDriver = driver

  //表示する
  def run(course: Course): Unit = println(s"${currentDriver.name} finish Course ${course.id}.")
}