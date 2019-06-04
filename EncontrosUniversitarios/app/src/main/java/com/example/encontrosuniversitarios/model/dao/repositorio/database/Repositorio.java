package com.example.encontrosuniversitarios.model.dao.repositorio.database;

import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;
import com.example.encontrosuniversitarios.model.dao.interfaces.database.IDaoFactory;

public class Repositorio implements IDaoFactory {

    @Override
    public IAtividadeBaseDao getAtividadeDao() {
        return null;
    }
}
