package com.test

import java.time.ZonedDateTime
import io.scalaland.chimney.dsl._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import com.github.plokhotnyuk.jsoniter_scala.core._



object Practice extends App {

// parse deserialize
  object in {
    case class MakeCoffee(
                           id: Int,
                           kind: String,
                           addict: String)
  }

  object out {
    case class CoffeeMade(
             id: Int,
             kind: String,
             forAddict: String,
             at: ZonedDateTime)

  }

  import in._
  import out._


  val command = MakeCoffee(1, "RAF", "test")
//  process

  val coffeeMade = CoffeeMade(
    id = command.id,
    kind = command.kind,
    forAddict = command.addict,
    at = ZonedDateTime.now())

  val event = command.into[CoffeeMade]
    .withFieldComputed(_.at, _ => ZonedDateTime.now)
    .withFieldRenamed(_.addict, _.forAddict)
    .transform
  // CoffeeMade(24, "Espresso", "Piotr", "2020-02-03T20:26:59.659647+07:00[Asia/Bangkok]")

//  serialize
  case class Device(id: Int, model: String)
  case class User(name: String, devices: Seq[Device])

  implicit val codec: JsonValueCodec[User] = JsonCodecMaker.make

  val inputt =
    """{
      |"name":"John",
      |"devices":[{"id":1,"model":"HTC One X"}]
      |}""".stripMargin


  val user = readFromArray(inputt.getBytes("UTF-8"))
  println(user)


//  val json = writeToArray(User(name = "John", devices = Seq(Device(id = 2, model = "iPhone X"))))


}
