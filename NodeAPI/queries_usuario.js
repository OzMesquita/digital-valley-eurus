const db = require('./conexao')

const getUsuarios = (request, response) => {
  db.pool.query('SELECT * FROM usuario ORDER BY id_usuario ASC', (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).json(results.rows)
  })
}

const getUsuarioById = (request, response) => {
  const id_usuario = parseInt(request.params.id)

  db.pool.query('SELECT * FROM usuario WHERE id_usuario = $1', [id_usuario], (error, results) => {
    if (error) {
      throw error
    }
    if(id_usuario!=null){
      response.status(200).json(results.rows)
    }else{
    }

  })
}

const createUsuario = (request, response) => {
  // const id = parseInt(request.body)
  const {cpf, matricula, email, senha, nivel_acesso, nome} = request.body

  db.pool.query('INSERT INTO usuario (cpf, matricula, email, senha, nivel_acesso, nome) VALUES ($1, $2, $3, $4, $5, $6)', [cpf, matricula, email, senha, nivel_acesso, nome], (error, result) => {
    if (error) {
      throw error
    }
    response.status(201).send(`Usuario adicionado: ${nome}`)
  })
}
// const createUsuario = (request, response) => {
//   const id = parseInt(request.body)
//   const cpf = request.params.cpf
//
//   db.pool.query('INSERT INTO usuario (cpf) VALUES ($1)', [cpf], (error, result) => {
//     if (error) {
//       throw error
//     }
//     response.status(201).send(`Usuario added with ID: ${result.id}`)
//   })
// }
const updateUsuario = (request, response) => {
  const id_usuario = parseInt(request.params.id)
  const { cpf, matricula, email, senha, nivel_acesso, nome} = request.body

  db.pool.query(
    'UPDATE usuario SET cpf = $1, matricula = $2, email = $3, senha = $4, nivel_acesso = $5, nome = $6 WHERE id_usuario = $7',
    [cpf, matricula, email, senha, nivel_acesso, nome, id_usuario],
    (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).send(`Usuario modificado ID: ${id_usuario}`)
      // response.status(200).send(`Usuario modified with`)
    }
  )
}

const deleteUsuario = (request, response) => {
  const id_usuario = parseInt(request.params.id)

  db.pool.query('DELETE FROM usuario WHERE id_usuario = $1', [id_usuario], (error, results) => {
    if (error) {
      throw error
    }
    response.status(200).send(`Usuario excluido ID: ${id_usuario}`)
  })
}

module.exports = {
  getUsuarios,
  getUsuarioById,
  createUsuario,
  updateUsuario,
  deleteUsuario,
}
