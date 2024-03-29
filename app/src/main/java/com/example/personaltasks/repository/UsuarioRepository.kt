package com.example.personaltasks.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.personaltasks.constants.DataBaseConstants
import com.example.personaltasks.entities.UsuarioEntity

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

    fun get(email: String, senha: String) : UsuarioEntity? {
        var usuarioEntity: UsuarioEntity? = null
        try {
            val cursor: Cursor
            val db = mPersonalTasksDataBaseHelper.readableDatabase

            //Itens esperados no retorno
            val projecao = arrayOf(DataBaseConstants.USUARIO.COLUMNS.ID,
                                                DataBaseConstants.USUARIO.COLUMNS.NOME,
                                                DataBaseConstants.USUARIO.COLUMNS.EMAIL,
                                                DataBaseConstants.USUARIO.COLUMNS.SENHA)

            //Qual o filtro que temos que aplicar
            val selecao = "${DataBaseConstants.USUARIO.COLUMNS.EMAIL} = ? AND ${DataBaseConstants.USUARIO.COLUMNS.EMAIL} = ? "
            val selecaoArgumentos = arrayOf(email, senha)

            //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
            cursor = db.query(DataBaseConstants.USUARIO.TABLE_NAME,projecao,selecao,selecaoArgumentos,null,null,null)
            if (cursor.count > 0){
                //cursor vai para primeira linha
                cursor.moveToFirst()
                //pegar dados do cursos
                val userId = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.USUARIO.COLUMNS.ID))
                val nome = cursor.getString(cursor.getColumnIndex(DataBaseConstants.USUARIO.COLUMNS.NOME))
                val email = cursor.getString(cursor.getColumnIndex(DataBaseConstants.USUARIO.COLUMNS.EMAIL))

                //preenche a entidade usuário
                usuarioEntity = UsuarioEntity(userId, nome, email)

            }

            cursor.close()
        } catch (e: Exception){
            return usuarioEntity
        }

        return usuarioEntity

    }

    fun emailExistente(email: String) : Boolean{
        val retorno: Boolean //inicializa como false
        try {

            val cursor: Cursor
            val db = mPersonalTasksDataBaseHelper.readableDatabase

            //Itens esperados no retorno
            val projecao = arrayOf(DataBaseConstants.USUARIO.COLUMNS.ID)

            //Qual o filtro que temos que aplicar
            val selecao = "${DataBaseConstants.USUARIO.COLUMNS.EMAIL} = ?"
            val selecaoArgumentos = arrayOf(email)

            //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
            cursor = db.query(
                DataBaseConstants.USUARIO.TABLE_NAME,
                projecao,
                selecao,
                selecaoArgumentos,
                null,
                null,
                null
            )
            //db.rawQuery("select * from user where email = danilo", null)
            retorno = cursor.count > 0
            cursor.close()
        } catch (e: Exception){
            throw e
        }
        return retorno


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