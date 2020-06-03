package hooks;

import core.Spec;
import core.azure.controller.RunTestController;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;

@Log4j2
public class Hook extends Spec {

    @Before
    public void init(Scenario scenario) {
        log.info(String.format("TESTE INICIADO: %s",scenario.getName()));
        log.info("Construindo objeto SPEC com as definições globais de requisição");
        attachments = new ArrayList<>();
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(System.getProperty("baseurl"))
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @After
    public void end(Scenario scenario){
        RunTestController runTestController = new RunTestController();
        runTestController.runTestCase(scenario);
        log.info(String.format("TESTE FINALIZADO: %s",scenario.getName()));
        log.info(String.format("TESTE STATUS: %s",scenario.getStatus()));
    }
}









