FROM golang:1.16.3 as build

ENV ORG_NAME=ohmygrpc
ENV SERVICE_NAME=java

ARG TARGETPLATFORM
ARG BUILDPLATFORM

WORKDIR /${SERVICE_NAME}-grpcgateway/bin
COPY ./bin ./

RUN if [ "$TARGETPLATFORM" = "linux/amd64" ]; then mv ${SERVICE_NAME}-grpcgateway.linux.amd64 ${SERVICE_NAME}-grpcgateway ; fi
RUN if [ "$TARGETPLATFORM" = "linux/arm64" ]; then mv ${SERVICE_NAME}-grpcgateway.linux.arm64 ${SERVICE_NAME}-grpcgateway ; fi


FROM --platform=$TARGETPLATFORM gcr.io/distroless/base

ENV ORG_NAME=ohmygrpc
ENV SERVICE_NAME=java

ARG TARGETPLATFORM
ARG BUILDPLATFORM

COPY --from=build /${SERVICE_NAME}-grpcgateway/bin/${SERVICE_NAME}-grpcgateway /app/${SERVICE_NAME}-grpcgateway
ENTRYPOINT ["app/${SERVICE_NAME}-grpcgateway"]
