import sbt._

object Dependencies {

  val sparkGraphx = "org.apache.spark" %% "spark-graphx" % "2.4.0-cdh6.1.0"
  val sparkHive = "org.apache.spark" %% "spark-hive" % "2.4.0-cdh6.1.0"
  val sparkMllib = "org.apache.spark" %% "spark-mllib" % "2.4.0-cdh6.1.0"
  val sparkStreamingKafka = "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.0-cdh6.1.0"
  val sparkYarn = "org.apache.spark" %% "spark-yarn" % "2.4.0-cdh6.1.0"
  val mysqlConnectorJava = "mysql" % "mysql-connector-java" % "5.1.38"

  val sparkGraphxProvided = "org.apache.spark" %% "spark-graphx" % "2.4.0-cdh6.1.0" % "provided"
  val sparkHiveProvided = "org.apache.spark" %% "spark-hive" % "2.4.0-cdh6.1.0" % "provided"
  val sparkMllibProvided = "org.apache.spark" %% "spark-mllib" % "2.4.0-cdh6.1.0" % "provided"
  val sparkStreamingKafkaProvided = "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.0-cdh6.1.0" % "provided"
  val sparkYarnProvided = "org.apache.spark" %% "spark-yarn" % "2.4.0-cdh6.1.0" % "provided"
  val mysqlConnectorJavaProvided = "mysql" % "mysql-connector-java" % "5.1.38" % "provided"

  val scopt = "com.github.scopt" %% "scopt" % "3.7.0"

  val scalatest = "org.scalatest" %% "scalatest" % "3.0.5"
  // 编译时用
  val sparkCommonDeps = Seq(sparkGraphx, sparkHive, sparkMllib, sparkStreamingKafka, sparkYarn, mysqlConnectorJava)
  // 打包时用
  val sparkCommonProvidedDeps = Seq(sparkGraphxProvided, sparkHiveProvided, sparkMllibProvided,
    sparkStreamingKafkaProvided, sparkYarnProvided, mysqlConnectorJavaProvided)
}
