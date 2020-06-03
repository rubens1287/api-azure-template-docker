package core;

import io.restassured.response.Response;

import java.util.HashMap;

public interface TestingType {

    boolean healthCheck(Response response, int statuCode);

    boolean verifyBody(Response response, HashMap data);

    boolean verifySchema(Response response);
}
