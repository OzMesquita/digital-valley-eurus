const Pool = require('pg').Pool
const pool = new Pool({
  user: 'postlocal',
  host: '127.0.0.1',
  database: 'encontros',
  password: '123',
  port: 5432,
})
	const db_name = "encontrosuniversitarios."
	module.exports = {pool,db_name};
