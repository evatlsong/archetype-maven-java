package com.evatlsong.archetype.service;

import java.io.Serializable;

/**
 * Created by evatlsong on 15-11-21.
 */
public interface GenericService<T, ID extends Serializable> {
    T get(ID id);
    T save(T entity);
}
