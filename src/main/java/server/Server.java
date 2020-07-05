package server;

import io.grpc.ServerBuilder;

import java.io.IOException;

public class Server {

    public void start() throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder
                .forPort(8080)
                .addService(new GreetingServer()).build();

        server.start();
        server.awaitTermination();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        server.start();
    }
}
