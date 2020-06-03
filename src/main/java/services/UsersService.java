package services;

import core.*;
import lombok.extern.log4j.Log4j2;
import request.pojo.PojoExemplo;
import response.pojo.users.Users;
import com.google.gson.Gson;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;


public class UsersService implements TestingType {
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
        //ReportType.reportToAllureUriRequest(URI);
        return response;
    }

    @Override
    public boolean healthCheck(Response response, int statuCode) {
        //ReportType.reportToAllureHeaderAndBodyResponse(response);
        return response.getStatusCode() == statuCode;
    }

    @Override
    public boolean verifyBody(Response response, HashMap data) {
        Gson gson = new Gson();
        Users users = gson.fromJson(response.jsonPath().prettyPrint(), Users.class);
        assertThat(users.getEmail()).isNotNull().isNotEmpty();
        assertThat(users.getName()).isEqualTo(data.get("nome"));
        assertThat(users.getId()).isGreaterThanOrEqualTo(1);
        return true;
    }

    @Override
    public boolean verifySchema(Response response) {
        assertThat(response.getBody().prettyPrint(), matchesJsonSchemaInClasspath("schemas/users/users-schema.json"));
        return true;
    }
}
