package com.test

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import scala.io.{BufferedSource, Source}
import scala.util.{Try, Using}

object AppMain extends App {
    println(s"Source path ${args(0)}")
    println(s"Target path ${args(1)}")


    sealed trait Foo
    case class Bar(xs: Vector[String]) extends Foo
    case class Qux(i: Int, d: Option[Double]) extends Foo

    val foo: Foo = Qux(13, Some(14.0))
    println(foo)

    Using(Source.fromFile("src/main/resources/countries.json")) {byte =>
        val source = byte.mkString
        println(source)

        val value = decode[List[Foo]](source)

        value match {
            case Right(value) => value
            case Left(error) => println(error)

        }
    }















    val i: Int = Option(5) match {
        case Some(value) => value + 5
        case None => 0
    }



/*
    T => Monad[T]
    List[Int] => Int => List[Int]
    Option[Int] => Option[Int]

    val maybeInt: Option[Int] = Option(5)
*/

    val l:  Int => Option[Int] = t => Option(t) // unit
    val l2: Option[Int] => Int = (opt: Option[Int]) => opt.get //

/*
    val map: Option[Int] = Option(5).map(x => x + 5)
    val map2: Option[Option[Int]] = Option(5).map(x => Option(x + 5))
    val flatMap: Option[Int] = Option(5).flatMap(x => Option(x + 5))
*/

    val map = List(5).map(x => x + 5)
    val map2 = List(5).map(x => List(x + 5))
    val flatMap = List(5).flatMap(x => List(x + 5))

    /*
    *
    * */

/*
    val json: String = foo.asJson.noSpaces
    println(json)
//  String (json) => object


    val ex = """{"Qux":{"i":13,"d":14.0}}"""
    val ex2 = """{"Qux":{"i":13,"d":14.0}}"""

    val decodedFoo: Either[Error, Foo] = decode[Foo](json)
    println(decodedFoo.right)
*/





}
