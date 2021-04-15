FROM golang:1.16.3 as build

ENV SERVICE_NAME=grpc-java

ARG TARGETPLATFORM
ARG BUILDPLATFORM

WORKDIR /${SERVICE_NAME}-grpcgateway/bin
COPY ./bin ./

RUN if [ "$BUILDPLATFORM" = "linux/amd64" ]; then mv ${SERVICE_NAME}-grpcgateway.linux.amd64 ${SERVICE_NAME}-grpcgateway ; fi
RUN if [ "$BUILDPLATFORM" = "linux/arm64" ]; then mv ${SERVICE_NAME}-grpcgateway.linux.arm64 ${SERVICE_NAME}-grpcgateway ; fi


FROM --platform=$BUILDPLATFORM gcr.io/distroless/base

ENV SERVICE_NAME=grpc-java

ARG TARGETPLATFORM
ARG BUILDPLATFORM

COPY --from=build /${SERVICE_NAME}-grpcgateway/bin/${SERVICE_NAME}-grpcgateway /app/${SERVICE_NAME}-grpcgateway
ENTRYPOINT ["app/${SERVICE_NAME}-grpcgateway"]
