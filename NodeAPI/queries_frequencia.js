const db = require('./conexao')

const verificarPodeCheckInCheckOut = (request, response, next) => {
    const { sala, id_usuario } = request.body
    const queryResponse = { checkedInOnDifferentRoom: false, previousRoom: null, message: '' }

    db.pool.query('SELECT * FROM frequencia as f where f.usuario_fk = $1 and not f.sala_fk = $2 and f.check_out IS NULL',[id_usuario,sala], (error, results) => {
        if (error) {
            throw error;
        }

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
    const { sala, id_usuario } = request.body
    const queryResponse = {successful: false, message: ''}
    var isCheckIn = true;
    db.pool.query('SELECT * FROM frequencia as f join usuario as u on f.usuario_fk=u.id_usuario where u.id_usuario = $1 and f.sala_fk = $2',[id_usuario,sala], (error, results) => {
        if (error) {
            throw error;
        }

        if(results.rowCount > 0) {
            for( var i=0; i<results.rowCount; i++) {
                if(results.rows[i].check_in != null && results.rows[i].check_out == null){
                    isCheckIn = false;
                    break;
                }
            }
        }
        if( isCheckIn ) {
            checkIn(sala, id_usuario)
            response.status(201).json('Check in: ')
        }else {
            checkOut(sala, id_usuario)
            response.status(201).json('Check out: ')
        }
    })
}

const checkIn = (sala, id_usuario) => {
    db.pool.query('INSERT INTO frequencia(sala_fk, check_in, usuario_fk) VALUES ($1, now(), $2)',[sala,id_usuario], (error, results) => {
    })
}

const checkOut = (sala, id_usuario) => {
    db.pool.query('UPDATE frequencia SET check_out = now() where sala_fk = $1 and usuario_fk = $2',[sala,id_usuario], (error, results) => {
    })
}

module.exports = {
    verificarPodeCheckInCheckOut,
    realizarCheckInCheckOut
  }
