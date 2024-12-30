const express = require('express');
const sql = require('mssql');

const app = express();
app.get('/books', async (req, res) => {
    const result = await sql.query`SELECT * FROM books`;
    res.json(result.recordset);
});
app.listen(3000);