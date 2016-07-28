/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bootwildfly.service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 *
 * @author benoni
 * @param <T>
 */
public interface Repository<T> {

    Optional<T> get(String id);

    /**
     * return all entities that match the given predicate.
     */
    Set<T> get(Predicate<T> predicate);

    Set<T> get();

    void persist(T entity);

    void persist(T... entities);

    void persist(Collection<T> entities);

    void remove(T entity);

    void remove(T... entities);

    void remove(Collection<T> entities);

    /**
     * remove the entity with the given id.
     */
    void remove(String id);

    /**
     * remove all entities that match the given predicate.
     */
    void remove(Predicate<T> predicate);
}