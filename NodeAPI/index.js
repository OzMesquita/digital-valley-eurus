
const express    = require('express')
const db_usu       = require('./queries_usuario')
const db_ati     = require('./queries_atividade')
const db_cat       = require('./queries_categoria')
const db_sal    = require('./queries_sala')
const db_tra    = require('./queries_trabalho')
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

app.get('/usuarios', db_usu.getUsuarios)
app.get('/usuarios/:id', db_usu.getUsuarioById)
app.post('/usuarios', db_usu.createUsuario)
app.put('/editar/usuarios/:id', db_usu.updateUsuario)
app.delete('/excluir/usuarios/:id', db_usu.deleteUsuario)

app.get('/atividades', db_ati.getAtividades)
app.get('/atividades/:id', db_ati.getAtividadeById)
app.post('/atividades', db_ati.createAtividade)
app.put('editar/atividades/:id', db_ati.updateAtividade)
app.delete('excluir/atividades/:id', db_ati.deleteAtividade)
//
// app.get('/categorias', db_cat.getCategorias)
// app.get('/categorias/:id', db_cat.getCategoriaById)
// app.post('/categorias', db_cat.createAtividade)
// app.put('editar/categorias/:id', db_cat.updateCategoria)
// app.delete('excluir/categorias/:id', db_cat.deleteCategoria)
//
// app.get('/trabalhos', db_tra.getTrabalhos)
// app.get('/trabalhos/:id', db_tra.getTrabalhoById)
// app.post('/trabalhos', db_tra.createTrabalho)
// app.put('editar/trabalhos/:id', db_tra.updateTrabalho)
// app.delete('excluir/trabalhos/:id', db_tra.deleteTrabalho)
//
// app.get('/salas', db_tra.getSalas)
// app.get('/salas/:id', db_tra.getSalaById)
// app.post('/salas', db_tra.createSala)
// app.put('editar/salas/:id', db_tra.updateSala)
// app.delete('excluir/salas/:id', db_tra.deleteSala)

app.listen(port, () => {
  console.log(`App running on port ${port}.`)
})
