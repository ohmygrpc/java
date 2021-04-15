package com.github.ohmygrpc.echo.grpc;

import com.github.ohmygrpc.idl.services.echo.v1.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class EchoServiceImpl extends EchoServiceGrpc.EchoServiceImplBase {

    @Override
    public void healthCheck(
            HealthCheckRequest request, StreamObserver<HealthCheckResponse> responseObserver) {
        HealthCheckResponse response = HealthCheckResponse.newBuilder().build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void echo(EchoRequest request, StreamObserver<EchoResponse> responseObserver) {
        try {
            EchoResponse response = EchoResponse.newBuilder().setMsg(request.getMsg()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(
                    Status.INTERNAL.withCause(e).withDescription(e.getMessage()).asException());
        }
    }
}
