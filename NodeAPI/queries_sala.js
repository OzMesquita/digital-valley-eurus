const db = require('./conexao')

const getSalas = (request, response) => {
  try {
    db.pool.query('SELECT * FROM '+db.db_name+'sala ORDER BY id_sala ASC', (error, results) => {
      if (results!= null) {
        response.status(200).json(results.rows)
      }else{
          response.status(200).json([])
      }

    })
  } catch(ex){
    console.log('Erro ao listar salas!');
    response.status(500).send(`Erro ao listar salas`)
    return null;
  }
}

const getSalaById = (request, response) => {
  try {
    const id_sala = parseInt(request.params.id)

    db.pool.query('SELECT * FROM '+db.db_name+'sala WHERE id_sala = $1', [id_sala], (error, results) => {

      if(id_sala!=null){
        response.status(200).json(results.rows)
      }else{
      }
    })
  } catch(ex){
    console.log('Erro ao atualizar sala!');
    response.status(500).send(`Erro ao atualizar sala`)
    return null;
  }
}

const createSala = (request, response) => {
  try {
    const {nome_sala, numeros } = request.body

    db.pool.query('INSERT INTO '+db.db_name+'sala (nome_sala, numero) VALUES ($1, $2)', [nome_sala, numero], (error, result) => {
      response.status(201).send(`Sala adicionada: ${nome_sala}`)
    })

  } catch(ex){
    console.log('Erro ao cadastrar sala!');
    response.status(500).send(`Erro ao cadastrar sala`)
    return null;
  }
}

const cadastrarCoordenadorNaSala = (request, response) => {
  try {
    const {id_usuario, id_sala} = request.body

    db.pool.query('INSERT INTO '+db.db_name+'atividades_coordenador (sala_fk, usuario_fk) VALUES ($1, $2)', [id_sala,id_usuario], (error, result) => {
      response.status(201).send('Coordenador cadastrado na sala com sucesso')
    })
  } catch(ex){
    console.log('Erro ao cadastrar Coordenador na Sala!');
    response.status(500).send(`Erro ao cadastrar Coordenador na Sala`)
    return null;
  }
}

const updateSala = (request, response) => {
  try {
    const id_sala = parseInt(request.params.id)
    const {nome_sala, numero_sala} = request.body
    db.pool.query(
      'UPDATE '+db.db_name+'sala SET nome_sala= $1, numero_sala = $2 WHERE id_sala = $3',
      [nome_sala, numero_sala, id_sala],
      (error, results) => {
        response.status(200).send(`Sala modificada ID: ${id_sala}`)
      }
    )
  } catch(ex){
    console.log('Erro ao atualizar sala!');
    response.status(500).send(`Erro ao atualizar sala`)
    return null;
  }
}

const deleteSala = (request, response) => {
  try {
    const id_sala = parseInt(request.params.id)

    db.pool.query('DELETE FROM '+db.db_name+'sala WHERE id_sala = $1', [id_sala], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`Sala excluida ID: ${id_sala}`)
    })
  } catch(ex){
    console.log('Erro ao excluir sala!');
    response.status(500).send(`Erro ao excluir sala`)
    return null;
  }
}

module.exports = {
  getSalas,
  getSalaById,
  createSala,
  updateSala,
  deleteSala,
  cadastrarCoordenadorNaSala
}
