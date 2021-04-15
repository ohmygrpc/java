ORG_NAME := ohmygrpc
SERVICE_NAME := java

.PHONY: help
## help: prints this help message
help:
	@echo "Usage: \n"
	@sed -n 's/^##//p' ${MAKEFILE_LIST} | column -t -s ':'

.PHONY: init-idl
## init-idl: initializes idl git submodule 
init-idl:
	git submodule update --init

.PHONY: update-idl
## init-idl: updates idl repo main branch
update-idl:
	cd idl && git pull origin main
	git submodule update --remote --merge
	cd grpcgateway && go get github.com/${ORG_NAME}/idl@main

.PHONY: build-grpcgateway
## build-grpcgateway: builds grpcgateway
build-grpcgateway:
	cd grpcgateway && \
	CGO_ENABLED=0 GOOS=linux GOARCH=amd64 go build -a -installsuffix cgo -ldflags="-w -s" -o bin/${SERVICE_NAME}-grpcgateway.linux.amd64 cmd/main.go && \
	CGO_ENABLED=0 GOOS=linux GOARCH=arm64 go build -a -installsuffix cgo -ldflags="-w -s" -o bin/${SERVICE_NAME}-grpcgateway.linux.arm64 cmd/main.go && \
	CGO_ENABLED=0 go build -a -installsuffix cgo -ldflags="-w -s" -o bin/${SERVICE_NAME}-grpcgateway cmd/main.go
