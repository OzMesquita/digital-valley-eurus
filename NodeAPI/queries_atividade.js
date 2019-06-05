const db = require('./conexao')

const getAtividades = (request, response) => {
    db.pool.query('SELECT * FROM atividade ORDER BY id_atividade ASC', (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
  }

  const getAtividadeById = (request, response) => {
    const id_atividade = parseInt(request.params.id)

    db.pool.query('SELECT * FROM atividade WHERE id_atividade = $1', [id_atividade], (error, results) => {
      if (error) {
        throw error
      }
      response.status(200).json(results.rows)
    })
  }

  const createAtividade = (request, response) => {
    const {horario_previsto, horario_inicial, horario_final, trabalho_fk, descricao, nome_atividade, categoria_fk, local_fk, apresentador_fk} = request.body

    db.pool.query('INSERT INTO atividade) VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9)', [horario_previsto, horario_inicial, horario_final, trabalho_fk, descricao, nome_atividade, categoria_fk, local_fk, apresentador_fk], (error, result) => {
      if (error) {
        throw error
      }
      response.status(201).send(`Atividade adicionada: ${nome}`)
    })
  }

  const updateAtividade = (request, response) => {
    const id_atividade = parseInt(request.params.id)
    const { horario_previsto, horario_inicial, horario_final, trabalho_fk, categoria_fk, local_fk, apresentador_fk} = request.body

    db.pool.query(
      'UPDATE atividade SET horario_previsto = $1, horario_inicial = $2, horario_final = $3, trabalho_fk = $4, categoria_fk = $5, local_fk = $6, apresentador_fk = $7 WHERE id_atividade = $8',
      [horario_previsto, horario_inicial, horario_final, trabalho_fk, categoria_fk, local_fk, apresentador_fk, id_atividade],
      (error, results) => {
        if (error) {
          throw error
        }
        response.status(200).send(`Atividade modificada ID: ${id_atividade}`)
      }
    )
  }

  const deleteAtividade = (request, response) => {
      const id_atividade = parseInt(request.params.id)

      db.pool.query('DELETE FROM atividade WHERE id_atividade = $1', [id_atividade], (error, results) => {
        if (error) {
          throw error
        }
        response.status(200).send(`Atividade excluida ID: ${id_atividade}`)
      })
    }

module.exports = {
  getAtividades,
  getAtividadeById,
  createAtividade,
  updateAtividade,
  deleteAtividade,
}
