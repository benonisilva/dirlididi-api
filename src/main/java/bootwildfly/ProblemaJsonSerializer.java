/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly;

import bootwildfly.domain.Problema;
import bootwildfly.domain.SumarioDeProblema;
import bootwildfly.domain.Teste;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 *
 * @author benoni
 */
public class ProblemaJsonSerializer implements JsonSerializer<Problema>{

    @Override
    public JsonElement serialize(Problema p, Type type, JsonSerializationContext jsc) {
        //jsc.serialize()
        JsonObject object = new JsonObject();
        TypeToken<List<Teste>> typeTestes= new TypeToken<List<Teste>> () {};
        JsonElement sumario = jsc.serialize(p.getSumario(), SumarioDeProblema.class);
        JsonElement testes = jsc.serialize(p.getTeste());
        object.addProperty("id", p.getId());
        object.addProperty("dica", p.getDica());
        object.addProperty("isPublicado", p.isIsPublicado());
        object.addProperty("isPublicado", p.isIsPublicado());
        object.add("sumario", sumario);
        object.add("teste", testes);
        
        
        return object;
    }
    
}
