const db = require('./conexao')

const verificarPodeCheckInCheckOut = (request, response, next) => {
    const { sala, matricula } = request.body
    const queryResponse = { checkedInOnDifferentRoom: false, previousRoom: null, message: '' }

    db.pool.query('SELECT * FROM frequencia as f join usuario as u on f.usuario_fk=u.id_usuario where u.matricula = $1 and f.sala_fk != $2',[matricula,sala], (error, results) => {
        if (error) {
            throw error;
        }

        if(results.rowCount > 0) {
            queryResponse.checkedInOnDifferentRoom = true;
            queryResponse.previousRoom = results.rows[0].sala_fk
        }
    })

    if(queryResponse.checkedInOnDifferentRoom){
        queryResponse.message = "O usuário informado não fez check out na sala " + queryResponse.previousRoom
        response.status(200).json(queryResponse)
    } else {
        next()
    }
}

const realizarCheckInCheckOut = (request, response) => {
    const { sala, matricula } = response.body
    const queryResponse = {successful: false, message: ''}
    const isCheckIn = true;
    db.pool.query('SELECT * FROM frequencia as f join usuario as u on f.usuario_fk=u.id_usuario where u.matricula = $1 and f.sala_fk = $2',[matricula,sala], (error, results) => {
        if (error) {
            throw error;
        }

        if(results.rowCount > 0) {
            for( var i=0; i<results.rowCount; i++) {
                if(results[i].check_in != null && results[i].check_out == null){
                    isCheckIn = false;
                    break;
                }
            }
        }
    })
    if( isCheckIn ) {
        checkIn(sala, matricula)
    }else {

    }
}

const checkIn = (sala, matricula) => {
    db.pool.query('INSERT INTO frequencia(sala_fk, check_in, usuario_fk) VALUES ($1, $2, $3)',[sala, Date.now(),matricula], (error, results) => {

    })
}

module.exports = {
    verificarPodeCheckInCheckOut,
    realizarCheckInCheckOut
  }
