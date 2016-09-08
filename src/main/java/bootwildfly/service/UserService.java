/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.service;


import bootwildfly.domain.SpringSecurityUser;
import bootwildfly.domain.Usuario;
import bootwildfly.domain.entities.UsuarioEntity;
import bootwildfly.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author benoni
 */
@Service
@Qualifier("userDetailsService")
public class UserService implements UserDetailsService {
    
    @Autowired
    private UsuarioRepository userRepository;
    
    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioEntity userEntity = userRepository.findByEmail(email);
        
        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("No appUser found with username '%s'.", email));
        } else {
            return new SpringSecurityUser(
                    userEntity.getId(),
                    userEntity.getEmail(),
                    userEntity.getSenha(),
                    userEntity.getEmail(),
                    null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(userEntity.getRole())
            );
        }
    }
    
    
    private User buildUserForAuthentication(UsuarioEntity user,
            List<GrantedAuthority> authorities) {
        
        return new User(user.getEmail(), user.getSenha(),
                true, true, true, true, authorities);
    }   
    
}
