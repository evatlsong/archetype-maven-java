package com.evatlsong.archetype.service.impl;

import com.evatlsong.archetype.dao.GenericDAO;
import com.evatlsong.archetype.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Created by evatlsong on 15-11-21.
 */
@Transactional
public abstract class GenericServiceImpl<T, ID extends Serializable> implements GenericService<T, ID> {
    @Autowired
    private GenericDAO genericDAO;

    @Override
    public T get(ID id) {
        return (T) genericDAO.findById(id, true);
    }

    @Override
    public T save(T entity) {
        return (T) genericDAO.makePersistent(entity);
    }
}
