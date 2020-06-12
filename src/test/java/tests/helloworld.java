package tests;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import proto.GreeterGrpc;
import proto.HelloReply;
import proto.HelloRequest;

import java.util.logging.Logger;

import static utilities.Config.getValues;

@RunWith(SerenityRunner.class)
public class helloworld {
    private final static Logger LOGGER = Logger.getLogger(helloworld.class.getName());
    ManagedChannel channel;

    GreeterGrpc.GreeterBlockingStub client;
    HelloRequest request;
    HelloReply reply;

    String name;
    StatusRuntimeException e;

    @Before
    public void setup() {
        LOGGER.info("Connecting to HelloWorld Service Channel");
        channel = getValues("helloworld");
        client = GreeterGrpc.newBlockingStub(channel);
        LOGGER.info("Connected to HelloWorld Service Client");
    }

    @Test
    public void SayHello() {
        name = "World";

        request = HelloRequest.newBuilder()
                .setName(name)
                .build();
        LOGGER.info("HelloRequest is : \n" + request);

        reply = client.sayHello(request);
        LOGGER.info("HelloReply is : \n" + reply);
    }

    @After
    public void teardown() {
        channel.shutdown();
        LOGGER.info("Closed the HelloWorld Service Channel");
        Serenity.recordReportData().asEvidence().withTitle("HelloRequest").andContents(String.valueOf(request));
        Serenity.recordReportData().asEvidence().withTitle("HelloReply").andContents(String.valueOf(reply));
        Serenity.recordReportData().asEvidence().withTitle("StatusRuntimeException").andContents(String.valueOf(e));

    }
}
