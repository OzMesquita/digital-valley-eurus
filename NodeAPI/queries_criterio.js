const db = require('./conexao')
const http = require('http')


const getCriterios = (request, response) => {
  try {
    db.pool.query('SELECT * FROM '+db.db_name+'criterio ORDER BY id ASC', (error, results) => {

    //  console.log(results.rows);
    if(results!= null){
        response.status(200).json(results.rows)
    }else{
        response.status(200).json([])
    }
    })
  }catch(ex){
    console.log('Erro ao listar criterios!');
    response.status(500).send(`Erro ao listar criterios`)
    return null;
  }
}

const createCriterio = (request, response) => {
  try {
    const { criterio,categoria } = request.body
    db.pool.query('INSERT INTO '+db.db_name+'criterio(criterio,categoria) VALUES($1,$2)',[criterio,categoria],(error,results) => {
      if(error == null){
        response.status(201).json('Critério criado com sucesso')
      }else{
        response.status(500).json('Erro ao criar critério')
      }
    })
  }catch(ex){
    console.log('Erro ao criar critério!');
    response.status(500).send(`Erro ao criar critério`)
    return null;
  }
}

module.exports = {
    getCriterios,
    createCriterio
}
