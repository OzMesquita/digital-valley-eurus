const Pool = require('pg').Pool
const pool = new Pool({
  user: 'postgres',
  host: '192.169.1.2',
  database: 'encontrosuniversitarios',
  password: '123',
  port: 5432,
})

module.exports = {pool};
