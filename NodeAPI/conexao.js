const Pool = require('pg').Pool
const pool = new Pool({
  user: 'encontros',
  host: '200.129.62.41',
  database: 'guardiaodb',
  password: 'EncDbUserN2s',
  port: 5432,
})

const db_name = "encontrosuniversitarios."

module.exports = {pool,db_name};
