package server;

import greet.GreetServiceGrpc;
import greet.Sample;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class StreamServerTest {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        GreetServiceGrpc.GreetServiceBlockingStub stub
                = GreetServiceGrpc.newBlockingStub(channel);

        final Iterator<Sample.GreetManyTimesResponse> greetManyTimesResponseIterator = stub.greetManyTimes(Sample.GreetManyTimesRequest.newBuilder()
                .setGreeting(Sample.Greeting.newBuilder()
                        .setFirstName("Shinto")
                        .setLastName("Philip")
                        .build())
                .build());

        for (; greetManyTimesResponseIterator.hasNext(); ) {
            Sample.GreetManyTimesResponse response = greetManyTimesResponseIterator.next();
            System.out.println(response);
        }

        channel.shutdown();
    }
}
