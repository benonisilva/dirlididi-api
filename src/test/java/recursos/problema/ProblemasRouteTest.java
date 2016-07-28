/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos.problema;

import groovy.json.JsonException;
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

/**
 *
 * @author benoni
 */
public class ProblemasRouteTest {
    @Test
    public void parameterSupportWithMapBuilder() throws Exception {
        with().expect().body("dica", equalTo("dica")).when().get("/problema/xpto");
    }
}