package com.example.encontrosuniversitarios.model.dao.interfaces.database;

import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;

public interface IDaoFactory {
    public IAtividadeBaseDao getAtividadeDao();
}
