package com.example.personaltasks

class PersonalTasksDataBaseHelper {

    //SQLite > Tipos de dados INTEGER, REAL, TEXT, BLOB
    private val criarTabelaUsuario = """ CREATE TABLE ${DataBaseConstants.USUARIO.TABLE_NAME}(
        ${DataBaseConstants.USUARIO.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DataBaseConstants.USUARIO.COLUMNS.NOME} TEXT,
        ${DataBaseConstants.USUARIO.COLUMNS.EMAIL} TEXT,
        ${DataBaseConstants.USUARIO.COLUMNS.SENHA} TEXT       
    )""".trimIndent()
}