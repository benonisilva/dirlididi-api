/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.service;

import bootwildfly.domain.Problema;
import bootwildfly.domain.SumarioDeProblema;
import bootwildfly.domain.Teste;
import bootwildfly.domain.TipoDeTeste;
import bootwildfly.domain.entities.ProblemaEntity;
import bootwildfly.domain.entities.SolucaoEntity;
import bootwildfly.domain.entities.TesteEntity;
import bootwildfly.domain.entities.UsuarioEntity;
import bootwildfly.repository.ProblemaRepository;
import bootwildfly.repository.SolucaoRepository;
import bootwildfly.repository.TesteRepository;
import bootwildfly.repository.UsuarioRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author benoni
 */
@Service
public class ProblemaService {
    
    private final ProblemaRepository problemaRepository;
    private final TesteRepository testeRepository;
    private final UsuarioRepository userRepository;
    private final SolucaoRepository solucaoRepository;
    
    @Autowired
    public ProblemaService(ProblemaRepository problemaRepository,UsuarioRepository userRepository,
            TesteRepository testeRepository,SolucaoRepository solucaoRepository) {
        this.problemaRepository = problemaRepository;
        this.testeRepository = testeRepository;
        this.userRepository = userRepository;
        this.solucaoRepository = solucaoRepository;
        
    }
    
    public void deleteProblema(String cod,Long userId){
       ProblemaEntity p = problemaRepository.findByCodigo(cod);
       if(p!=null){
           if(p.getUsuarioCriador().getId()==userId){
               problemaRepository.delete(p);
           }
       }
    }
    
    public void publicarProblema(String cod){
        ProblemaEntity p = problemaRepository.findByCodigo(cod);
        if(p!=null){ 
          p.setIsPublicado(true);
          problemaRepository.save(p);
        }
    } 
    
    @Transactional
    public void saveProblema(Problema problemaDto, Long userId){
        String cod = UUID.randomUUID().toString();
        problemaDto.getSumario().setCodigo(cod);
        problemaDto.setIsPublicado(false);
        ProblemaEntity problema = problemaDtoToEntity(problemaDto);
        UsuarioEntity usuario = userRepository.findOne(userId);
        problema.setUsuarioCriador(usuario);
        Date dt = new Date();
        problema.setDataCriacao(dt);
        
        ProblemaEntity problemaPersistido = problemaRepository.save(problema);
        //problemaRepository.
        List<TesteEntity> testesEntity = testesDtoToEntities(problemaDto.getTeste());
        testesEntity.forEach(k -> k.setProblema(problemaPersistido));
        testeRepository.save(testesEntity);
    }
    
    //NOTA: poderia separar a logica e deixar mais coeso 
    //saparando problema de teste mas pelo que vi nas telas
    //eles andam juntos
    @Transactional
    public boolean updateProblema(String cod,Long userId,Problema problemaDto){
        ProblemaEntity problema = problemaRepository.findByCodigo(cod);
        //construir excessoes apropriadas, 403, 404
        if(problema==null){
            return false;
        }else{
            if(problema.getUsuarioCriador().getId()!=userId){
                return false;
            }else{
                ProblemaEntity problamaEdit = problemaDtoToEntity(problemaDto);
                problamaEdit.setId(problema.getId());
                problamaEdit.setCodigo(cod);
                UsuarioEntity user = userRepository.findOne(userId);
                problamaEdit.setUsuarioCriador(user);
                problemaRepository.save(problamaEdit);
                List<TesteEntity> testesEntity = testesDtoToEntities(problemaDto.getTeste());
                testesEntity.forEach(k -> k.setProblema(problamaEdit));
                testeRepository.save(testesEntity);
                return true;
            }
        }
    }
    
    //automapper implementar...
    public List<SumarioDeProblema> findAll(int page,Long userId){
        
        Page<ProblemaEntity> listaProblema = problemaRepository.findAll(new PageRequest(page, 5));
        List<SumarioDeProblema> listaSumario = new ArrayList<>();
           
        for(ProblemaEntity p:listaProblema){
            
            SolucaoEntity solucao = solucaoRepository.findByUsuarioIdProblemaId(userId, p.getId());
            SumarioDeProblema s = new SumarioDeProblema();
            
            if(solucao==null){
              s.setResolvido(false);
            }else{
                s.setResolvido(true);
            }
            s.setCodigo(p.getCodigo());
            s.setDataCriacao(p.getDataCriacao().toString());
            s.setDescricao(p.getDescricao());
            s.setNome(p.getNome());    
                        
            listaSumario.add(s);
        }
        
        return listaSumario;       
    }
    
    public Problema findByCodigo(String codigo, Long userId) {
        
        ProblemaEntity problema= problemaRepository.findByCodigo(codigo);
        if(problema == null){
            return null;
        }
        Problema problemaDto = new Problema();
        
        problemaDto.setDica(problema.getDica());
        problemaDto.setIsPublicado(problema.isIsPublicado());
        problemaDto.setSumario(new SumarioDeProblema(
          problema.getDataCriacao().toString(),problema.getNome(),problema.getCodigo(),problema.getDescricao()
        ));
        List<Teste> listaTestesDto = new ArrayList<>();
        List<TesteEntity> testes = testeRepository.findByProblema(problema);
        boolean isCriador = problema.getUsuarioCriador().getId()==userId;
        for(TesteEntity t:testes){
            String entrada = isCriador ? t.getEntrada() : "";
            Teste tDto = new Teste("","",t.getDica(),"",entrada,t.getSaida(),
                                TipoDeTeste.valueOf(t.getTipo())
            );
            listaTestesDto.add(tDto);
        }
        problemaDto.setTeste(listaTestesDto);
        
        return problemaDto;
    }

    private ProblemaEntity problemaDtoToEntity(Problema problemaDto) {
        
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String s  = problemaDto.getSumario().getDataCriacao();
        Date dt;
        try {
            dt = df.parse(s);
        } catch (ParseException ex) {
            dt = new Date();
            Logger.getLogger(ProblemaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ProblemaEntity problemaEntity = 
                new ProblemaEntity(problemaDto.getSumario().getCodigo(),
                        problemaDto.getSumario().getNome(), 
                        problemaDto.getSumario().getDescricao(), 
                        dt, 
                        problemaDto.isIsPublicado(), problemaDto.getDica(), 
                        null);

        return problemaEntity;
    }

    private List<TesteEntity> testesDtoToEntities(List<Teste> testes) {
        List<TesteEntity> testesEntities = new ArrayList<>();
        for(Teste dto:testes){
            TesteEntity ent = new TesteEntity(
                    dto.getEntrada(), 
                    dto.getSaida(), 
                    dto.getDica(), 
                    dto.getTipo().name()
            );
            Long l = dto.getId() == null ? Long.MIN_VALUE : new Long(dto.getId());
            ent.setId(l);
            testesEntities.add(ent);
        }
        return testesEntities;
    }
    
}
