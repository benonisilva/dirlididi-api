/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.repository;

import bootwildfly.domain.entities.UsuarioEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author benoni
 */
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {
    
    UsuarioEntity findByEmail(String email);
    
    @Query(value = "select PROBLEMA_ID from USUARIO_SOLUC_PROBLEMA WHERE USUARIO_ID = ?1",nativeQuery = true)
    List<Long> findAllResolvidos(Long userId);
    
}
