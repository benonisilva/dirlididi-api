package bootwildfly.controller;

import bootwildfly.domain.Problema;
import bootwildfly.domain.SumarioDeProblema;
import bootwildfly.domain.Teste;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProblemaController {

    @ApiOperation(value = "Lista Problemas", nickname = "Lista Problemas")
   
    @RequestMapping(value = "/problema", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pagina", value = "Pagina da lista de Problema", required = false, dataType = "string", paramType = "query", defaultValue="1")
      })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = SumarioDeProblema.class , responseContainer="List"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
   
    public @ResponseBody List<SumarioDeProblema> getProblemas(@RequestParam(value="pagina", defaultValue="string") String pagina){
        List<SumarioDeProblema> listaProblemas = new ArrayList<>();
        SumarioDeProblema sumarioDeProblema = 
                new SumarioDeProblema("sasa", true, "sasa", "sasa", "sasas");
        
        listaProblemas.add(sumarioDeProblema);
        listaProblemas.add(sumarioDeProblema);
        listaProblemas.add(sumarioDeProblema);
        
        return listaProblemas;
    }
    
    
    @ApiOperation(value = "Get Problema", nickname = "Get Problema")
   
    @RequestMapping(value = "/problema/{cod}", method = RequestMethod.GET)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "cod", value = "Problema com codigo={cod}", required = true, dataType = "string", paramType = "query", defaultValue="")
      })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success", response = Problema.class ),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
   
    public @ResponseBody Problema getProblema(@RequestParam(value="cod", defaultValue="string") String cod){
       
        SumarioDeProblema sumarioDeProblema = 
                new SumarioDeProblema("sasa", true, "sasa", "sasa", "sasas");
        Teste t = new Teste();
        t.setCodigo("de");
        t.setDica("dede");
        List<Teste> testes = new ArrayList<>();
        testes.add(t);
        testes.add(t);
        Problema retVal = new Problema(sumarioDeProblema);
        retVal.setDica("dica");
        retVal.setTeste(testes);
        
        return retVal;
    }
    
    @ApiOperation(value = "Edit Problema", nickname = "Edit Problema")

    @RequestMapping(value = "/problema/{cod}", method = RequestMethod.PUT)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "codProblema", value = "Problema com codigo={codProblema}", required = true, dataType = "string", paramType = "query", defaultValue = "")
    })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})

    public @ResponseBody
    String editProblema(@PathVariable String codProblema, @RequestBody Problema problema) {
        return "OK";
    }
}