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
import cucumber.api.java.pt.*;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author benoni
 */
public class ApiTesteStep {
    private static final String URL="http://localhost:8080";
    private Response response;
    private String email,password,authToken;
    
    @Dado("^que o usuario entra com login e password \"(.+)\"$")
    public void que_o_usuario_entra_com_email_e_password(String login){
        String [] email_pass = login.split(",");
        email = email_pass[0];
        password = email_pass[1];
    }
    
    @Dado("^que o admin entra com login '(.+)' e password '(.+)'")
    public void o_admin_entra_com_login_email_com_e_password(String email,String password) {
        this.email = email;
        this.password = password;
    }
    
    @Mas("^as credenciais são invalidas")
    public void as_credenciais_são_invalidas() throws Throwable {
        response.then().statusCode(401);
    }

    
    @Quando("^o usuario envia POST com dados para url \"(.+)\"$")
    public void o_usuario_envia_POST_com_dados_para_url(String url){
        Login login = new Login(email, password);
        
        response = given()
                .contentType("application/json")
                .body(login)
                .when().post(URL + url);//.then().extract().path("token");
        //authToken = login_response;
    }
    
    @Entao("^o recebe o token de autorização")
    public void usuario_recebe_token_autorizacao(){
        authToken = response.then().extract().path("token");
        Assert.assertEquals(true, !"".equals(authToken));
    }
    
    @E("^o usuario logado edita o problema de '(.+)' com nome '(.+)'")
    public void usuario_logado_acessa_problema_cod1(String cod,String nome){
       Problema prob = given().header(ApplicationConstant.TOKEN_HEADER,authToken)
              .when().get(URL+"/problema/"+cod).as(Problema.class);
       
       SumarioDeProblema s = prob.getSumario();
       s.setNome(nome);
       prob.setSumario(s);
       given()
                .contentType("application/json").header(ApplicationConstant.TOKEN_HEADER, authToken)
                .body(prob)
                .when().patch(URL + "/problema/"+cod);
    }


    @Quando("^Envio requisição GET para \"(.+)\"$")
    public void iSendAGetRequestTo(String uri) throws Exception {
       response = when().get(URL+uri);
    }
    
    @Entao("^o status deve ser (\\d+)$")
    public void theResponseStatusShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @E("^o tamanho da lista deve ser (\\d+)$")
    public void theLenResponseShouldBe(int expected) {
        response.then().body("get.size()", equalTo(5));
    }
    
    @E("^o resultado deve conter os codigos \"(.+)\"$")
    public void theJSONResponseShouldBe(String strCodigos) {
        String [] codigos = strCodigos.split(",");
        for(int i=0; i<codigos.length; i++){
            response.then().body(String.format("[%1d].codigo", i), equalTo(codigos[i]));
        }
    }
    
    @E("^o prolema deve ter nome igual a \"(.+)\"$")
    public void o_prolema_deve_ter_nome_igual_a(String nome) {
       response.then().body("sumario.nome", equalTo(nome));
    }
    
    @E("^o problema deve ter descricao igual a \"(.+)\"$")
    public void o_prolema_deve_ter_descricao_igual_a(String descricao) {
       response.then().body("sumario.descricao", equalTo(descricao));
    }
}