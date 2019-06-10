const db = require('./conexao')

function dataFormatada(teste){
  // teste = row.horario_previsto;  //2019-05-03T13:35:00.000Z
  t = Date.parse(teste)
  var data = new Date(teste);
  dia = data.getDate();
  mes = data.getMonth() +1; //+1 pois no getMonth Janeiro começa com zero.
  ano = data.getFullYear();
  hora = data.getHours();
  minutos = data.getMinutes();
  segundos = data.getSeconds();
  // correta =
  return dia+"/"+mes+"/"+ano+" "+hora+":"+minutos+":"+segundos;
}

const getAtividades = (request, response) => {
  db.pool.query('SELECT * from  atividade as a join categoria as c on a.categoria_fk = c.id_categoria join local as l on a.local_fk=l.id_local join 	 trabalho as t on a.trabalho_fk=t.id_trabalho join usuario as u on a.apresentador_fk=u.id_usuario join sala on l.sala_fk=sala.id_sala'
  ,  (error, result) => {
    var resultss = [], index = {};
    if (error) {
      throw error
    }
    console.log(result);
    result.rows.forEach(function (row) {
      if ( !(row.id_atividade in index) ) {
        index[row.id_atividade] = {
          id_atividade: row.id_atividade,
          nome_atividade: row.nome_atividade,
          horario_previsto:null,
          horario_inicial:null,
          horario_final:null,
          categoria:{id_categoria:row.id_categoria,
            nome_categoria:row.nome_categoria,
            descricao:row.descricao
          },
          trabalho:{id_trabalho:row.id_trabalho,
            titulo:row.titulo,
            orientador:row.orientador,
            autor:{id_usuario:row.id_usuario,
              cpf:row.cpf,
              matricula:row.matricula,
              email:row.email,
              senha:row.senha,
              nivel_acesso:row.nivel_acesso,
              nome:row.nome},
              modalidade:{id_categoria:row.id_categoria,
                nome_categoria:row.nome_categoria,
                descricao:row.descricao}

              },
              local:{id_local:row.id_local,
                nome_local:row.nome_local,
                ponto_referencia_local:row.ponto_referencia_local,
                andar_local:row.andar_local,
                sala:{id_sala:row.id_sala,
                  nome_sala:row.nome_sala,
                  numero:row.numero}
                },
                apresentador:{id_usuario:row.id_usuario,
                  cpf:row.cpf,
                  matricula:row.matricula,
                  email:row.email,
                  senha:row.senha,
                  nivel_acesso:row.nivel_acesso,
                  nome:row.nome
                }
              };
              resultss.push(index[row.id_atividade]);
            }
          });
          console.log(resultss);
          response.status(200).json(resultss);
        });
      }

const getAtividadeById = (request, response) => {
  const id_atividad = parseInt(request.params.id)
  var resultss = [], index = {}, teste, data, momento, dia, correta;

  db.pool.query('SELECT * from  atividade as a join categoria as c on a.categoria_fk = c.id_categoria join local as l on a.local_fk=l.id_local join 	 trabalho as t on a.trabalho_fk=t.id_trabalho join usuario as u on a.apresentador_fk=u.id_usuario join sala on l.sala_fk=sala.id_sala WHERE id_atividade = $1', [id_atividad], (error, result) => {
    if (error) {
      throw error
    }
    console.log(result);
    result.rows.forEach(function (row) {

      if ( !(row.id_atividade in index) ) {
        index[row.id_atividade] = {
          id_atividade: row.id_atividade,
          nome_atividade: row.nome_atividade,
          horario_previsto: dataFormatada(row.horario_previsto),
          horario_inicial:dataFormatada(row.horario_inicial),
          horario_final:dataFormatada(row.horario_final),
          categoria:{id_categoria:row.id_categoria,
            nome_categoria:row.nome_categoria,
            descricao:row.descricao
          },
          trabalho:{id_trabalho:row.id_trabalho,
            titulo:row.titulo,
            orientador:row.orientador,
            autor:{id_usuario:row.id_usuario,
                  cpf:row.cpf,
                  matricula:row.matricula,
                  email:row.email,
                  senha:row.senha,
                  nivel_acesso:row.nivel_acesso,
                  nome:row.nome},
            modalidade:{id_categoria:row.id_categoria,
              nome_categoria:row.nome_categoria,
              descricao:row.descricao}
          },
          local:{id_local:row.id_local,
            nome_local:row.nome_local,
            ponto_referencia_local:row.ponto_referencia_local,
            andar_local:row.andar_local,
            sala:{id_sala:row.id_sala,
              nome_sala:row.nome_sala,
              numero:row.numero}
            },
            apresentador:{id_usuario:row.id_usuario,
              cpf:row.cpf,
              matricula:row.matricula,
              email:row.email,
              senha:row.senha,
              nivel_acesso:row.nivel_acesso,
              nome:row.nome
            }

              };
              resultss.push(index[row.id_atividade]);

            }
          });
          console.log(resultss);

          // console.log("teste ",Date.parse(teste));
          // console.log("data", data.toISOString());
          // console.log("correta", correta);
          // // console.log(data.format('YYYY-MM-DD[T]HH:mm:ss'));
          // console.log(formatDate("8888888888888888888", teste));
          // console.log(dt.format('YYYY-MM-DD[T]HH:mm:ss'));
          response.status(200).json(resultss);
        });
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
