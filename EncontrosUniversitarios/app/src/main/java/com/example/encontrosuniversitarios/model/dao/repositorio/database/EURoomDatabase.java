package com.example.encontrosuniversitarios.model.dao.repositorio.database;

import android.content.Context;

import com.example.encontrosuniversitarios.model.Atividade;
import com.example.encontrosuniversitarios.model.dao.entidade.AtividadeEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.AtividadeFrequenciaEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.AtividadesLocalEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.CategoriaEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.CoAutorEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.FrequenciaEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.LocalEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.SalaEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.TrabalhoCoAutoresEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.TrabalhoEntidade;
import com.example.encontrosuniversitarios.model.dao.entidade.UsuarioEntidade;
import com.example.encontrosuniversitarios.model.dao.interfaces.base.IAtividadeBaseDao;
import com.example.encontrosuniversitarios.model.dao.interfaces.database.IDaoFactory;
import com.example.encontrosuniversitarios.model.dao.interfaces.room.IAtividadeRoomDao;
import com.example.encontrosuniversitarios.model.dao.repositorio.room.AtividadeRoomDaoAdapter;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class EURoomDatabase extends RoomDatabase implements IDaoFactory {

    private static EURoomDatabase instance;

    private static AtividadeRoomDaoAdapter atividadeImplementacaoRoom;

    private EURoomDatabase(){}

    protected static synchronized EURoomDatabase getInstance(Context context) {
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
