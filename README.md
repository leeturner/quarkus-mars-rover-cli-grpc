# Quarkus Mars Rover gRPC CLI

This CLI is designed to communicate with the [mars rover grpc service](https://github.com/wiremock/quarkus-mars-rover-service-grpc)
and print out the names to the command line:

```
Mars Rovers:

0 - Spirit
1 - Opportunity
2 - Curiosity
3 - Perseverance
4 - Sojourner
```

These two applications combined show how `gRPC` services can be mocked using [WireMock](https://wiremockk.org) and 
the [WireMock gRPC Extension](https://github.com/wiremock/wiremock-grpc-extension).  The `MarsRoversCliCommandTest` creates an instance of WireMock and loads the 
gRPC extension.  It then mocks the `rovers.MarsRoverGrpc` service and defines the payload to return. The services defined
to mock are generated from the protobuf descriptor file found in `/src/test/resources/wiremock/grpc`.  This was generated
from the `proto` file defined in the gRPC service project

A list of rover names will be returned along with a corresponding index:

```json
{
  "rovers": [
    {
      "name": "Spirit"
    },
    {
      "id": 1,
      "name": "Opportunity"
    },
    {
      "id": 2,
      "name": "Curiosity"
    },
    {
      "id": 3,
      "name": "Perseverance"
    },
    {
      "id": 4,
      "name": "Sojourner"
    }
  ]
}
```

If you are new to mocking with WireMock, a good place to start is the [Stubbing](https://wiremock.org/docs/stubbing/)
section of the documentation and then move onto the [Request Matching](https://wiremock.org/docs/request-matching/) and
[Response Templating](https://wiremock.org/docs/response-templating/) sections. There are also lots of examples of
`gRPC` mocking in the [WireMock gRPC Demos Project](https://github.com/wiremock/wiremock-grpc-demos)

## WireMock Cloud
[WireMock Cloud](https://www.wiremock.io/?utm_source=github&utm_medium=referral&utm_campaign=quarkus-insights&utm_term=quarkus-mars-rover-cli-grpc)
is a managed, hosted version of WireMock, developed by the same team who wrote the open-source project. It's built on
the same technology that powers open source WireMock and is 100% compatible with the WireMock API, with additional
features that make it quick and easy to mock any API you depend on. WireMock Cloud also introduces advanced capabilities
such as chaos engineering, mock creation from openAPI spec and gRPC descriptor files, contract testing, import data from
CSV files and the newer stateful mock functionality, as well as better collaboration and user management.  WireMock Cloud
has a free forever plan so take a look and see how WireMock Cloud can fit into your SDLC.

## Quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.
