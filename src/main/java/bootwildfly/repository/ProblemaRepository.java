/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.repository;

import bootwildfly.domain.entities.ProblemaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author benoni
 */
public interface ProblemaRepository extends CrudRepository<ProblemaEntity, Long>{
    
    Page<ProblemaEntity> findAll(Pageable pageable);
    ProblemaEntity findByCodigo(String codigo);
    
}
