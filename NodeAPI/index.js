
const express    = require('express')
const db_u       = require('./queries_usuario')
const db_ati     = require('./queries_atividade')
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

app.get('/usuarios', db_u.getUsers)
app.get('/usuarios/:id', db_u.getUserById)
app.post('/usuarios', db_u.createUser)
app.put('/editar/usuarios/:id', db_u.updateUser)
app.delete('/excluir/usuarios/:id', db_u.deleteUser)

app.get('/atividades', db_ati.getAtividades)
app.get('/atividades/:id', db_ati.getAtividadesById)
app.post('/atividades', db_ati.createAtividade)
app.put('editar/atividades/:id', db_ati.updateAtividade)
app.delete('excluir/atividades/:id', db_ati.deleteAtividade)

app.listen(port, () => {
  console.log(`App running on port ${port}.`)
})
