/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.problema;

import bootwildfly.domain.Problema;
import bootwildfly.domain.Resposta;
import bootwildfly.domain.SumarioDeProblema;
import bootwildfly.domain.Teste;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static io.restassured.parsing.Parser.JSON;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Before;

/**
 *
 * @author benoni
 */
public class ProblemasRouteTest {
    private final String URL="http://dirlididi-benonisilva.rhcloud.com";
    private Gson gson = new GsonBuilder().serializeNulls().create();
    
    private SumarioDeProblema s = new SumarioDeProblema("a", false, "c", "d", "e");
    private Teste t = new Teste("a", "b", "c", "d", "e", "f");
    private Problema p = new Problema(s);
    private List<Teste>  lt = new ArrayList<>();
    
    @Before
    public void createObj(){
        lt.add(t);
        p.setDica("dica");
        p.setTeste(lt);
        p.setId(1);
    }
    
    @Test
    public void get_problema_codigo5() throws Exception {
        with().expect().body("sumario.nome", equalTo("nome5")).when().get(URL+"/problema/codigo5");
    }
    
    @Test
    public void pagination_test_size_return() throws Exception {
        with().expect().body("get.size()", equalTo(5)).when().get(URL+"/problema");
    }
    
    @Test
    public void edit_problema_test_status_ok() throws Exception {
        given().
                params("nome", "nomenovo", "codigo", "codigo5","dataCriacao","30/07/16","resolvido",true).
                when().
                put(URL+"/problema/codigo5").then().statusCode(200);
    }
    
    @Test
    public void post_problema_test_status_ok() throws Exception {
        given().
                params("nome", "nomeadicionado", "codigo", "NULL", "dataCriacao", "30/07/16", "resolvido", false).
                when().
                post(URL + "/problema").then().statusCode(200);
    }
    
    @Test
    public void envia_solucao_test_status_ok() throws Exception {
        List<Resposta> respostas = new ArrayList<>();
        Resposta r = new Resposta();
        r.setEntrada("1");
        r.setSaida("2");
        respostas.add(r);
        respostas.add(r);
        given().
                params("estrategia", "Descricao de como resolveu o problema", "respostas",respostas).
                when().
                post(URL + "/problema/codigo5/solucao").then().statusCode(200);
    }
    
    @Test
    public void delete_problema_test_status_ok() throws Exception {
        expect().statusCode(200).when().delete(URL + "/problema/codigo5");
    }
}