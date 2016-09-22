/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.controller;

import bootwildfly.domain.Estatistica;
import bootwildfly.domain.SpringSecurityUser;
import bootwildfly.service.EstatisticaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    
    @Autowired
    private EstatisticaService estatisticaService;
    
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
        
        Long userContextId = null;
        //NOTA: para manter a coerencia na rest e nao usar /usuario/recurso  ou /anonimo/recurso usei essa estrategia... nao encontrei outra.
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userContextId = ((SpringSecurityUser) auth.getPrincipal()).getId();
        } catch (UsernameNotFoundException | ClassCastException e) {

        }
        
        int totalProblemas = estatisticaService.totalProblemas();
        int totalSolucionados = estatisticaService.totalSolucionados();
        int totalSolucionadosByUser = estatisticaService.totalByUsuarioId(userContextId);
        Estatistica estatistica = new Estatistica(totalSolucionados,totalProblemas, totalSolucionadosByUser);
        
        return estatistica;
    }
    
    @ApiOperation(value = "Estatisticas do sistema", nickname = "Estatisticas")
    @RequestMapping(value = "/anonimo/estatistica", method = RequestMethod.GET)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Estatistica.class),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")
    })
    
    public @ResponseBody Estatistica getEstatisticasAnonima(){
         
        int totalProblemas = estatisticaService.totalProblemas();
        int totalSolucionados = estatisticaService.totalSolucionados();

        Estatistica estatistica = new Estatistica(totalProblemas, totalSolucionados, 0);

        return estatistica;
    }
}
