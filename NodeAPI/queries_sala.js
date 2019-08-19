const db = require('./conexao')

const getSalas = (request, response) => {
  db.pool.query('SELECT * FROM sala ORDER BY id_sala ASC', (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).json(results.rows)
  })
}

const getSalaById = (request, response) => {
  const id_sala = parseInt(request.params.id)

  db.pool.query('SELECT * FROM sala WHERE id_sala = $1', [id_sala], (error, results) => {
    if (error) {
      throw error
    }
    if(id_sala!=null){
      response.status(200).json(results.rows)
    }else{
    }
  })
}

const createSala = (request, response) => {
  // const id = parseInt(request.body)
  const {nome_sala, numero_sala } = request.body

  db.pool.query('INSERT INTO sala (nome_sala, numero_sala) VALUES ($1, $2)', [nome_sala, numero_sala], (error, result) => {
    if (error) {
      throw error
    }
    response.status(201).send(`Sala adicionada: ${nome_sala}`)
  })
}

const cadastrarCoordenadorNaSala = (request, response) => {
  const {id_usuario, id_sala} = request.body

  db.pool.query('INSERT INTO atividades_coordenador (sala_fk, usuario_fk) VALUES ($1, $2)', [id_sala,id_usuario], (error, result) => {
    if (error) {
      throw error
    }
    response.status(201).send('Coordenador cadastrado na sala com sucesso')
  })
}

const updateSala = (request, response) => {
  const id_sala = parseInt(request.params.id)
  const {nome_sala, numero_sala} = request.body

  db.pool.query(
    'UPDATE sala SET nome_sala= $1, numero_sala = $2 WHERE id_sala = $3',
    [nome_sala, numero_sala, id_sala],
    (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`Sala modificada ID: ${id_sala}`)
      // response.status(200).send(`Sala modified with`)
    }
  )
}

const deleteSala = (request, response) => {
  const id_sala = parseInt(request.params.id)

  db.pool.query('DELETE FROM sala WHERE id_sala = $1', [id_sala], (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).send(`Sala excluida ID: ${id_sala}`)
  })
}

module.exports = {
  getSalas,
  getSalaById,
  createSala,
  updateSala,
  deleteSala,
  cadastrarCoordenadorNaSala
}
