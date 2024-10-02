# Distributed Key-Value Store

A distributed key-value store built with Java, Maven, and Docker. This system supports multiple nodes, fault tolerance, replication, scalability, and performance monitoring. It efficiently handles key-value operations across distributed nodes, utilizing leader election and consistent hashing for optimized data storage and retrieval.


## Features

- **Leader Election**: Uses Raft-style leader election for managing write operations.
- **Replication and Consistency**: Data is replicated across nodes for fault tolerance, ensuring data remains consistent even during node failures.
- **Scalability**: The system dynamically scales by allowing nodes to be added or removed.  When new nodes are added, the system automatically rebalances the data using consistent hashing.
- **Latency and Throughput Monitoring**: The system includes tools for measuring the latency and throughput of key operations, enabling performance optimization.
- **Fault Tolerance**: The system is resilient to node failures. If a node goes down, the remaining nodes can still serve requests, ensuring continued operation.
- **Error Handling and Retries**: The system includes robust error handling and retry mechanisms. If a node is temporarily unavailable, the system retries the operation up to 3 times before failing. 



## Project Structure

For a detailed breakdown of the project structure, please refer to `PROJECT_STRUCTURE.md`.

## Installation

### Prerequisites

- **Java 17**: Required for compilation and runtime.
- **Maven**: Used to build the project and manage dependencies.
- **Docker**: Used to containerize the project and run it in isolated environments.

### Dependencies

This project uses **Maven** to manage external libraries and dependencies. All required libraries are specified in the `pom.xml` file, and Maven will automatically resolve and download these dependencies when building the project.

#### Key Dependencies:

- **gRPC**: Handles network communication between nodes.
- **log4j**: For logging and tracking events.
- **JUnit 5**: Used for writing and running tests.

For the full list of dependencies, refer to the `pom.xml` file in the project.

### Build the Project

1. Clone the repository:
    ```bash
    git clone https://github.com/abenez3r/distributed-kv-store.git
    ```

2. Navigate to the project directory:
    ```bash
    cd distributed-kv-store
    ```

3. Build the project using Maven:
    ```bash
    mvn clean package
    ```

4. Ensure that the JAR file is created in the `target/` directory:
    ```bash
    ls target/distributed-kv-store-1.0-SNAPSHOT.jar
    ```

### Running the Application Locally

To run the application locally, you can execute the following command:

```bash
java -jar target/distributed-kv-store-1.0-SNAPSHOT.jar
```

This will start the application and display output related to leader election, node operations, and heartbeats.

### Running with Docker

#### Docker Build

Build the Docker image:
```bash
docker build -t kvstore .
```
Verify the image is built:

```bash
docker images
```
#### Docker Run

To run the Docker container:
```bash
docker run -p 8080:8080 kvstore
```
This will start the application inside a Docker container, exposing it on port 8080. <br>

To verify that the container is running, you can use:
```bash
docker ps
```

To check the container logs:
```bash
docker logs <container_id>
```


## Testing

This project includes a range of unit and integration tests designed to verify the system's functionality and ensure it operates reliably under various scenarios.

### Run Tests

To run both unit and integration tests, use the following command:

```bash
mvn test
```

### Included Tests
- Consistency Test: Ensures data is replicated across nodes and remains consistent during node failure. See the implementation in `src/test/java/com/example/kvstore/ConsistencyTest.java`.

- Scalability Test: Verifies the system's ability to scale when nodes are added or removed. See the implementation in `src/test/java/com/example/kvstore/ScalabilityTest.java`.

- Latency Test: Measures the time taken for key operations like `put()` and `get()`. See the implementation in `src/test/java/com/example/kvstore/LatencyTest.java`.

- Fault Tolerance Test: Simulates node failures and checks if the system can continue operating without data loss. See the implementation in `src/test/java/com/example/kvstore/FaultToleranceTest.java`.
