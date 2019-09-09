const db = require('./conexao')

const getSalas = (request, response) => {
  try {
    db.pool.query('SELECT * FROM sala ORDER BY id_sala ASC', (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
  } catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao listar salas`)
    return null;
  }
}

const getSalaById = (request, response) => {
  try {
    const id_sala = parseInt(request.params.id)

    db.pool.query('SELECT * FROM sala WHERE id_sala = $1', [id_sala], (error, results) => {

      if(id_sala!=null){
        response.status(200).json(results.rows)
      }else{
      }
    })
  } catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao listar sala`)
    return null;
  }
}

const createSala = (request, response) => {
  try {
    // return JSON.parse(req.headers.myHeader);
    const {nome_sala, numeros } = request.body

    db.pool.query('INSERT INTO sala (nome_sala, numero) VALUES ($1, $2)', [nome_sala, numero], (error, result) => {
      response.status(201).send(`Sala adicionada: ${nome_sala}`)
    })

  } catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao cadastrar sala`)
    return null;
  }
}

const cadastrarCoordenadorNaSala = (request, response) => {
  try {
    const {id_usuario, id_sala} = request.body

    db.pool.query('INSERT INTO atividades_coordenador (sala_fk, usuario_fk) VALUES ($1, $2)', [id_sala,id_usuario], (error, result) => {
      response.status(201).send('Coordenador cadastrado na sala com sucesso')
    })
  } catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao cadastrar Coordenador na Sala`)
    return null;
  }
}

const updateSala = (request, response) => {
  try {
    const id_sala = parseInt(request.params.id)
    const {nome_sala, numero_sala} = request.body
    db.pool.query(
      'UPDATE sala SET nome_sala= $1, numero_sala = $2 WHERE id_sala = $3',
      [nome_sala, numero_sala, id_sala],
      (error, results) => {
        response.status(200).send(`Sala modificada ID: ${id_sala}`)
        // response.status(200).send(`Sala modified with`)
      }
    )
  } catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao atualizar sala`)
    return null;
  }
}

const deleteSala = (request, response) => {
  try {
    const id_sala = parseInt(request.params.id)

    db.pool.query('DELETE FROM sala WHERE id_sala = $1', [id_sala], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`Sala excluida ID: ${id_sala}`)
    })
  } catch(ex){
    console.log('Erro 500!');
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
