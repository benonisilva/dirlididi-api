/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.service;

import bootwildfly.repository.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author benoni
 */
@Service
public class TesteService {
    private final TesteRepository testeRepository;

    @Autowired
    public TesteService(TesteRepository testeRepository) {
        this.testeRepository = testeRepository;
    }
    
}
