package com.test

import pureconfig.ConfigReader.Result
import pureconfig.ConfigSource
import pureconfig._
import pureconfig.generic.auto._

object TestConfig extends App {

//  ConfigSource.default.load[ServiceConf] match {
  ConfigSource.file(args(0)).load[ServiceConf] match {
    case Left(fail) => ???
    case Right(conf) => println(conf.host)
  }




}
