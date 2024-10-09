package com.leeturner

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.junit5.WireMockExtension
import io.quarkus.test.junit.main.QuarkusMainLauncher
import io.quarkus.test.junit.main.QuarkusMainTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import org.wiremock.grpc.GrpcExtensionFactory
import org.wiremock.grpc.dsl.WireMockGrpc.message
import org.wiremock.grpc.dsl.WireMockGrpc.method
import org.wiremock.grpc.dsl.WireMockGrpcService

@QuarkusMainTest
class MarsRoversCliCommandTest {

  companion object {
    @RegisterExtension
    val wm: WireMockExtension = WireMockExtension.newInstance()
      .options(
        wireMockConfig()
          .port(9001)
          .withRootDirectory("src/test/resources/wiremock")
          .extensions(GrpcExtensionFactory())
      )
      .build()
  }

  private lateinit var mockMarsRoversService: WireMockGrpcService

  @BeforeEach
  fun setup() {
    mockMarsRoversService = WireMockGrpcService(
      WireMock(wm.port),
      "rovers.MarsRoverGrpc"
    )
  }

  @Test
  fun `mars rovers cli command returns all rovers`(launcher: QuarkusMainLauncher) {

    mockMarsRoversService.stubFor(
      method("rovers")
        .willReturn(
          message(
            MarsRoverResponse.newBuilder()
              .addRovers(MarsRover.newBuilder().setId(0).setName("Spirit - Test").build())
              .addRovers(MarsRover.newBuilder().setId(1).setName("Opportunity - Test").build())
              .addRovers(MarsRover.newBuilder().setId(2).setName("Curiosity - Test").build())
              .addRovers(MarsRover.newBuilder().setId(3).setName("Perseverance - Test").build())
              .addRovers(MarsRover.newBuilder().setId(4).setName("Sojourner - Test").build())
              .build()
          )
        )
    )

    val result = launcher.launch()
    assertTrue(result.output.contains("Mars Rovers:"))
    assertTrue(result.output.contains("0 - Spirit - Test"))
    assertTrue(result.output.contains("1 - Opportunity - Test"))
    assertTrue(result.output.contains("2 - Curiosity - Test"))
    assertTrue(result.output.contains("3 - Perseverance - Test"))
    assertTrue(result.output.contains("4 - Sojourner - Test"))
    
    mockMarsRoversService.verify("rovers")
  }

}