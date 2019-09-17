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

module.exports = {
    getCriterios
}