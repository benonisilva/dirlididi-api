/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.problema;

import bootwildfly.ApplicationConstant;
import bootwildfly.ProblemaJsonSerializer;
import bootwildfly.service.*;
import bootwildfly.domain.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author benoni
 */
public class ProblemasRouteTest {
    private static final String URL="http://localhost:8080";
    private static final Gson gson = new GsonBuilder().serializeNulls().create();
    private static final String ADMIN_PASS = "123456";
    private static final String ADMIN_USER = "admin@admin.com";
    
    private static String authToken;
    private static String authJson;
    
    private  SumarioDeProblema sumario;
    private  Teste teste;
    private  Problema problema;
    private  List<Teste> listaTeste;
    private  Usuario  usuario;
    
    
    // we need to get the oauth token before we can perform the request
    private static void authenticateUser(String username, String password) {
        
        Login login = new Login(username, password);
        authJson = gson.toJson(login, Login.class);
        
        String response = given()
        .contentType("application/json")
        .body(authJson)
        .when().post(URL+"/auth").then().statusCode(200).extract().path("token");
        authToken = response;
    }
    
    @BeforeClass
    public static void createObj(){
        authenticateUser(ADMIN_USER,ADMIN_PASS);
    }
    
    @Test
    public void get_cod5_with_autorized_user() throws Exception {
        given().header(ApplicationConstant.TOKEN_HEADER,authToken).with().expect().body("sumario.nome", equalTo("nome5")).when().get(URL+"/problema/cod5");
    }
    
    @Test
    public void get_problema_cod5_without_autorized_user() throws Exception {
        given().header(ApplicationConstant.TOKEN_HEADER,"assas").with().expect().body("sumario.nome", equalTo("nome5")).when().get(URL+"/problema/cod5");
    }
    
    @Test
    public void pagination_test_size_return() throws Exception {
        with().expect().body("get.size()", equalTo(5)).when().get(URL+"/problema/sumario");
    }
    
    @Test
    public void edit_problema_test_status_ok_and_false_by_not_autorized() throws Exception {
        Problema response = given().header(ApplicationConstant.TOKEN_HEADER,authToken).
               with().expect().body("sumario.nome", equalTo("nome5")).
               when().get(URL+"/problema/cod5").as(Problema.class);
        
        response.setDica("edit_dica_5");
        String body = gson.toJson(response,Problema.class);
        
        given()
            .contentType("application/json").header(ApplicationConstant.TOKEN_HEADER,authToken)
            .body(body)
            .when().patch(URL + "/problema/cod5").then()
            .statusCode(200).body(equalTo("false"));
       
    }
    
    @Test
    public void edit_problema_test_status_ok_and_true() throws Exception {
        authenticateUser("user@user.com", ADMIN_PASS);
        Problema response = given().header(ApplicationConstant.TOKEN_HEADER, authToken).
                with().expect().body("sumario.nome", equalTo("nome1")).
                when().get(URL + "/problema/cod1").as(Problema.class);
        
        assertThat(response.getTeste().size(), equalTo(2));

        response.setDica("edit_dica_1");
                      Gson gson = new GsonBuilder().registerTypeAdapter(Problema.class, new ProblemaJsonSerializer())
                .create();
        String body = gson.toJson(response);

        given()
                .contentType("application/json").header(ApplicationConstant.TOKEN_HEADER, authToken)
                .body(body)
                .when().patch(URL + "/problema/cod1").then()
                .statusCode(200).body(equalTo("true"));

    }
//    
//    @Test
//    public void post_problema_test_status_ok() throws Exception {
//       String body = gson.toJson("",Problema.class);
//        
//       given()
//                .contentType("application/json")
//                .body(body)
//                .when().post(URL + "/problema").then()
//                .statusCode(200);
//        
//        with().expect().body("sumario.resolvido", equalTo(false)).when().get(URL+"/problema/codigo5");
//    }
//    
    @Test
    public void envia_solucao_test_status_ok_estatistica_5() throws Exception {
        authenticateUser("user@user.com", ADMIN_PASS);
        List<Resposta> respostas = new ArrayList<>();
        Resposta r = new Resposta();
        r.setEntrada("1");
        r.setSaida("2");
        respostas.add(r);
        respostas.add(r);
        SolucaoDeProblema so = new SolucaoDeProblema();
        so.setEstrategia("descricao");
        so.setRespostas(respostas);
        
        String body = gson.toJson(so,SolucaoDeProblema.class);
        
        given()
        .contentType("application/json").header(ApplicationConstant.TOKEN_HEADER, authToken)
        .body(body)
        .when().post(URL+"/problema/cod5/solucao").then()
        .statusCode(200).body(equalTo("true"));
    }
//    
//    @Test
//    public void delete_problema_test_status_ok() throws Exception {
//        expect().statusCode(200).when().delete(URL + "/problema/codigo5");
//        with().expect().body("numeroProblemasSubmetidos",equalTo(28)).when().get(URL+"/estatistica");
//    }
}