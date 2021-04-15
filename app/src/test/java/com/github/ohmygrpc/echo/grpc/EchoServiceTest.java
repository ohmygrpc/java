package com.github.ohmygrpc.echo.grpc;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.ohmygrpc.idl.services.echo.v1.EchoRequest;
import com.github.ohmygrpc.idl.services.echo.v1.EchoServiceGrpc;
import com.github.ohmygrpc.idl.services.echo.v1.HealthCheckRequest;
import com.linecorp.armeria.client.Clients;
import com.linecorp.armeria.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class EchoServiceTest {

    private static Server server;

    @BeforeAll
    static void beforeClass() {
        server = Main.newServer(0, 0);
        server.start().join();
    }

    @AfterAll
    static void afterClass() {
        if (server != null) {
            server.stop().join();
        }
    }

    private static String uri() {
        return "gproto+http://127.0.0.1:" + server.activeLocalPort() + '/';
    }

    @Test
    void healthCheck() {
        final EchoServiceGrpc.EchoServiceBlockingStub echoService =
                Clients.newClient(uri(), EchoServiceGrpc.EchoServiceBlockingStub.class);
        assertThat(echoService.healthCheck(HealthCheckRequest.newBuilder().build())).isNotNull();
    }

    @Test
    void echo() {
        final EchoServiceGrpc.EchoServiceBlockingStub echoService =
                Clients.newClient(uri(), EchoServiceGrpc.EchoServiceBlockingStub.class);
        String requestMsg = "hello";
        String expectedResponseMsg = "hello";
        assertThat(echoService.echo(EchoRequest.newBuilder().setMsg(requestMsg).build()).getMsg())
                .isEqualTo(expectedResponseMsg);
    }
}
