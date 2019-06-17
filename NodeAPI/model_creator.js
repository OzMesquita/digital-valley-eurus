function createCategoriaModel(row)  {
 return {id_categoria:row.id_categoria,
    nome_categoria:row.nome_categoria,
    descricao:row.descricao
  }
}
module.exports = {
  createCategoriaModel
}
