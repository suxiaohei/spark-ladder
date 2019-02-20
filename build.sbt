import Dependencies._
import sbtassembly.AssemblyPlugin.autoImport.assemblyOption

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

// 公共配置
val commonSettings = Seq(
  organization := "com.ladder",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.8"
)

// 公共的 打包 配置
val commonAssemblySettings = Seq(
  //解决依赖重复的问题
  assemblyMergeStrategy in assembly := {
    case PathList(ps@_*) if ps.last endsWith ".properties" => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith "Absent.class" => MergeStrategy.first
    case PathList(ps@_*) if ps.last endsWith ".xml" => MergeStrategy.first
    case PathList("com", "esotericsoftware", xs@_*) => MergeStrategy.first
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  },
  //执行assembly的时候忽略测试
  test in assembly := {},
  //把scala本身排除在Jar中，因为spark已经包含了scala
  assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
)

// 主工程
lazy val sparkLadder = (project in file("."))
  .aggregate(example, study)
  .settings(
    name := "sparkLadder"
  )

// 事例项目
lazy val example = (project in file("example"))
  .settings(
    libraryDependencies ++= sparkCommonProvidedDeps,
    libraryDependencies += scopt,
    libraryDependencies += scalatest)
  .settings(commonSettings)
  .settings(commonAssemblySettings)
  .settings(
    //指定类的名字
    mainClass in assembly := Some("com.ladder.example.hive.SparkHiveExample"),
    //定义jar包的名字
    assemblyJarName in assembly := "sbt-package-example.jar"
  )

// 事例项目
lazy val study = (project in file("study"))
  .settings(
    libraryDependencies ++= sparkCommonProvidedDeps,
    libraryDependencies += scopt,
    libraryDependencies += scalatest)
  .settings(commonSettings)
  .settings(commonAssemblySettings)
  .settings(
    //指定类的名字
    mainClass in assembly := Some("com.ladder.study.StudyMain"),
    //定义jar包的名字
    assemblyJarName in assembly := "sbt-package-study.jar"
  )


