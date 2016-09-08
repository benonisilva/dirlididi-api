/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.repository;

import bootwildfly.domain.entities.SolucaoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author benoni
 */
public interface SolucaoRepository extends CrudRepository<SolucaoEntity, Long>{
    @Query(value="SELECT * from SOLUCAO s WHERE USUARIO_SOLUCIONADOR_ID=?1 and PROBLEMA_SOLUCIONADO_ID=?2", nativeQuery = true)
    SolucaoEntity findByUsuarioIdProblemaId(Long userId,Long problemaId);
    
    @Query(value="SELECT * from SOLUCAO s WHERE USUARIO_SOLUCIONADOR_ID=?1", nativeQuery = true)
    List<SolucaoEntity> findByUsuarioId(Long userId);
}
