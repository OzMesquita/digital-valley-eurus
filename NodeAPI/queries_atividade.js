const db = require('./conexao')
const modelCreator = require('./model_creator')

function dataFormatada(){
  // teste = row.horario_previsto;  //2019-05-03T13:35:00.000Z

  var data = new Date(Date.now())
  console.log("data ",data);
  dia = data.getDate();
  mes = data.getMonth() +1; //+1 pois no getMonth Janeiro começa com zero.
  ano = data.getFullYear();
  hora = data.getHours();
  minutos = data.getMinutes();
  segundos = data.getSeconds();
  milisegundos = data.getMilliseconds();
  // correta =
  return ano+"-"+mes+"-"+dia+"T"+hora+":"+minutos+":"+segundos+"."+milisegundos+"-0300";
//2019-09-03T18:04:56.074Z
}

const getAtividades = (request, response) => {
  try {
    db.pool.query('SELECT * from  atividade as a join categoria as c on a.categoria_fk = c.id_categoria join local as l on a.local_fk=l.id_local join 	 trabalho as t on a.trabalho_fk=t.id_trabalho join usuario as u on a.apresentador_fk=u.id_usuario join sala on l.sala_fk=sala.id_sala order by horario_previsto asc'
    ,  (error, result) => {
      var atividades = [], index = {};

      result.rows.forEach(function (row) {
        if ( !(row.id_atividade in index) ) {
          index[row.id_atividade] = modelCreator.createAtividadeModel(row);
          atividades.push(index[row.id_atividade]);
        }
      });
      console.log(atividades);
      response.status(200).json(atividades);
    });
  } catch(ex){
    console.log('Erro ao listar atividades!');
    response.status(500).send(`Erro ao listar atividades`)
    return null;
  }
}

const getAtividadeById = (request, response) => {
  try {
    const id_ati = parseInt(request.params.id)
    //var atividade = {}

    db.pool.query('SELECT * from  atividade as a join categoria as c on a.categoria_fk = c.id_categoria join local as l on a.local_fk=l.id_local join 	 trabalho as t on a.trabalho_fk=t.id_trabalho join usuario as u on a.apresentador_fk=u.id_usuario join sala on l.sala_fk=sala.id_sala WHERE id_atividade = $1', [id_ati], (error, result) => {

      //console.log(result);
      result.rows.forEach(function (row) {
        //console.log("--------> row --- ",row);
        index = modelCreator.createAtividadeModel(row);
      });
      response.status(200).json(index);
    });
  } catch(ex){
    console.log('Erro ao listar atividade, pelo id!');
    response.status(500).send(`Erro ao listar atividade`)
    return null;
  }
}

const createAtividade = (request, response) => {
  try {
    const {horario_previsto, horario_inicial, horario_final, trabalho_fk, descricao, nome_atividade, categoria_fk, local_fk, apresentador_fk} = request.body

    db.pool.query('INSERT INTO atividade(horario_previsto,horario_inicial,horario_final,trabalho_fk,descricao,nome_atividade,categoria_fk,local_fk,apresentador_fk) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)', [horario_previsto, horario_inicial, horario_final, trabalho_fk, descricao, nome_atividade, categoria_fk, local_fk, apresentador_fk], (error, result) => {
      if(error == null){
          response.status(201).send(`Atividade adicionada: ${nome_atividade}`)
      }else{
        response.status(500).json('Erro ao adicionar atividade')
      }
    })
  } catch(ex){
    console.log('Erro ao criar atividade!');
    response.status(500).send(`Erro ao criar atividade`)
    return null;
  }
}

const updateAtividade = (request, response) => {
  try {
    const id_atividade = parseInt(request.params.id)
    const {isHorarioInicio, horario} = request.body
    console.log(request.body)
    var query = ""
    if(isHorarioInicio) {
      query = 'UPDATE atividade SET horario_inicial = now() WHERE id_atividade = $1'
    }else{
      query = 'UPDATE atividade SET horario_final = now() WHERE id_atividade = $1'
    }

    db.pool.query(
      query,
      [id_atividade],
      (error, results) => {
        console.log(error)
        response.status(200).send(true)
      }
    )
  } catch(ex){
    console.log('Erro ao atualizar atividade!');
    console.log(ex)
    response.status(500).send(`Erro ao atualizar atividade`)
    return null;
  }
}

const deleteAtividade = (request, response) => {
  try {
    const id_atividade = parseInt(request.params.id)
    db.pool.query('DELETE FROM atividade WHERE id_atividade = $1', [id_atividade], (error, results) => {
      if(error == null){
          response.status(200).send(`Atividade excluida ID: ${id_atividade}`)
      }else{
        response.status(500).json('Erro ao excluir atividade')
      }
    })
  } catch(ex){
    console.log('Erro ao excluir atividade!');
    response.status(500).send(`Erro ao excluir atividade`)
    return null;
  }
}

const getAtividadesHoje =(request, response) => {
  try {
    db.pool.query('SELECT * from  atividade as a join categoria as c on a.categoria_fk = c.id_categoria join local as l on a.local_fk=l.id_local join trabalho as t on a.trabalho_fk=t.id_trabalho join usuario as u on a.apresentador_fk=u.id_usuario join sala on l.sala_fk=sala.id_sala WHERE horario_previsto::date = CURRENT_DATE',  (error, result) => {
      var atividades = [];
      if (error) {
        throw error
      }
      result.rows.forEach(function (row) {
        atividades.push(modelCreator.createAtividadeModel(row));
      });
      response.status(200).json(atividades);
    });
  } catch(ex){
    console.log('Erro ao listar atividades de hoje!');
    response.status(500).send(`Erro ao listar atividades de hoje`)
    return null;
  }
}

