const db = require('./conexao')
const modelCreator = require('./model_creator')

function dataFormatada(teste){
  // teste = row.horario_previsto;  //2019-05-03T13:35:00.000Z
  t = Date.parse(teste)
  var data = new Date(teste);
  dia = data.getDate();
  mes = data.getMonth() +1; //+1 pois no getMonth Janeiro começa com zero.
  ano = data.getFullYear();
  hora = data.getHours();
  minutos = data.getMinutes();
  segundos = data.getSeconds();
  // correta =

    return dia+"/"+mes+"/"+ano+" "+hora+":"+minutos+":"+segundos;

}

const getAtividades = (request, response) => {
  db.pool.query('SELECT * from  atividade as a join categoria as c on a.categoria_fk = c.id_categoria join local as l on a.local_fk=l.id_local join 	 trabalho as t on a.trabalho_fk=t.id_trabalho join usuario as u on a.apresentador_fk=u.id_usuario join sala on l.sala_fk=sala.id_sala order by horario_previsto asc'
  ,  (error, result) => {
    var atividades = [], index = {};
    if (error) {
      throw error
    }
    console.log(result);
    result.rows.forEach(function (row) {
      if ( !(row.id_atividade in index) ) {
        index[row.id_atividade] = modelCreator.createAtividadeModel(row);
        atividades.push(index[row.id_atividade]);
      }
    });
    console.log(atividades);
    response.status(200).json(atividades);
    });
}

const getAtividadeById = (request, response) => {
  const id_ati = parseInt(request.params.id)
  //var atividade = {}

  db.pool.query('SELECT * from  atividade as a join categoria as c on a.categoria_fk = c.id_categoria join local as l on a.local_fk=l.id_local join 	 trabalho as t on a.trabalho_fk=t.id_trabalho join usuario as u on a.apresentador_fk=u.id_usuario join sala on l.sala_fk=sala.id_sala WHERE id_atividade = $1', [id_ati], (error, result) => {
    if (error) {
      throw error
    }
    //console.log(result);
    result.rows.forEach(function (row) {
        //console.log("--------> row --- ",row);
        index = modelCreator.createAtividadeModel(row);
    });
    console.log("indexxxxxxxxxxxxx ",index);
    response.status(200).json(index);
    });
}

const createAtividade = (request, response) => {
  const {horario_previsto, horario_inicial, horario_final, trabalho_fk, descricao, nome_atividade, categoria_fk, local_fk, apresentador_fk} = request.body

  db.pool.query('INSERT INTO atividade VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)', [horario_previsto, horario_inicial, horario_final, trabalho_fk, descricao, nome_atividade, categoria_fk, local_fk, apresentador_fk], (error, result) => {
    if (error) {
      throw error
    }
    response.status(201).send(`Atividade adicionada: ${nome_atividade}`)
  })
}

const updateAtividade = (request, response) => {
  const id_atividade = parseInt(request.params.id)
  const atividade = request.body

  //const atividade2 = request.body
  const { nome_atividade, horario_previsto, horario_inicial, horario_final, descricao} = request.body
  console.log(atividade)

   db.pool.query(
     'UPDATE atividade SET nome_atividade = $1, horario_previsto = $2, horario_inicial = $3, horario_final = $4, descricao = $5  WHERE id_atividade = $6',
      [nome_atividade, horario_previsto, horario_inicial, horario_final, descricao, id_atividade],
     (error, results) => {
       if (error) {
         throw error
       }
       response.status(200).send(true)
     }
   )
}

const deleteAtividade = (request, response) => {
  const id_atividade = parseInt(request.params.id)
  db.pool.query('DELETE FROM atividade WHERE id_atividade = $1', [id_atividade], (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).send(`Atividade excluida ID: ${id_atividade}`)
  })
}

const getAtividadesHoje =(request, response) => {
  db.pool.query('SELECT * from  atividade as a join categoria as c on a.categoria_fk = c.id_categoria join local as l on a.local_fk=l.id_local join 	 trabalho as t on a.trabalho_fk=t.id_trabalho join usuario as u on a.apresentador_fk=u.id_usuario join sala on l.sala_fk=sala.id_sala WHERE horario_previsto::date = CURRENT_DATE',  (error, result) => {
    var atividades = [];
    if (error) {
      throw error
    }
    result.rows.forEach(function (row) {
      atividades.push(modelCreator.createAtividadeModel(row));
    });
    console.log(atividades);
    response.status(200).json(atividades);
  });
}

module.exports = {
  getAtividades,
  getAtividadeById,
  getAtividadesHoje,
  createAtividade,
  updateAtividade,
  deleteAtividade,

}
