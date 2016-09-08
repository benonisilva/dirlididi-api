/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.service;

import bootwildfly.domain.Usuario;
import bootwildfly.domain.entities.UsuarioEntity;
import bootwildfly.repository.UsuarioRepository;
import javax.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author benoni
 */
@Service
public class AdminUserService {
    
    private final UsuarioRepository userRepository;

    @Autowired
    public AdminUserService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public boolean saveUser(Usuario usuarioDto) {
        UsuarioEntity usuarioEntity = usuarioDtoEntity(usuarioDto);
        userRepository.save(usuarioEntity);
        return true;
    }
    
    private UsuarioEntity usuarioDtoEntity(Usuario usuarioDto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setEmail(usuarioDto.getEmail());
        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
        String passEncoded = passEncoder.encode(usuarioDto.getSenha());
        entity.setSenha(passEncoded);
        entity.setRole("USER");
        return entity;
    }
}
