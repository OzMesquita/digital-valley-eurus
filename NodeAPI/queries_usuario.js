const db = require('./conexao')
const http = require('http')
const nodemailer = require('nodemailer')
const crypto = require('crypto')
const bcrypt = require('bcrypt')
var salt = bcrypt.genSaltSync(10)

const getUsuarios = (request, response) => {
  try {
    db.pool.query('SELECT * FROM '+db.db_name+'usuario ORDER BY id_usuario ASC', (error, results) => {
      if(results!=null){
        response.status(200).json(results.rows)
      }else{
        response.status(200).json([])
      }

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

    db.pool.query('SELECT * FROM '+db.db_name+'usuario WHERE id_usuario = $1', [id_usuario], (error, results) => {
      if(id_usuario!=null){
        response.status(200).json(results.rows)
      }else{
        response.status(200).json([])
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
    db.pool.query('SELECT nome,id_usuario FROM '+db.db_name+'usuario where matricula = $1 LIMIT 1', [matricula], (error, results) => {
      response.status(200).json(results.rows[0])
    })
  }catch(ex){
    console.log('Erro ao listar usuário!');
    response.status(500).send(`Erro ao listar usuário`)
    return null;
  }
}

const getUsuarioByEmailMatricula = (request, response, next) => {
  try {
    const { matricula, email } = request.body
    const queryResponse = { alreadyTakenEmail: false, alreadyTakenMatricula: false, message: ''}

    db.pool.query('SELECT * FROM '+db.db_name+'usuario WHERE matricula = $1 or email = $2', [matricula, email], (error, results) => {
      if(results != null){
        for(var i=0; i<results.rowCount;i++){
          if(queryResponse.alreadyTakenMatricula && queryResponse.alreadyTakenEmail) break;
          if(results.rows[i].matricula == matricula){
            queryResponse.alreadyTakenMatricula = true
          }
          if(results.rows[i].email == email){
            queryResponse.alreadyTakenEmail = true
          }
        }
      }
      if(!queryResponse.alreadyTakenEmail && !queryResponse.alreadyTakenMatricula){
        next()
      }else{
        if(queryResponse.alreadyTakenMatricula){
          queryResponse.message = "Já existe uma conta com a matrícula fornecida"
        }else{
          queryResponse.message = "Já existe uma conta com o email fornecido"
        }
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

    var senhaEncriptada = bcrypt.hashSync(senha, salt)

    db.pool.query('SELECT * FROM '+db.db_name+'usuario WHERE email = $1', [email], (error, results) => {

      if (results.rowCount > 0) {
        console.log(results.rows[0])
        if(results.rows[0].senha == senhaEncriptada) {
          const usuarioLogado = {
            id_usuario: results.rows[0].id_usuario,
            nome: results.rows[0].nome,
            email: results.rows[0].email,
            matricula: results.rows[0].matricula,
            senha: senha,
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
    console.log('Erro ao listar usuário por email!');
    response.status(500).send(`Erro ao listar usuário, por email`)
    return null;
  }

}

const createUsuario = (request, response) => {
  try {
    const queryResponse = { alreadyTakenEmail: false, alreadyTakenMatricula: false, message: ''}
    const {matricula, email, senha, nivel_acesso, nome} = request.body

    var senhaEncriptada = bcrypt.hashSync(senha, salt)
    console.log(senhaEncriptada)
    db.pool.query('INSERT INTO '+db.db_name+'usuario (matricula, email, senha, nivel_acesso, nome) VALUES ($1, $2, $3, $4, $5)', [matricula, email, senhaEncriptada, nivel_acesso, nome], (error, result) => {
      if(error==null){
        queryResponse.message = "Usuário criado com sucesso"
        response.status(201).json(queryResponse)
      }else{
        queryResponse.message = "Matrícula ou email já cadastrados"
        queryResponse.alreadyTakenMatricula = true;
        response.status(201).json(queryResponse)
      }
    })
  }catch(ex){
    console.log('Erro ao criar usuário!');
    response.status(500).send(`Erro ao criar usuário`)
    return null;
  }
}


const updateUsuario = (request, response) => {
  try {
    const id_usuario = parseInt(request.params.id)
    const { cpf, matricula, email, senha, nivel_acesso, nome} = request.body

    var senhaEncriptada = bcrypt.hashSync(senha, salt)

    db.pool.query(
      'UPDATE '+db.db_name+'usuario SET cpf = $1, matricula = $2, email = $3, senha = $4, nivel_acesso = $5, nome = $6 WHERE id_usuario = $7',
      [cpf, matricula, email, senhaEncriptada, nivel_acesso, nome, id_usuario],
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

      db.pool.query('DELETE FROM '+db.db_name+'usuario WHERE id_usuario = $1', [id_usuario], (error, results) => {
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
    try{
      const email = req.params.email
      var timeNow = Date.now()
      var token = crypto.createHash('md5').update(timeNow.toString()+"-1-"+email).digest('hex')
      db.pool.query('INSERT INTO '+db.db_name+'recuperacao_senha(token,horario,email) VALUES($1,now(),$2)', [token,email], (error, results) => {
        if(error == null){
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
            text: 'Foi solicitada a recuperação de senha da sua conta do Aplicativo dos Encontros Universitários, segue abaixo o link de recuperação de acesso. \n http://192.169.1.128:3000/form-recuperarsenha/'+token
          }

          transporter.sendMail(mailOptions, function(error,info){
            if(error){
              //console.log(error)
              res.status(404).send(false)
            }else{
              //console.log('email sent: '+ info.response)
              res.status(200).send(true)
            }
          })
        }else{
          //console.log(error)
          res.status(404).send(false)
        }
      })

    }catch(ex){
      console.log('Erro de recuperação de senha');
      //console.log(ex)
      res.status(404).send(false)
      return null;
    }
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
          response.status(200).send(data)
        }
      })
    }).on("error", (err)=>{
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
