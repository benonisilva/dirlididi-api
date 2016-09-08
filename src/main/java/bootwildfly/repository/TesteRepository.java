/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.repository;

import bootwildfly.domain.entities.TesteEntity;
import bootwildfly.domain.entities.ProblemaEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author benoni
 */
public interface TesteRepository extends CrudRepository<TesteEntity, Long>{
    List<TesteEntity> findByProblema(ProblemaEntity problema);
}