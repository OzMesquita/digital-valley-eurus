
const express    = require('express')
const db_usu     = require('./queries_usuario')
const db_ati     = require('./queries_atividade')
const db_cat     = require('./queries_categoria')
const db_sal     = require('./queries_sala')
const db_tra     = require('./queries_trabalho')
const db_fre     = require('./queries_frequencia')
const db_cri     = require('./queries_criterio')
const bodyParser = require('body-parser')

const path = require('path')
const app        = express()
const port       = 3000

app.use(bodyParser.json())
app.use(
  bodyParser.urlencoded({
    extended: true,
  })
)

app.use(__dirname,express.static('/public'));

var engine = require('consolidate');
app.set('views', __dirname + '/views');
app.engine('html', engine.mustache);
app.set('view engine', 'html');

app.get('/', (request, response) => {
  response.json({ info: 'Node.js, Express, and Postgres API' })
})

// app.get('/forgot', (request, response)=> {
//    response.render('./boi.html', { });
// })

app.get('/usuario/verificacao/:matricula', db_usu.getValidacaoMatricula)
app.post('/auth', db_usu.getUsuarioByEmailSenha)
app.get('/usuario/:matricula',db_usu.getUsuarioByMatricula)
app.get('/usuarios', db_usu.getUsuarios)
app.get('/usuarios/:id', db_usu.getUsuarioById)
app.post('/usuarios', db_usu.getUsuarioByEmailMatricula, db_usu.createUsuario)
app.put('/usuarios/:id', db_usu.updateUsuario)
app.delete('/usuarios/:id', db_usu.deleteUsuario)
app.get('/recuperarsenha/:email', db_usu.forgotPassword)
app.get('/form-recuperarsenha/:token', (req,res) => {
  res.type('html')
  console.log(__dirname)
  res.sendFile(path.join(__dirname+'/views/index.html'))
})

app.get('/atividades', db_ati.getAtividades)
app.get('/atividades/:id', db_ati.getAtividadeById)
app.get('/hoje/atividades/', db_ati.getAtividadesHoje)
app.post('/atividades', db_ati.createAtividade)
app.put('/atividades/:id', db_ati.updateAtividade)
app.delete('/atividades/:id', db_ati.deleteAtividade)
app.get('/atividades/coordenador/:id',db_ati.getAtividadesCoordenadorSala)
app.get('/atividades/professor/:id', db_ati.getAtividadesProfessor)
app.get('/frequencia/:id',db_ati.getAtividadesFrequentadas)
app.get('/momento/', db_ati.getMomento)
app.post('/avaliacao',db_ati.verificarAtividadeAvaliada,db_ati.cadastrarNotas,db_ati.cadastrarAvaliacao)
app.post('/avaliada',db_ati.verificarAvaliacaoFeita)

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

app.post('/frequencia', db_fre.verificarPodeCheckInCheckOut, db_fre.realizarCheckInCheckOut)

app.get('/criterios', db_cri.getCriterios)
app.post('/criterios', db_cri.createCriterio)

app.listen(port, () => {
  console.log(`App running on port ${port}.`)
})
