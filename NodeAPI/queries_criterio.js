const db = require('./conexao')
const http = require('http')


const getCriterios = (request, response) => {
  try {
    db.pool.query('SELECT * FROM criterio ORDER BY id ASC', (error, results) => {
      response.status(200).json(results.rows)
    })
  }catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao listar criterios`)
    return null;
  }
}

const createCriterio = (request, response) => {
  try {
    const { criterio,categoria } = request.body
    db.pool.query('INSERT INTO criterio(criterio,categoria) VALUES($1,$2)',[criterio,categoria],(error,results) => {
      if(error == null){
        response.status(201).json('Critério criado com sucesso')
      }else{
        response.status(500).json('Erro ao criar critério')
      }
    })
  }catch(ex){
    console.log('Erro 500!');
    response.status(500).send(`Erro ao criar critério`)
    return null;
  }
}

module.exports = {
    getCriterios,
    createCriterio
}