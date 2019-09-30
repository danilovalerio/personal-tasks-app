package com.example.personaltasks.repository

import android.content.ContentValues
import android.content.Context
import com.example.personaltasks.constants.DataBaseConstants

/**
 * Responsável pela inserção da classe usuário
 * Para evitar concorrência todas as classes repository deve ser Singleton
 * Regras singleton:
 *  - Construtor tem que ser privado
 *  - Tenha um método que retorna a instância da classe (getInstance)
 */

class UsuarioRepository private constructor(context: Context){
    //para acessar o banco
    private var mPersonalTasksDataBaseHelper : PersonalTasksDataBaseHelper = PersonalTasksDataBaseHelper(context)

    companion object{
        fun getInstance(context: Context) : UsuarioRepository {
            if(INSTANCE == null){
                INSTANCE = UsuarioRepository(context)
            }
            return INSTANCE as UsuarioRepository
        }
        //variável que armazena a instância da classe
        private var INSTANCE: UsuarioRepository? = null
    }

    fun emailExistente(email: String) : Boolean{
        val db = mPersonalTasksDataBaseHelper.readableDatabase

        //Itens esperados no retorno
        val projecao = arrayOf(DataBaseConstants.USUARIO.COLUMNS.ID)

        //Qual o filtro que temos que aplicar
        val selecao = "${DataBaseConstants.USUARIO.COLUMNS.EMAIL} = ?"
        val selecaoArgumentos = arrayOf(email)

        //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
        db.query(DataBaseConstants.USUARIO.TABLE_NAME, projecao, selecao,  selecaoArgumentos, null, null, null)

        //db.rawQuery("select * from user where email = danilo", null)
    }

    //faz a inserção e retorna o último ID inserido
    fun insert(nome: String, email: String, senha: String): Int{
        //ações no banco readableDatabase: SELECT, writableDatabase: INSERT, DELETE, UPDATE
        val db = mPersonalTasksDataBaseHelper.writableDatabase //abre conexão para escrita

        val insertValues = ContentValues()
        insertValues.put(DataBaseConstants.USUARIO.COLUMNS.NOME, nome)
        insertValues.put(DataBaseConstants.USUARIO.COLUMNS.EMAIL, email)
        insertValues.put(DataBaseConstants.USUARIO.COLUMNS.SENHA, senha)

        return db.insert(DataBaseConstants.USUARIO.TABLE_NAME, null, insertValues).toInt()
    }
}