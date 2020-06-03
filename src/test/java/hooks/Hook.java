package hooks;

import core.Spec;
import core.azure.controller.RunTestController;
import core.azure.model.attachment.Attachment;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.deps.net.iharder.Base64;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.output.WriterOutputStream;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;

@Log4j2
public class Hook extends Spec {


    @Before
    public void init(Scenario scenario) {

        requestWriter = new StringWriter();
        requestCapture = new PrintStream(new WriterOutputStream(requestWriter),true);
        responseWriter = new StringWriter();
        responseCapture = new PrintStream(new WriterOutputStream(responseWriter),true);

        log.info(String.format("TESTE INICIADO: %s",scenario.getName()));
        log.info("Construindo objeto SPEC com as definições globais de requisição");
        attachments = new ArrayList<>();
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(System.getProperty("baseurl"))
                .addFilter(new RequestLoggingFilter(requestCapture))
                .addFilter(new ResponseLoggingFilter(responseCapture))
                .build();
    }

    @After
    public void end(Scenario scenario){
        if (scenario.isFailed()) {
            attachments.add(new Attachment(requestWriter.toString()));
            attachments.add(new Attachment(responseWriter.toString()));
        }
        RunTestController runTestController = new RunTestController();
        runTestController.runTestCase(scenario);
        log.info(String.format("TESTE FINALIZADO: %s",scenario.getName()));
        log.info(String.format("TESTE STATUS: %s",scenario.getStatus()));
    }
}









