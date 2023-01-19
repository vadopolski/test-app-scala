package com.test


import scopt.OParser


object ParseConfig extends App {


  val builder = OParser.builder[Config]
  val parser1 = {
    import builder._
    OParser.sequence(
      programName("scopt"),
      head("scopt", "4.x"),
      // option -f, --foo
      opt[Int]('f', "foo")
        .action((x, c) => c.copy(foo = x))
        .required()
        .validate(x =>
          if (x > 700) success
          else failure("Value <max> must be >0"))
        .text("foo is an integer property"),
      // more options here...
    )
  }

  /**
   *
   * # Run on a Spark standalone cluster in client deploy mode
   *  ./bin/spark-submit \
   *  --class org.apache.spark.examples.SparkPi \
   *  --master spark://207.184.161.138:7077 \
   *  --executor-memory 20G \
   *  --total-executor-cores 100 \
   *  /path/to/examples.jar \
   *  --port_num 1000
   *  --age 1000
   *  --ex_num 1000
   *  --ex_cores 1000
   *  --ex_memory 1000
   *  --port_num 1000
   *
   *  airflow
   *  alter often
   *
   *  alter not often
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
   *
  * */





  // OParser.parse returns Option[Config]
  OParser.parse(parser1, args, Config()) match {
    case Some(config) => println(config.foo)
    // do something
    case _ =>
    // arguments are bad, error message will have been displayed
  }


}
