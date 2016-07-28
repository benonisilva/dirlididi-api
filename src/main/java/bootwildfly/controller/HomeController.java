/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.controller;

import bootwildfly.domain.Estatistica;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author benoni
 */
@RestController
public class HomeController {
    
    @ApiOperation(value = "Estatisticas do sistema", nickname = "Estatisticas")
    @RequestMapping(value = "/estatistica", method = RequestMethod.GET)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Estatistica.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")
    })
    public @ResponseBody Estatistica getEstatisticas(){
        Estatistica estatistica = new Estatistica(88, 28, 0);
        return estatistica;
    }
}
