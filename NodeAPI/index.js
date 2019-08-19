
const express    = require('express')
const db_usu     = require('./queries_usuario')
const db_ati     = require('./queries_atividade')
const db_cat     = require('./queries_categoria')
const db_sal     = require('./queries_sala')
const db_tra     = require('./queries_trabalho')
const bodyParser = require('body-parser')
const app        = express()
const port       = 3000

app.use(bodyParser.json())
app.use(
  bodyParser.urlencoded({
    extended: true,
  })
)

app.get('/', (request, response) => {
  response.json({ info: 'Node.js, Express, and Postgres API' })
})

app.post('/auth', db_usu.getUsuarioByMatriculaSenha)

app.get('/usuarios', db_usu.getUsuarios)
app.get('/usuarios/:id', db_usu.getUsuarioById)
app.post('/usuarios', db_usu.getUsuarioByEmailMatricula, db_usu.createUsuario)
app.put('/usuarios/:id', db_usu.updateUsuario)
app.delete('/usuarios/:id', db_usu.deleteUsuario)

app.get('/atividades', db_ati.getAtividades)
app.get('/atividades/:id', db_ati.getAtividadeById)
app.get('/hoje/atividades/', db_ati.getAtividadesHoje)
app.post('/atividades', db_ati.createAtividade)
app.put('/atividades/:id', db_ati.updateAtividade)
app.delete('/atividades/:id', db_ati.deleteAtividade)
app.get('/atividades/coordenador/:id',db_ati.getAtividadesCoordenadorSala)

app.get('/categorias', db_cat.getCategorias)
app.get('/categorias/:id', db_cat.getCategoriaById)
app.post('/categorias', db_cat.createCategoria)
app.put('/categorias/:id', db_cat.updateCategoria)
app.delete('/categorias/:id', db_cat.deleteCategoria)

app.get('/trabalhos', db_tra.getTrabalhos)
app.get('/trabalhos/:id', db_tra.getTrabalhoById)
app.post('/trabalhos', db_tra.createTrabalho)
app.put('/trabalhos/:id', db_tra.updateTrabalho)
app.delete('/trabalhos/:id', db_tra.deleteTrabalho)

app.get('/salas', db_sal.getSalas)
app.get('/salas/:id', db_sal.getSalaById)
app.post('/salas', db_sal.createSala)
app.put('/salas/:id', db_sal.updateSala)
app.delete('/salas/:id', db_sal.deleteSala)
app.post('/salas/coordenadorsala', db_sal.cadastrarCoordenadorNaSala)

app.listen(port, () => {
  console.log(`App running on port ${port}.`)
})
