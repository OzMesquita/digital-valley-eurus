const db = require('./conexao')

const getTrabalhos = (request, response) => {
  db.pool.query('SELECT * FROM '+db.db_name+'trabalho ORDER BY id_trabalho ASC', (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).json(results.rows)
  })
}

const getTrabalhoById = (request, response) => {
  const id_trabalho = parseInt(request.params.id)

  db.pool.query('SELECT * FROM '+db.db_name+'trabalho WHERE id_trabalho = $1', [id_trabalho], (error, results) => {
    if (error) {
      throw error
    }
    if(id_trabalho!=null){
      response.status(200).json(results.rows)
    }else{
    }

  })
}

const createTrabalho = (request, response) => {
  // const id = parseInt(request.body)
  const {titulo, modalidade_fk, autor_fk, orientador} = request.body

  db.pool.query('INSERT INTO '+db.db_name+'trabalho (titulo, modalidade_fk, autor_fk, orientador) VALUES ($1, $2, $3, $4)', [titulo, modalidade_fk, autor_fk, orientador], (error, result) => {
    if (error) {
      throw error
    }
    response.status(201).send(`Trabalho adicionado: ${titulo}`)
  })
}

const updateTrabalho = (request, response) => {
  const id_trabalho = parseInt(request.params.id)
  const { titulo, modalidade_fk, autor_fk, orientador} = request.body

  db.pool.query(
    'UPDATE '+db.db_name+'trabalho SET titulo = $1, modalidade_fk = $2, autor_fk = $3, orientador = $4 WHERE id_trabalho = $5',
    [titulo, modalidade_fk, autor_fk, orientador, id_trabalho],
    (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`Trabalho modificado ID: ${id_trabalho}`)
      // response.status(200).send(`Trabalho modified with`)
    }
  )
}

const deleteTrabalho = (request, response) => {
  const id_trabalho = parseInt(request.params.id)

  db.pool.query('DELETE FROM '+db.db_name+'trabalho WHERE id_trabalho = $1', [id_trabalho], (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).send(`Trabalho excluido ID: ${id_trabalho}`)
  })
}

module.exports = {
  getTrabalhos,
  getTrabalhoById,
  createTrabalho,
  updateTrabalho,
  deleteTrabalho,
}
