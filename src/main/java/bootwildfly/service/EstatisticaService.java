/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.service;

import bootwildfly.repository.ProblemaRepository;
import bootwildfly.repository.SolucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author benoni
 */
@Service
public class EstatisticaService {
    
    private final ProblemaRepository problemaRepository;
    private final SolucaoRepository solucaoRepository;

    @Autowired
    public EstatisticaService(ProblemaRepository problemaRepository, SolucaoRepository solucaoRepository) {
        this.problemaRepository = problemaRepository;
        this.solucaoRepository = solucaoRepository;
    }
    
    public int totalProblemas(){
        return (int) problemaRepository.count();
    }
    
    public int totalSolucionados(){
        return (int) solucaoRepository.count();
    }
    
    public int totalByUsuarioId(Long userId){
        return solucaoRepository.findByUsuarioId(userId).size();
    }
    
    
}
