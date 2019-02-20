package com.ladder.example.sql

import org.apache.spark.sql.SparkSession

object SimpleExample {

		/**
			* 执行函数
			*
			* @param args 　参数列表
			*/
		def main(args: Array[String]) {
				// Should be some file on your system
				val logFile = "file:/data/service/spark/spark-2.4.0/README.md"
				// 创建Session
				val sparkSession = SparkSession.builder
					.appName("Simple Application")
					.master("local")
					.getOrCreate()
				// 读取数据入缓存
				val logData = sparkSession.read.textFile(logFile).cache()
				// 从缓存中读取数据，计算
				val numAs = logData.filter(line => line.contains("a")).count()
				val numBs = logData.filter(line => line.contains("b")).count()
				// 输出结果
				println(s"Lines with a: $numAs, Lines with b: $numBs")
				// 必须关闭session
				sparkSession.stop()
		}
}
