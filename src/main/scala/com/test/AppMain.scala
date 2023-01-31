package com.test

import io.circe._
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._

import java.nio.file.{Files, Paths}
import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try, Using}

object AppMain extends App {
    case class Country(
                        name: Name,
                        capital: List[String],
                        area: Double, // required
                        region: Option[String]) // optional

    case class Name(common: String)

    case class CountryDto(
                           name: String,
                           capital: String,
                           area: Double
                         )

    object CountryDto {
        // TODO:  added chimney
        def createDto(country: Country): CountryDto =
            CountryDto(
                name = country.name.common,
                capital = country.capital.head,
                area = country.area
            )
    }

    implicit val inputCodec: JsonValueCodec[List[Country]] = JsonCodecMaker.make
    implicit val outputCodec: JsonValueCodec[List[CountryDto]] = JsonCodecMaker.make

    // TODO: Move to args
    val value = "src/main/resources/countries.json"

    val triedString: Try[List[Country]] = Using(Source.fromFile(value)) { source =>
      readFromString[List[Country]](source.mkString)
    }

    triedString match {
        case Success(countries) =>
            println(s"Source json was successfully red and validated, size is = ${countries.size}")

            val dtoes: List[CountryDto] = countries
              .filter(x => x.region.contains("Africa"))
              .sortBy(x => -x.area)
              .take(10)
              .map(x => CountryDto.createDto(x))

            println(s"Data successfully processed and converted to target size = ${dtoes.size}")

            // TODO: Move to args and to scopt
            val filename: String = "output.json"


            Try(Files.write(Paths.get(filename), writeToArray(dtoes))) match {
                // TODO: Add exception
                case Failure(exception) => ???
                case Success(path) => println(s"Processed file successfully written to $path")
            }


        case Failure(exception) => exception match {
            case ex: com.github.plokhotnyuk.jsoniter_scala.core.JsonReaderException => println(s"ERROR ${ex.getMessage}")
            case _ => exception.printStackTrace()
        }

    }



/*
    triedString match {
        case Success(value) => {
            val countries = {
                readFromArray[List[Country]](value.getBytes)
            }
            val result = countries
              .filter(_.region == "Africa")
              .map(c => CountryDto.createDto(c))
              .sortBy(-_.area).take(10)
            val resultJson = writeToArray(result)

            val filename: String = "output.json"
            val path = Paths.get(filename)
            try {
                Files.write(path, resultJson)
            } catch {
                case e: Exception => {
                    println(e)
                }
            }
        }
        case Failure(exception) => println(s"Could you pls check the source file")
    }

    triedString
      .flatMap(x => ??? )
      .recover{case ex: Exception => println(s"Could you pls check the source file") }


    triedString.foreach { s =>
        val countries = readFromArray[List[Country]](s.getBytes)
        val result = countries
          .filter(_.region == "Africa")
          .map(c => CountryDto.createDto(c))
          .sortBy(-_.area).take(10)
        val resultJson = writeToArray(result)

        val filename: String = "output.json"
        val path = Paths.get(filename)
        try {
            Files.write(path, resultJson)
        } catch {
            case e: Exception => {
                println(e)
            }
        }
    }
*/









}
