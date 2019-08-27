const db = require('./conexao')

const getUsuarios = (request, response) => {
  try {
    db.pool.query('SELECT * FROM usuario ORDER BY id_usuario ASC', (error, results) => {
      response.status(200).json(results.rows)
    })
  }catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao listar usuários`)
    return null;
  }
}

const getUsuarioById = (request, response) => {
  try {
    const id_usuario = parseInt(request.params.id)

    db.pool.query('SELECT * FROM usuario WHERE id_usuario = $1', [id_usuario], (error, results) => {

      if(id_usuario!=null){
        response.status(200).json(results.rows)
      }else{
      }
    })
  }catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao listar usuário`)
    return null;
  }
}

const getUsuarioByEmailMatricula = (request, response, next) => {
  try {
    const { matricula, email } = request.body
    const queryResponse = { alreadyTakenEmail: false, alreadyTakenMatricula: false, message: ''}

    db.pool.query('SELECT * FROM usuario WHERE matricula = $1 or email = $2', [matricula, email], (error, results) => {
      // if (error) {
      //   console.log(error)
      //   throw error
      // }
      //
      for(var i=0; i<results.rowCount;i++){
        if(queryResponse.alreadyTakenMatricula && queryResponse.alreadyTakenEmail) break;
        if(results.rows[i].matricula == matricula){
          queryResponse.alreadyTakenMatricula = true
        }
        if(results.rows[i].email == email){
          queryResponse.alreadyTakenEmail = true
        }
      }
    })

    if(!queryResponse.alreadyTakenEmail && !queryResponse.alreadyTakenMatricula){
      next()
    }else{
      queryResponse.message = "Já existe uma conta com o mesmo email ou matrícula fornecida"
      response.status(201).json(queryResponse)
    }
  }catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao listar usuário`)
    return null;
  }
}

const getUsuarioByMatriculaSenha = (request, response) => {
  try {
    const { email, senha } = request.body
    const queryResponse = {
      unregisteredEmail: false,
      wrongPassword: false,
      loginSuccessful: false,
      usuarioLogado: null
    }

    db.pool.query('SELECT * FROM usuario WHERE email = $1', [email], (error, results) => {

      if (results.rowCount > 0) {
        // console.log(results.rows)
        if(results.rows[0].senha == senha) {
          const usuarioLogado = {
            id_usuario: results.rows[0].id_usuario,
            nome: results.rows[0].nome,
            email: results.rows[0].email,
            matricula: results.rows[0].matricula,
            senha: results.rows[0].senha,
            nivel_acesso: results.rows[0].nivel_acesso
          }
          queryResponse.usuarioLogado = usuarioLogado;
          queryResponse.loginSuccessful = true;

        }else{
          queryResponse.wrongPassword = true;
        }
      } else {
        queryResponse.unregisteredEmail = true;
      }
      response.status(200).json(queryResponse)
    });
  }catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao listar usuário`)
    return null;
  }

}

const createUsuario = (request, response) => {
  try {
    // const id = parseInt(request.body)
    const queryResponse = { alreadyTakenEmail: false, alreadyTakenMatricula: false, message: ''}
    const {matricula, email, senha, nivel_acesso, nome} = request.body

    db.pool.query('INSERT INTO usuario (matricula, email, senha, nivel_acesso, nome) VALUES ($1, $2, $3, $4, $5)', [matricula, email, senha, nivel_acesso, nome], (error, result) => {
      queryResponse.message = "Usuario criado com sucesso"
      response.status(201).json(queryResponse)
    })
  }catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao crear usuario`)
    return null;
  }
}


const updateUsuario = (request, response) => {
  try {
    const id_usuario = parseInt(request.params.id)
    const { cpf, matricula, email, senha, nivel_acesso, nome} = request.body

    db.pool.query(
      'UPDATE usuario SET cpf = $1, matricula = $2, email = $3, senha = $4, nivel_acesso = $5, nome = $6 WHERE id_usuario = $7',
      [cpf, matricula, email, senha, nivel_acesso, nome, id_usuario],
      (error, results) => {
        response.status(200).send(`Usuario modificado ID: ${id_usuario}`)
        // response.status(200).send(`Usuario modified with`)
      }
    )} catch(ex){
      console.log('Erro 500!');
      response.status(500).send(`Erro ao editar usuario`)
      return null;
    }
  }

  const deleteUsuario = (request, response) => {
    try {
      const id_usuario = parseInt(request.params.id)

      db.pool.query('DELETE FROM usuario WHERE id_usuario = $1', [id_usuario], (error, results) => {
        response.status(200).send(`Usuario excluido ID: ${id_usuario}`)
      })
    }catch(ex){
      console.log('Erro 500!');
      response.status(500).send(`Erro ao excluir usuario`)
      return null;
    }
  }

  module.exports = {
    getUsuarios,
    getUsuarioById,
    createUsuario,
    updateUsuario,
    deleteUsuario,
    getUsuarioByEmailMatricula,
    getUsuarioByMatriculaSenha
  }
