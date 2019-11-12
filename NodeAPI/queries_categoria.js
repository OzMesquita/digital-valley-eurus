const db = require('./conexao')

const getCategorias = (request, response) => {
  db.pool.query('SELECT * FROM '+db.db_name+'categoria ORDER BY id_categoria ASC', (error, results) => {

    if (results!=null) {
        response.status(200).json(results.rows)
    }else{
        response.status(200).json([])
    }

  })
}

const getCategoriaById = (request, response) => {
  const id_categoria = parseInt(request.params.id)

  db.pool.query('SELECT * FROM '+db.db_name+'categoria WHERE id_categoria = $1', [id_categoria], (error, results) => {
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
  const {nome_categoria, descricao } = request.body

  db.pool.query('INSERT INTO '+db.db_name+'categoria (nome_categoria, descricao) VALUES ($1, $2)', [nome_categoria, descricao], (error, result) => {
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
    'UPDATE '+db.db_name+'categoria SET nome_categoria= $1, descricao = $2 WHERE id_categoria = $3',
    [nome_categoria, descricao, id_categoria],
    (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`Categoria modificada ID: ${id_categoria}`)
    }
  )
}

const deleteCategoria = (request, response) => {
  const id_categoria = parseInt(request.params.id)

  db.pool.query('DELETE FROM '+db.db_name+'categoria WHERE id_categoria = $1', [id_categoria], (error, results) => {
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
