const db = require('./conexao')
const http = require('http')
const nodemailer = require('nodemailer')

const getUsuarios = (request, response) => {
  try {
    db.pool.query('SELECT * FROM usuario ORDER BY id_usuario ASC', (error, results) => {
      response.status(200).json(results.rows)
    })
  }catch(ex){
    console.log('Erro ao listar usuários!');
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
    console.log('Erro ao listar usuário pelo id!');
    response.status(500).send(`Erro ao listar usuário`)
    return null;
  }
}

const getUsuarioByMatricula = (request, response) => {
  try {
    const matricula = request.params.matricula
    console.log(matricula)
    db.pool.query('SELECT nome,id_usuario FROM usuario where matricula = $1 LIMIT 1', [matricula], (error, results) => {
      response.status(200).json(results.rows[0])
    })
  }catch(ex){
    console.log(ex)
    console.log('Erro ao listar usuário!');
    response.status(500).send(`Erro ao listar usuário`)
    return null;
  }
}

const getUsuarioByEmailMatricula = (request, response, next) => {
  try {
    const { matricula, email } = request.body
    const queryResponse = { alreadyTakenEmail: false, alreadyTakenMatricula: false, message: ''}

    db.pool.query('SELECT * FROM usuario WHERE matricula = $1 or email = $2', [matricula, email], (error, results) => {
      for(var i=0; i<results.rowCount;i++){
        if(queryResponse.alreadyTakenMatricula && queryResponse.alreadyTakenEmail) break;
        if(results.rows[i].matricula == matricula){
          queryResponse.alreadyTakenMatricula = true
          console.log("mat ",queryResponse.alreadyTakenMatricula)
        }
        if(results.rows[i].email == email){
          queryResponse.alreadyTakenEmail = true
          console.log("email", queryResponse.alreadyTakenEmail)
        }
      }
      console.log("email2", queryResponse.alreadyTakenEmail)
      console.log("matricula", queryResponse.alreadyTakenMatricula)
      if(!queryResponse.alreadyTakenEmail && !queryResponse.alreadyTakenMatricula){
          console.log("d ",queryResponse.alreadyTakenEmail, queryResponse.alreadyTakenMatricula)
        next()
      }else{
        console.log("i ", queryResponse.alreadyTakenEmail, queryResponse.alreadyTakenMatricula)
        if(queryResponse.alreadyTakenMatricula){
          queryResponse.message = "Já existe uma conta com a matrícula fornecida"
        }else{
          queryResponse.message = "Já existe uma conta com o email fornecido"
        }
        console.log('Matricula/email duplicado')
        response.status(201).json(queryResponse)
      }
    })
    
  }catch(ex){
    console.log('Erro ao listar usuário, por matricula e email!');
    response.status(500).send(`Erro ao listar usuário, por matricula e email`)
    return null;
  }
}

const getUsuarioByEmailSenha = (request, response) => {
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
      console.log(queryResponse)
      response.status(200).json(queryResponse)
    });
  }catch(ex){
    console.log('Erro ao listar usuário por email!');
    response.status(500).send(`Erro ao listar usuário, por email`)
    return null;
  }

}

const createUsuario = (request, response) => {
  try {
    const queryResponse = { alreadyTakenEmail: false, alreadyTakenMatricula: false, message: ''}
    const {matricula, email, senha, nivel_acesso, nome} = request.body

    db.pool.query('INSERT INTO usuario (matricula, email, senha, nivel_acesso, nome) VALUES ($1, $2, $3, $4, $5)', [matricula, email, senha, nivel_acesso, nome], (error, result) => {
      if(error==null){
        queryResponse.message = "Usuário criado com sucesso"
        console.log(queryResponse.alreadyTakenEmail, queryResponse.alreadyTakenMatricula);
        response.status(201).json(queryResponse)
      }else{
        queryResponse.message = "Matrícula ou email já cadastrados"
        queryResponse.alreadyTakenMatricula = true;
        console.log(queryResponse.alreadyTakenEmail, queryResponse.alreadyTakenMatricula);
        response.status(201).json(queryResponse)
      }
    })
  }catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao criar usuário`)
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
        if(error == null){
          response.status(200).send(`Usuario modificado ID: ${id_usuario}`)
        }else{
          response.status(500).json('Erro ao editar usuário')
        }
      }
    )} catch(ex){
      console.log('Erro ao editar usuario!');
      response.status(500).send(`Erro ao editar usuario`)
      return null;
    }
  }

  const deleteUsuario = (request, response) => {
    try {
      const id_usuario = parseInt(request.params.id)

      db.pool.query('DELETE FROM usuario WHERE id_usuario = $1', [id_usuario], (error, results) => {
        if(error == null){
          response.status(200).send(`Usuario excluido ID: ${id_usuario}`)
        }else{
          response.status(500).send(`Erro ao excluir usuario`)
        }
      })
    }catch(ex){
      console.log('Erro ao excluir usuario!');
      response.status(500).send(`Erro ao excluir usuario`)
      return null;
    }
  }

  const forgotPassword = (req, res) => {
    const {email} = req.body
    console.log(email)
    const transporter = nodemailer.createTransport({
      service: 'gmail',
      auth: {
        user: 'matheusdin98@gmail.com',
        pass: 'matheus15'
      },
      tls: {
        rejectUnauthorized: false
      }
    })

    var mailOptions = {
      from: 'matheusdin98@gmail.com',
      to: email,
      subject: 'Recuperação de senha - Aplicativo dos Encontros Universitários da UFC Campus Russas',
      text: 'Foi solicitada a recuperação de senha da sua conta do Aplicativo dos Encontros Universitários, segue abaixo o link de recuperação de acesso. \n http://192.169.1.128:3000/form-recuperarsenha'
    }

    transporter.sendMail(mailOptions, function(error,info){
      if(error){
        console.log(error)
      }else{
        console.log('email sent: '+ info.response)
      }
    })
  }

  const getValidacaoMatricula = (req, response) => {

    const matricula = req.params.matricula
    http.get('http://192.169.1.2:8080/guardiao/api/Service?matricula='+matricula, (res)=> {
      let data = ''

      res.on('data',(chunk)=>{
        data += chunk
      })

      res.on('end', ()=>{
        if(data==404){
          const validacao = {matricula:null, nome:null}
          response.status(200).send(validacao)
        }else{
          console.log(JSON.parse(data).explanation)
          response.status(200).send(data)
        }
      })
    }).on("error", (err)=>{
      console.log("Errooo")
    })
  }

  
  module.exports = {
    getUsuarios,
    getUsuarioById,
    createUsuario,
    updateUsuario,
    deleteUsuario,
    getUsuarioByEmailMatricula,
    getUsuarioByEmailSenha,
    getUsuarioByMatricula,
    getValidacaoMatricula,
    forgotPassword
  }
