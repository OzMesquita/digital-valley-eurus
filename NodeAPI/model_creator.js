function createCategoriaModel(row)  {
 return {
    id_categoria:row.id_categoria,
    nome_categoria:row.nome_categoria
  }
}

function createUsuarioModel(row) {
  return {
    id_usuario:row.id_usuario,
    cpf:row.cpf,
    matricula:row.matricula,
    email:row.email,
    senha:row.senha,
    nivel_acesso:row.nivel_acesso,
    nome:row.nome
  }
}

function createTrabalhoModel(row) {
  return {
    id_trabalho:row.id_trabalho,
    titulo:row.titulo,
    orientador:row.orientador,
    autor:createUsuarioModel(row),
    modalidade:createCategoriaModel(row)
  }
}

function createSalaModel(row) {
  return {
    id_sala:row.id_sala,
    nome_sala:row.nome_sala,
    numero:row.numero
  }
}

function createLocalModel(row) {
  return {
    id_local:row.id_local,
    nome_local:row.nome_local,
    ponto_referencia_local:row.ponto_referencia_local,
    andar_local:row.andar_local,
    sala:createSalaModel(row)
    }
}

function createAtividadeModel(row) {
  return {
    id_atividade: row.id_atividade,
    nome_atividade: row.nome_atividade,
    horario_previsto: row.horario_previsto,
    horario_inicial: row.horario_inicial,
    horario_final: row.horario_final,
    descricao: row.descricao,
    categoria:createCategoriaModel(row),
    trabalho:createTrabalhoModel(row),
    local:createLocalModel(row),
    apresentador:createUsuarioModel(row)
  }
}
module.exports = {
  createCategoriaModel,
  createUsuarioModel,
  createTrabalhoModel,
  createSalaModel,
  createLocalModel,
  createAtividadeModel
}
