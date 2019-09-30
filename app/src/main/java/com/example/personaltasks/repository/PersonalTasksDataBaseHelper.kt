package com.example.personaltasks.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.personaltasks.constants.DataBaseConstants

class PersonalTasksDataBaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    //Variáveis tem que ser estática para ser acessada antes da criação da classe (lin. 8)
    companion object{
        private val DATABASE_VERSION: Int = 1
        private val DATABASE_NAME: String = "personaltask.db"
    }

    //SQLite > Tipos de dados INTEGER, REAL, TEXT, BLOB
    private val criarTabelaUsuario = """ CREATE TABLE ${DataBaseConstants.USUARIO.TABLE_NAME}(
        ${DataBaseConstants.USUARIO.COLUMNS.ID} INTEGER PRIMARY KEY AUTOINCREMENT,
        ${DataBaseConstants.USUARIO.COLUMNS.NOME} TEXT,
        ${DataBaseConstants.USUARIO.COLUMNS.EMAIL} TEXT,
        ${DataBaseConstants.USUARIO.COLUMNS.SENHA} TEXT       
    );"""

    private val deletarTabelaUsuario = "drop table if exists ${DataBaseConstants.USUARIO.TABLE_NAME}"

    //Só executa quando o app é instalado
    override fun onCreate(sqLite: SQLiteDatabase) {
        sqLite.execSQL(criarTabelaUsuario)

    }

    override fun onUpgrade(sqLite: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //remoção
        sqLite.execSQL(deletarTabelaUsuario)
        //criacao
        sqLite.execSQL(criarTabelaUsuario)
    }

}