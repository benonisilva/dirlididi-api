/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.service;

import bootwildfly.domain.Resposta;
import bootwildfly.domain.SolucaoDeProblema;
import bootwildfly.domain.entities.ProblemaEntity;
import bootwildfly.domain.entities.SolucaoEntity;
import bootwildfly.domain.entities.TesteEntity;
import bootwildfly.domain.entities.UsuarioEntity;
import org.springframework.stereotype.Service;

import bootwildfly.repository.*;
import com.google.common.base.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author benoni
 */
@Service
public class SolucaoService {
    
   private final ProblemaRepository problemaRepository;
   private final SolucaoRepository solucaoRepository;
   private final TesteRepository testeRepository;
   private final UsuarioRepository usuarioRepository;

   @Autowired
   public SolucaoService(ProblemaRepository problemaRepository, 
           SolucaoRepository solucaoRepository, TesteRepository testeRepository, UsuarioRepository usuarioRepository) {
        
        this.problemaRepository = problemaRepository;
        this.solucaoRepository = solucaoRepository;
        this.testeRepository = testeRepository;
        this.usuarioRepository = usuarioRepository;
    }
   
   public boolean checaSolucao(SolucaoDeProblema solucao,Long UserId, String cod){
       ProblemaEntity problema = problemaRepository.findByCodigo(cod);
       List<TesteEntity> testes = testeRepository.findByProblema(problema);
       List<TesteEntity> testesErrados = checaSolucao(solucao.getResposta(),testes);
       boolean isSolved = testesErrados.isEmpty();
       if(isSolved) {
           UsuarioEntity u = usuarioRepository.findOne(UserId);
           SolucaoEntity s = new SolucaoEntity();
           s.setDescricao(solucao.getEstrategia());
           s.setProblemaSolucionado(problema);
           s.setUsuarioSolucionador(u);
           solucaoRepository.save(s);
       }
       return isSolved;
   }

    private List<TesteEntity> checaSolucao(List<Resposta> respostas, List<TesteEntity> testes) {
        List<TesteEntity> testesErrados = new ArrayList<>();
        //TesteEntityPredicates pred = new TesteEntityPredicates();
        for(Resposta r:respostas){
           TesteEntity te = testes.stream().filter((t) -> t.getEntrada().equalsIgnoreCase(r.getEntrada()) ).findFirst().get();
           if(!te.getSaida().equalsIgnoreCase(r.getSaida()))  testesErrados.add(te);
        }
        
        return testesErrados;
    }
    
    public class TesteEntityPredicates {
        public Predicate<TesteEntity> equalsEntrada(String entrada) {
            return t -> t.getEntrada().equalsIgnoreCase(entrada);
        }
    }
}
