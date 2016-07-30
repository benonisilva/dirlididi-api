/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.service;


import bootwildfly.domain.Problema;
import bootwildfly.domain.SumarioDeProblema;
import bootwildfly.domain.Teste;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;


/**
 *
 * @author benoni
 */
@Component("repositoryProblema")
public class MockListaProblema implements Repository<Problema>{
    //usado para testes
    private final static int TAMANHO=10;
    private final List<Problema> problemasMock = new ArrayList<>();

    public MockListaProblema() {
        gerarProblemas();
    }

    public List<Problema> getProblemasMock() {
        return problemasMock;
    }
    
    private void gerarProblemas(){
        for(int i=0;i<TAMANHO; i++){
            SumarioDeProblema s = 
                    new SumarioDeProblema();
            s.setCodigo("codigo"+i);
            s.setDataCriacao("21/07/17");
            s.setResolvido(false);
            s.setNome("nome"+i);
            s.setDescricao("descricao"+i);
            Teste t = new Teste();
            t.setCodigo("codigo"+i);
            t.setNome("nome"+i);
            t.setDescricao("dscricao"+i);
            t.setDica("dica"+i);
            t.setEntrada(""+i);
            t.setSaida(""+i);
            List<Teste> lt = new ArrayList<>();
            lt.add(t);
            Problema p = new Problema();
            p.setSumario(s);
            p.setTeste(lt);
            p.setId(i);
            problemasMock.add(p);
        }
                
    }    

    @Override
    public Optional<Problema> get(String id) {
        Optional<Problema> op = Optional.empty();
        for(Problema p:problemasMock){
            
            if(p.getSumario().getCodigo().equals(id)){
                op = Optional.ofNullable(p);
            }
        }
        return op;
    }

    @Override
    public Set<Problema> get(Predicate<Problema> predicate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Problema> get() {
        Set<Problema> retVal = new HashSet<>(problemasMock);
        return retVal;
    }

    @Override
    public void persist(Problema entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persist(Problema... entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persist(Collection<Problema> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Problema entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Problema... entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Collection<Problema> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Predicate<Problema> predicate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
}
