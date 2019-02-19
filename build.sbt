import Dependencies._

// 添加非托管依赖的jar
unmanagedBase := baseDirectory.value / "lib"
unmanagedJars in Compile := Seq.empty[sbt.Attributed[java.io.File]]

// cloudera 远程目录
externalResolvers ++= Seq(
  "spring" at "https://maven.aliyun.com/repository/spring",
  "spring-plugin" at "https://maven.aliyun.com/repository/spring-plugin",
  "cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  "bintray-sbt-plugins" at "http://dl.bintray.com/sbt/sbt-plugin-releases",
  "central" at "https://maven.aliyun.com/repository/central"
)

// 主工程
lazy val sparkLadder = (project in file("."))
  .aggregate(example)
  .settings(
    name := "spark-ladder",
    inThisBuild(List(
      scalaVersion := "2.11.8",
      version := "0.1.0-SNAPSHOT"
    ))
  )

// 事例项目
lazy val example = (project in file("example"))
  .settings(
    libraryDependencies ++= sparkCommonDeps,
    libraryDependencies += scopt,
    libraryDependencies += scalatest
  )

//执行assembly的时候忽略测试
test in assembly := {}
//指定类的名字
mainClass in assembly := Some("ladder.example.hive.SparkHiveExample")

/**
  * MergeStrategy.first:默认取得第一个匹配项
  * MergeStrategy.last:默认取得最后一个匹配项
  * MergeStrategy.discard:近丢弃匹配的文件
  **/
//解决依赖重复的问题
assemblyMergeStrategy in assembly := {
  case PathList(ps@_*) if ps.last endsWith ".properties" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith "Absent.class" => MergeStrategy.first
  case PathList(ps@_*) if ps.last endsWith ".xml" => MergeStrategy.first
  case PathList("com", "esotericsoftware", xs@_*) => MergeStrategy.first
  case x => {
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
  }
}
//定义jar包的名字
assemblyJarName in assembly := "sbt-package-assembly.jar"

//把scala本身排除在Jar中，因为spark已经包含了scala
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
