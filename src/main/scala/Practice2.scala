//2-15 上級問題 lazyを使う
/*
  変数を宣言する前に「lazy」キーワードを付与することで、
  最初に参照されるまで変数を初期化しないようにすることができる。
  (例えば、"クラス初期化時には計算されない"など)

　この変数は最初の参照時に1度計算されたら、その後計算されることはない。(計算結果は保存されるので)
  そのため、アプリケーションの起動を高速化できる。
*/
/*
  (new C).printBar()
*/
trait A {
  val foo: String
}

trait B extends A {
  val bar = foo + "World"
}

class C extends B {

  /*
    下記のprintBarメソッドの結果は、
    val foo = "Hello" の頭に
     •lazyがないと、"nullWorld"
     •lazyがあると、"HelloWorld"
    が、それぞれ表示される。

    lazyがないと、"nullWorld"になる理由：
     Scala のクラスおよびトレイトはスーパークラスから順番に初期化される。
     今回の例で言えば、クラス CはtraitBを継承し、traitBはtraitAを継承している。
     つまり初期化はtraitAが一番先におこなわれ、変数fooが宣言され、中身は何も代入されていないので、nullになる。
     次にtraitBで変数barが宣言され、nullである変数fooと"World"という文字列から"nullWorld"という文字列が作られ、変数barに代入される。
     そして、printBar()により、変数barの中身である"nullWorld"が表示される。

    "HelloWorld"を表示させるには...
     val bar = foo + "World" の評価が早すぎたため、"nullWorld"となった。
     そこで、valの前にlazyという修飾子を使うことで、変数fooに初めてアクセスされた時(値が代入された時)にその内容が評価されるようにする。
  */
  lazy val foo = "Hello"
  def printBar(): Unit = println(bar)
}