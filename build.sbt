
name := "spark-ladder"

version := "1.0"

scalaVersion := "2.11.8"

// cloudera 远程目录
externalResolvers ++= Seq(
		"spring" at "https://maven.aliyun.com/repository/spring",
		"spring-plugin" at "https://maven.aliyun.com/repository/spring-plugin",
		"cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
		"central" at "https://maven.aliyun.com/repository/central"
)

// 大数据相关
libraryDependencies += "org.apache.spark" %% "spark-graphx" % "2.4.0-cdh6.1.0"
libraryDependencies += "org.apache.spark" %% "spark-hive" % "2.4.0-cdh6.1.0"
libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.4.0-cdh6.1.0"
libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka-0-10" % "2.4.0-cdh6.1.0"
// scopt
libraryDependencies += "com.github.scopt" %% "scopt" % "3.7.0"
