package com.leeturner

import io.quarkus.grpc.GrpcClient
import picocli.CommandLine.Command
import java.time.Duration

@Command(name = "rovers", mixinStandardHelpOptions = true)
class MarsRoversCommand : Runnable {

  @GrpcClient
  lateinit var marsRovers: MarsRoverGrpc

  override fun run() {
    println("Mars Rovers:")
    println()

    val roversResponse =
      marsRovers.rovers(MarsRoverRequest.getDefaultInstance()).await().atMost(Duration.ofSeconds(5))
    roversResponse.roversList.forEach { rover -> (println("${rover.id} - ${rover.name}")) }
  }

}