const getAtividadesCoordenadorSala = (request, response) => {
  try {
    const id_usuario = parseInt(request.params.id)
    db.pool.query('SELECT * FROM atividade as a join local as l on a.local_fk=l.id_local join sala as s on l.sala_fk = s.id_sala join atividades_coordenador as ac on ac.sala_fk=s.id_sala join usuario as u on a.apresentador_fk=u.id_usuario WHERE ac.usuario_fk=$1',[id_usuario],
    (error, result) => {
      var atividadesCoordenador = []

      result.rows.forEach(function (row) {
        atividadesCoordenador.push(modelCreator.createAtividadeModel(row));
      });
      response.status(200).json(atividadesCoordenador);
    })
  } catch(ex){
    console.log('Erro ao listar coordenadores das salas!');
    response.status(500).send(`Erro ao listar coordenadores das salas`)
    return null;
  }
}

const getAtividadesProfessor = (request, response) => {
  try{
    const id_usuario = parseInt(request.params.id)
    db.pool.query('SELECT * FROM atividade as a join local as l on a.local_fk=l.id_local join sala as s on l.sala_fk = s.id_sala join atividades_professor as ap on ap.atividade_fk=a.id_atividade join usuario as u on a.apresentador_fk=u.id_usuario WHERE ap.usuario_fk=$1', [id_usuario],
      (error, result) => {
        if (error) {
          throw error
        }
        var avaliacoesProfessor = []
        result.rows.forEach(function (row) {
          avaliacoesProfessor.push(modelCreator.createAtividadeModel(row));
        });
        response.status(200).json(avaliacoesProfessor);
    })

  }catch(ex){
    console.log('Erro ao listar avaliações do professor!');
    response.status(500).send(`Erro ao listar avaliações do professor!`)
    return null;
  }
}

const getAtividadesFrequentadas = (request, response) => {

  const id_usuario = parseInt(request.params.id)
  db.pool.query('SELECT * FROM frequencia as f join sala as s on f.sala_fk=s.id_sala join local as l on l.sala_fk=s.id_sala join atividade as a on a.local_fk=l.id_local join usuario as u on u.id_usuario = a.apresentador_fk where a.horario_inicial >= f.check_in and a.horario_final <= f.check_out and f.usuario_fk = $1',[id_usuario],
  (error, result) => {

    var atividades = [], index = {};

    result.rows.forEach(function (row) {
      if ( !(row.id_atividade in index) ) {
        index[row.id_atividade] = modelCreator.createAtividadeModel(row);
        atividades.push(index[row.id_atividade]);
      }
    });

    response.status(200).json(atividades)
  }
  )
}
const getMomento = (request, response) =>{
  console.log("datafor ",dataFormatada())
  response.status(200).json(dataFormatada())
}

const cadastrarNotas = async (request, response, next) => {
  const {atividade,avaliador,notas} = request.body
  const queryResponse = {alreadyEvaluatedActivity: false,error: false, message: ''}
  var createError = null
  const pool = db.pool
  for(var i=0;i<notas.length;i++){
    const {error,results} = await pool.query('INSERT INTO nota_atividade(atividade_fk,avaliador_fk,criterio_fk,nota) VALUES($1,$2,$3,$4)',[atividade,avaliador,notas[i].criterio,notas[i].nota])
    if(error != null){
      createError = error
    }
  }
  console.log("ERRO PEGO")
  console.log(createError)
  if(createError == null) {
    console.log('PRINT')
    next()
  }else{
    db.pool.query('DELETE * FROM nota_atividade WHERE atividade_fk=$1 AND avaliador_fk=$2',[atividade,avaliador],(error,results) => {

    })
    queryResponse.error = true
    console.log(queryResponse)
    response.status(500).json(queryResponse)
  }
}

const cadastrarAvaliacao = (request, response) => {
  const {atividade,avaliador,comentarios,notas} = request.body
  const queryResponse = {alreadyEvaluatedActivity: false,error: false, message: ''}
  var totalNotas = 0.0;
  for(var i = 0;i<notas.length;i++) {
    totalNotas += notas[i].nota
  }
  var media = totalNotas / notas.length
  db.pool.query('INSERT INTO avaliacao_atividade(atividade_fk,avaliador_fk,media,comentario) VALUES($1,$2,$3,$4)',[atividade,avaliador,media.toFixed(2),comentarios],(error, results) => {
    console.log(error)
    if(error == null){
      queryResponse.message = "A avaliação foi feita com sucesso"
      console.log(queryResponse)
      response.status(201).json(queryResponse)
    }else{
      queryResponse.error = true
      console.log(queryResponse)
      response.status(201).json(queryResponse)
    }
  })
}

const verificarAtividadeAvaliada = (request, response, next) => {
  const {atividade,avaliador} = request.body
  const queryResponse = {alreadyEvaluatedActivity: false, error: false, message: ''}
  db.pool.query('SELECT * FROM avaliacao_atividade WHERE atividade_fk=$1 AND avaliador_fk=$2',[atividade,avaliador],(error,results) => {
    if(results.rowCount > 0){
      queryResponse.alreadyEvaluatedActivity = true;
      console.log(queryResponse)
      response.status(400).json(queryResponse)
    }else{
      next()
    }
  })
}

module.exports = {
  getAtividades,
  getAtividadeById,
  getAtividadesHoje,
  createAtividade,
  updateAtividade,
  deleteAtividade,
  getAtividadesCoordenadorSala,
  getAtividadesFrequentadas,
  getMomento,
  cadastrarNotas,
  cadastrarAvaliacao,
  verificarAtividadeAvaliada,
  getAtividadesProfessor
}
