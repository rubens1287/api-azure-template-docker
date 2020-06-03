package services;

import core.*;
import core.azure.model.attachment.Attachment;
import lombok.extern.log4j.Log4j2;
import response.pojo.users.Users;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

@Log4j2
public class UsersService extends Spec implements TestingType {
    /**
     * Retorna lista de usuários
     *
     * @param data
     * @return
     */
    public Response getUsers(Map data) {

        String URI = "/users/";
        RequestSpecification httpRequest = given().spec(Spec.spec);
        Response response = httpRequest.get(URI + data.get("usuario_id"));
        return response;
    }

    @Override
    public boolean healthCheck(Response response, int statuCode) {
        log.info("Validação health check");
        return response.getStatusCode() == statuCode;
    }

    @Override
    public boolean verifyBody(Response response, HashMap data) {
        log.info("Validação dos dados do corpo da resposta");
        Gson gson = new Gson();
        Users users = gson.fromJson(response.jsonPath().prettyPrint(), Users.class);
        assertThat(users.getEmail()).isNotNull().isNotEmpty();
        assertThat(users.getName()).isEqualTo(data.get("nome"));
        assertThat(users.getId()).isGreaterThanOrEqualTo(1);
        return true;
    }

    @Override    public boolean verifySchema(Response response) {
        log.info("Validação do schema do corpo da resposta do json");
        assertThat(response.getBody().prettyPrint(), matchesJsonSchemaInClasspath("schemas/users/users-schema.json"));
        attachments.add(new Attachment(requestWriter.toString(),"Request"));
        attachments.add(new Attachment(responseWriter.toString(),"Response"));
        return true;
    }
}
