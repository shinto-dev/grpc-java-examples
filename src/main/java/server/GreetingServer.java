package server;


import greet.GreetServiceGrpc;
import greet.Sample;
import io.grpc.stub.StreamObserver;

import java.util.stream.IntStream;

public class GreetingServer extends GreetServiceGrpc.GreetServiceImplBase {
    @Override
    public void greet(Sample.GreetRequest request, StreamObserver<Sample.GreetResponse> responseObserver) {
        System.out.println("got request");
        responseObserver.onNext(Sample.GreetResponse.newBuilder()
                .setResult("hello from server")
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void greetManyTimes(Sample.GreetManyTimesRequest request, StreamObserver<Sample.GreetManyTimesResponse> responseObserver) {
        System.out.println("Request received");

        IntStream.range(0, 10).forEach(
                i -> responseObserver.onNext(Sample.GreetManyTimesResponse.newBuilder()
                        .setResult("hello from server" + i)
                        .build())
        );
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Sample.LongGreetRequest> longGreet(StreamObserver<Sample.LongGreetResponse> responseObserver) {
        return super.longGreet(responseObserver);
    }
}
