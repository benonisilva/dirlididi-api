/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.controller;

import bootwildfly.ApplicationConstant;
import bootwildfly.domain.Estatistica;
import bootwildfly.domain.Usuario;
import bootwildfly.domain.entities.UsuarioEntity;
import bootwildfly.repository.UsuarioRepository;
import bootwildfly.service.AdminUserService;
import bootwildfly.service.ProblemaService;
import bootwildfly.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author benoni
 */
@RestController
public class AdminController {
    
    @Autowired
    private ProblemaService problemaService;
    
    @Autowired
    private AdminUserService userService;
    
    @ApiOperation(value = "Publica problema", nickname = "Publica" , authorizations = @Authorization("ADMIN"))
    @ApiImplicitParams({
        @ApiImplicitParam(name = "codigoProblema", value = "Codigo do problema. ", required = true, 
                dataType = "string", paramType = "path", defaultValue = "", access = "ADMIN")
    })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/admin/problema/{codigoProblema}", method = RequestMethod.PATCH , headers = ApplicationConstant.TOKEN_HEADER)
    public @ResponseBody String getInit(@PathVariable String codigoProblema){
       problemaService.publicarProblema(codigoProblema);
       return "OK";
    }
    
    @ApiOperation(value = "Adiciona Usuario", nickname = "Adiciona", authorizations = @Authorization("ADMIN"))
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Failure")})
    @RequestMapping(value = "/admin/usuario", method = RequestMethod.POST, headers = ApplicationConstant.TOKEN_HEADER)
    public @ResponseBody
    String getInit(@RequestBody Usuario usuario) {
        userService.saveUser(usuario);
        return "OK";
    }
    
}
