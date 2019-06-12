const db = require('./conexao')

const getCategorias = (request, response) => {
  db.pool.query('SELECT * FROM categoria ORDER BY id_categoria ASC', (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).json(results.rows)
  })
}

const getCategoriaById = (request, response) => {
  const id_categoria = parseInt(request.params.id)

  db.pool.query('SELECT * FROM categoria WHERE id_categoria = $1', [id_categoria], (error, results) => {
    if (error) {
      throw error
    }
    if(id_categoria!=null){
      response.status(200).json(results.rows)
    }else{
    }

  })
}

const createCategoria = (request, response) => {
  // const id = parseInt(request.body)
  const {nome_categoria, descricao } = request.body

  db.pool.query('INSERT INTO categoria (nome_categoria, descricao) VALUES ($1, $2)', [nome_categoria, descricao], (error, result) => {
    if (error) {
      throw error
    }
    response.status(201).send(`Categoria adicionada: ${nome_categoria}`)
  })
}

const updateCategoria = (request, response) => {
  const id_categoria = parseInt(request.params.id)
  const {nome_categoria, descricao} = request.body

  db.pool.query(
    'UPDATE categoria SET nome_categoria= $1, descricao = $2 WHERE id_categoria = $3',
    [nome_categoria, descricao, id_categoria],
    (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`Categoria modificada ID: ${id_categoria}`)
      // response.status(200).send(`Categoria modified with`)
    }
  )
}

const deleteCategoria = (request, response) => {
  const id_categoria = parseInt(request.params.id)

  db.pool.query('DELETE FROM categoria WHERE id_categoria = $1', [id_categoria], (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).send(`Categoria excluida ID: ${id_categoria}`)
  })
}

module.exports = {
  getCategorias,
  getCategoriaById,
  createCategoria,
  updateCategoria,
  deleteCategoria,
}
