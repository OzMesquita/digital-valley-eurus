package com.example.encontrosuniversitarios.model.dao.repositorio.database;

import android.content.Context;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;
import com.example.encontrosuniversitarios.model.dao.interfaces.database.DaoFactory;
import com.example.encontrosuniversitarios.model.dao.interfaces.room.IAtividadeRoomDao;
import com.example.encontrosuniversitarios.model.dao.repositorio.room.AtividadeRoomDaoAdapter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Atividade.class}, version = 1)
public abstract class EURoomDatabase extends RoomDatabase implements DaoFactory {

    private static EURoomDatabase instance;

    private static AtividadeRoomDaoAdapter atividadeImplementacaoRoom;

    private EURoomDatabase(){}

    public static synchronized EURoomDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EURoomDatabase.class,
                    "eu_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    @Override
    public IAtividadeBaseDao getAtividadeDao() {
        if(atividadeImplementacaoRoom == null){
            atividadeImplementacaoRoom = new AtividadeRoomDaoAdapter(this.getAtividadeRoomDao());
        }
        return atividadeImplementacaoRoom;
    }

    protected abstract IAtividadeRoomDao getAtividadeRoomDao();
}
