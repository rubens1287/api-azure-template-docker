package steps;

import core.Spec;
import core.azure.model.attachment.Attachment;
import core.data.DataYaml;
import org.junit.Assert;
import services.UsersService;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.response.Response;

import java.util.HashMap;

public class UsersSteps {

    private Response response;
    private UsersService usersService;
    private HashMap data = DataYaml.getMapYamlValues("Usuarios","usuarios");

    @Quando("^eu consultar um usuario$")
    public void euConsultarUmUsuario()  {
        usersService = new UsersService();
        response = usersService.getUsers(data);
    }

    @Entao("^sera apresentado todos os dados deste usuario$")
    public void seraApresentadoTodosOsDadosDesteUsuario()  {
        Assert.assertTrue(usersService.healthCheck(response,200));
        usersService.verifyBody(response,data);
        usersService.verifySchema(response);
    }
}
