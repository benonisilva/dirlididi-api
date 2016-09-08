package bootwildfly.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import bootwildfly.Application;
import bootwildfly.ApplicationConstant;
import bootwildfly.ProblemaJsonSerializer;
import bootwildfly.domain.Problema;
import bootwildfly.domain.SolucaoDeProblema;
import bootwildfly.domain.SpringSecurityUser;
import bootwildfly.domain.SumarioDeProblema;
import bootwildfly.domain.entities.ProblemaEntity;
import bootwildfly.service.ProblemaService;
import bootwildfly.service.SolucaoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemaController {
    
    @Autowired
    private ProblemaService problemaService;
    
    @Autowired
    private SolucaoService solucaoService;
    
    private static final int OFFSET=5;
    
//    @ExceptionHandler(IllegalArgumentException.class)
//    void handleBadRequests(HttpServletResponse response) throws IOException {
//        response.sendError(HttpStatus.BAD_REQUEST.value(), "Parametro invalido");
//    }
    
    @ApiOperation(value = "Lista Problemas", nickname = "Lista Problemas")
    @RequestMapping(value = "/problema/sumario", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pagina", value = "Pagina da lista de Problema", required = false, dataType = "string", paramType = "query", defaultValue="1")
      })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = SumarioDeProblema.class , responseContainer="List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public @ResponseBody List<SumarioDeProblema> getProblemas(@RequestParam(value="pagina", defaultValue = "0" ) Integer pagina){
        Long userContextId = ApplicationConstant.INVALID_ID;
        //NOTA: para manter a coerencia na rest e nao usar /usuario/recurso  ou /anonimo/recurso usei essa estrategia... nao encontrei outra.
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userContextId = ((SpringSecurityUser)auth.getPrincipal() ).getId();
        }
        catch (UsernameNotFoundException | ClassCastException e){
            
        }
        List<SumarioDeProblema> listaSumarioProblemas = problemaService.findAll(pagina,userContextId);        
        return listaSumarioProblemas;
    }
    
    
    @ApiOperation(value = "Get Problema", nickname = "Get Problema")
    @RequestMapping(value = "/problema/{codigoProblema}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "codigoProblema", value = "{codigoProblema}", required = true, dataType = "string", paramType = "path", defaultValue="")
      })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Problema.class  ),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public @ResponseBody Problema getProblema(@PathVariable(value="codigoProblema") String codigo){
        
        Long userContextId = ApplicationConstant.INVALID_ID;
        //NOTA: para manter a coerencia na rest e nao usar /usuario/recurso  ou /anonimo/recurso usei essa estrategia... nao encontrei outra.
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userContextId = ((SpringSecurityUser) auth.getPrincipal()).getId();
        } catch (UsernameNotFoundException | ClassCastException e) {

        }
        
        Problema problema = problemaService.findByCodigo(codigo,userContextId);
        
        return problema;
    }
    
    //NOTAS: Ter um recurso {teste} parece ser irrelevante dado que nas telas editamos {problemas}
    //       e nao ha nenhuma indicacao de que podemos editar um {teste} ou uma tela de submissão.
    @ApiOperation(value = "Edit Problema", nickname = "edit")
    @RequestMapping(value = "/problema/{codigoProblema}", method = RequestMethod.PATCH)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "codigoProblema", value = "Codigo do Problema", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public @ResponseBody
    boolean editProblema(@PathVariable String codigoProblema, @RequestBody Problema problema) {
        Long userContextId = null;
        //NOTA: para manter a coerencia na rest e nao usar /usuario/recurso  ou /anonimo/recurso usei essa estrategia... nao encontrei outra.
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userContextId = ((SpringSecurityUser) auth.getPrincipal()).getId();
        } catch (UsernameNotFoundException e) {

        }
        
        boolean sucesso = problemaService.updateProblema(codigoProblema,userContextId,problema);
        
        return sucesso;
    }
    
    @ApiOperation(value = "Delete Problema", nickname = "deletar")
    @RequestMapping(value = "/problema/{codigoProblema}", method = RequestMethod.DELETE)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "codigoProblema", value = "Codigo do problema", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public @ResponseBody
    String deleteProblema(@PathVariable String codigoProblema) {
        Long userContextId = null;
        //NOTA: para manter a coerencia na rest e nao usar /usuario/recurso  ou /anonimo/recurso usei essa estrategia... nao encontrei outra.
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userContextId = ((SpringSecurityUser) auth.getPrincipal()).getId();
        } catch (UsernameNotFoundException e) {

        }
        problemaService.deleteProblema(codigoProblema,userContextId);
        return "OK";
    }
    
    @ApiOperation(value = "Adiciona problema.", nickname = "insert")
    @RequestMapping(value = "/problema", method = RequestMethod.POST)
       @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public @ResponseBody
    String addProblema(@RequestBody Problema problema) {
        Long userContextId = null;
        //NOTA: para manter a coerencia na rest e nao usar /usuario/recurso  ou /anonimo/recurso usei essa estrategia... nao encontrei outra.
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userContextId = ((SpringSecurityUser) auth.getPrincipal()).getId();
        } catch (UsernameNotFoundException e) {

        }
        problemaService.saveProblema(problema, userContextId);
        return "OK";
    }
    
    @ApiOperation(value = "Adiciona uma solucao ao problema", nickname = "solucionar")
    @RequestMapping(value = "/problema/{codigoProblema}/solucao", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "codigoProblema", value = "Codigo do problema. ", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public @ResponseBody
    boolean checaSolucao(@PathVariable String codigoProblema, @RequestBody SolucaoDeProblema solucao) {
        Long userContextId = null;
        //NOTA: para manter a coerencia na rest e nao usar /usuario/recurso  ou /anonimo/recurso usei essa estrategia... nao encontrei outra.
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            userContextId = ((SpringSecurityUser) auth.getPrincipal()).getId();
        } catch (UsernameNotFoundException e) {

        }
        
        boolean isSolved = solucaoService.checaSolucao(solucao, userContextId,codigoProblema);
        
        return isSolved;
    }
}