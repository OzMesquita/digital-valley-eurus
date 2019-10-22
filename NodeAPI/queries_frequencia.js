const db = require('./conexao')

const verificarPodeCheckInCheckOut = (request, response, next) => {
    const { sala, id_usuario } = request.body
    //console.log(request.body)
    const queryResponse = { checkedInOnDifferentRoom: false, previousRoom: null, message: '', successful: false }

    db.pool.query('SELECT * FROM '+db.db_name+'frequencia as f where f.usuario_fk = $1 and not f.sala_fk = $2 and f.check_out IS NULL',[id_usuario,sala], (error, results) => {

        if(results.rowCount > 0) {
            queryResponse.checkedInOnDifferentRoom = true;
            queryResponse.previousRoom = results.rows[0].sala_fk
            queryResponse.message = "O usuário informado não fez check out na sala " + queryResponse.previousRoom
            response.status(200).json(queryResponse)
        } else {
            next()
        }
    })

}

const realizarCheckInCheckOut = (request, response) => {
    const { sala, id_usuario} = request.body
    const queryResponse = {successful: false, message: '', checkedInOnDifferentRoom: false, previousRoom: null}
    var isCheckIn = true;
    db.pool.query('SELECT * FROM '+db.db_name+'frequencia as f join '+db.db_name+'usuario as u on f.usuario_fk=u.id_usuario where u.id_usuario = $1 and f.sala_fk = $2',[id_usuario,sala], (error, results) => {

        if(results.rowCount > 0) {
            for( var i=0; i<results.rowCount; i++) {
                if(results.rows[i].check_in != null && results.rows[i].check_out == null){
                    isCheckIn = false;
                    break;
                }
            }
        }
        error = null
        if( isCheckIn ) {
            error = checkIn(sala, id_usuario)
        }else {
            error = checkOut(sala, id_usuario)
        }
        if(error) {
            queryResponse.successful = false
            queryResponse.message = 'Ocorreu um erro ao processar a requisição'
        }else{
            queryResponse.successful = true
            queryResponse.message = isCheckIn ? 'Check in realizado com sucesso' : 'Check out realizado com sucesso'
        }
        response.status(200).json(queryResponse)
    })
}

const checkIn = (sala, id_usuario) => {
    db.pool.query('INSERT INTO '+db.db_name+'frequencia(sala_fk, check_in, usuario_fk) VALUES ($1, now(), $2)',[sala,id_usuario], (error, results) => {
        console.log(error)
        return error
    })
}

const checkOut = (sala, id_usuario) => {
    db.pool.query('UPDATE '+db.db_name+'frequencia SET check_out = now() where sala_fk = $1 and usuario_fk = $2 and check_out IS NULL' ,[sala,id_usuario], (error, results) => {
        console.log(error)
        return error
    })
}

module.exports = {
    verificarPodeCheckInCheckOut,
    realizarCheckInCheckOut
  }
