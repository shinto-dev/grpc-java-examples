package server;

import greet.GreetServiceGrpc;
import greet.Sample;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class UnaryCallTest {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        GreetServiceGrpc.GreetServiceBlockingStub stub
                = GreetServiceGrpc.newBlockingStub(channel);

        Sample.GreetResponse greetResponse = stub.greet(Sample.GreetRequest.newBuilder()
                .setGreeting(Sample.Greeting.newBuilder()
                        .setFirstName("Shinto")
                        .setLastName("Philip")
                        .build())
                .build());

        System.out.println(greetResponse);
        channel.shutdown();
    }
}
