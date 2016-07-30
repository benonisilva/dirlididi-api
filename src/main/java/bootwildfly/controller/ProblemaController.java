package bootwildfly.controller;

import bootwildfly.domain.Problema;
import bootwildfly.domain.SolucaoDeProblema;
import bootwildfly.domain.SumarioDeProblema;
import bootwildfly.service.MockListaProblema;
import bootwildfly.service.Repository;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private Repository<Problema> repositoryProblema;
    
    private static final int OFFSET=5;
    
    @ExceptionHandler(IllegalArgumentException.class)
    void handleBadRequests(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Parametro invalido");
    }
    
    @ApiOperation(value = "Lista Problemas", nickname = "Lista Problemas")
    @RequestMapping(value = "/problema", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pagina", value = "Pagina da lista de Problema", required = false, dataType = "string", paramType = "query", defaultValue="1")
      })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = SumarioDeProblema.class , responseContainer="List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public @ResponseBody List<SumarioDeProblema> getProblemas(@RequestParam(value="pagina", defaultValue = "1" ) Integer pagina){
        
        List<SumarioDeProblema> listaSumarioProblemas = new ArrayList<>();
        
        int inicio,fim;
        inicio=(pagina-1)*OFFSET;
        fim=inicio+OFFSET;
        int total=repositoryProblema.get().size();
        
        if(fim>total) throw new IllegalArgumentException("fora "+inicio+"  "+fim+" "+total );
        
        List<Problema> l = new ArrayList<>();
        l.addAll(repositoryProblema.get());
        l.subList(inicio,fim).forEach((Problema p)->{
            listaSumarioProblemas.add(p.getSumario());
        });
        
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
        
        Problema problema = repositoryProblema.get(codigo).get();
        return problema;
    }
    
    //NOTAS: Ter um recurso {teste} parece ser irrelevante dado que nas telas editamos {problemas}
    //       e nao ha nenhuma indicacao de que podemos editar um {teste} ou uma tela de submissão.
    @ApiOperation(value = "Edit Problema", nickname = "edit")
    @RequestMapping(value = "/problema/{codigoProblema}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "codigoProblema", value = "Codigo do Problema", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    public @ResponseBody
    String editProblema(@PathVariable String codigoProblema, @RequestBody Problema problema) {
        return "OK";
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
    String deleteProblema(@PathVariable String codigoProblema, @RequestBody Problema problema) {
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
        return true;
    }
}