package com.example.personaltasks.repository

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.personaltasks.constants.DataBaseConstants
import com.example.personaltasks.entities.TarefaEntity

class TarefaRepository private constructor(context: Context) {

    private var mPersonalTasksDataBaseHelper: PersonalTasksDataBaseHelper =
        PersonalTasksDataBaseHelper(context)

    companion object {
        fun getInstance(context: Context): TarefaRepository {
            if (INSTANCE == null) {
                INSTANCE = TarefaRepository(context)
            }
            return INSTANCE as TarefaRepository
        }

        //variável que armazena a instância da classe
        private var INSTANCE: TarefaRepository? = null
    }

    //Obtém a lista de tarefas
    fun getList(usuarioId: Int): MutableList<TarefaEntity> {
        val lista = mutableListOf<TarefaEntity>()
        try {
            val cursor: Cursor
            val db = mPersonalTasksDataBaseHelper.readableDatabase

            cursor = db.rawQuery(
                "SELECT * FROM ${DataBaseConstants.TAREFA.TABLE_NAME}+" +
                        "WHERE ${DataBaseConstants.TAREFA.COLUMNS.USUARIOID} = usuarioId", null
            )

            if (cursor.count > 0) {
                cursor.moveToFirst()

                while (cursor.moveToNext()) {
                    val id =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.ID))
                    val descricao =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.DESCRICAO))
                    val prioridadeid =
                        cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.PRIORIDADEID))
                    val datafim =
                        cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.DATAFIM))
                    val completa =
                        (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.COMPLETA)) == 1)

                    //Adiciona tarefa à lista
                    lista.add(
                        TarefaEntity(
                            id,
                            usuarioId,
                            prioridadeid,
                            descricao,
                            datafim,
                            completa
                        )
                    )
                }
            }
            cursor.close()
        } catch (e: Exception) {
            //caso der erro retornamos a lista vazia
            return lista
        }

        return lista
    }

    fun get(id: Int): TarefaEntity? {
        var tarefaEntity: TarefaEntity? = null
        try {
            val cursor: Cursor
            val db = mPersonalTasksDataBaseHelper.readableDatabase

            //Itens esperados no retorno
            val projecao = arrayOf(
                DataBaseConstants.TAREFA.COLUMNS.ID,
                DataBaseConstants.TAREFA.COLUMNS.USUARIOID,
                DataBaseConstants.TAREFA.COLUMNS.PRIORIDADEID,
                DataBaseConstants.TAREFA.COLUMNS.DESCRICAO,
                DataBaseConstants.TAREFA.COLUMNS.DATAFIM,
                DataBaseConstants.TAREFA.COLUMNS.COMPLETA
            )

            //Qual o filtro que temos que aplicar
            val selecao = "${DataBaseConstants.TAREFA.COLUMNS.ID} = ? "
            val selecaoArgumentos = arrayOf(id.toString())

            //query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
            cursor = db.query(
                DataBaseConstants.TAREFA.TABLE_NAME,
                projecao,
                selecao,
                selecaoArgumentos,
                null,
                null,
                null
            )
            if (cursor.count > 0) {
                //cursor vai para primeira linha
                cursor.moveToFirst()
                //pegar dados do cursos
                val tarefaid =
                    cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.ID))
                val usuarioid =
                    cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.USUARIOID))
                val descricao =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.DESCRICAO))
                val prioridadeid =
                    cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.PRIORIDADEID))
                val datafim =
                    cursor.getString(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.DATAFIM))
                val completa =
                    (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.TAREFA.COLUMNS.COMPLETA)) == 1)

                //preenche a entidade usuário
                tarefaEntity =
                    TarefaEntity(tarefaid, usuarioid, prioridadeid, descricao, datafim, completa)

            }

            cursor.close()
        } catch (e: Exception) {
            return tarefaEntity
        }

        return tarefaEntity

    }

    fun insert(tarefa: TarefaEntity) {
        try {

            //ações no banco readableDatabase: SELECT, writableDatabase: INSERT, DELETE, UPDATE
            val db = mPersonalTasksDataBaseHelper.writableDatabase //abre conexão para escrita

            val completa: Int = if (tarefa.completa) 1 else 0

            val insertValues = ContentValues()
            insertValues.put(DataBaseConstants.TAREFA.COLUMNS.USUARIOID, tarefa.usuarioid)
            insertValues.put(DataBaseConstants.TAREFA.COLUMNS.PRIORIDADEID, tarefa.prioridadeid)
            insertValues.put(DataBaseConstants.TAREFA.COLUMNS.DATAFIM, tarefa.datafim)
            insertValues.put(DataBaseConstants.TAREFA.COLUMNS.DESCRICAO, tarefa.descricao)
            insertValues.put(DataBaseConstants.TAREFA.COLUMNS.COMPLETA, completa)

            db.insert(DataBaseConstants.TAREFA.TABLE_NAME, null, insertValues)

        } catch (e: Exception) {
            throw e
        }
    }

    fun update(tarefa: TarefaEntity) {
        try {

            //ações no banco readableDatabase: SELECT, writableDatabase: INSERT, DELETE, UPDATE
            val db = mPersonalTasksDataBaseHelper.writableDatabase //abre conexão para escrita

            val completa: Int = if (tarefa.completa) 1 else 0

            val updateValues = ContentValues()
            updateValues.put(DataBaseConstants.TAREFA.COLUMNS.USUARIOID, tarefa.usuarioid)
            updateValues.put(DataBaseConstants.TAREFA.COLUMNS.PRIORIDADEID, tarefa.prioridadeid)
            updateValues.put(DataBaseConstants.TAREFA.COLUMNS.DATAFIM, tarefa.datafim)
            updateValues.put(DataBaseConstants.TAREFA.COLUMNS.DESCRICAO, tarefa.descricao)
            updateValues.put(DataBaseConstants.TAREFA.COLUMNS.COMPLETA, completa)

            //Qual o filtro que temos que aplicar
            val selecao = "${DataBaseConstants.TAREFA.COLUMNS.ID} = ? "
            val selecaoArgumentos = arrayOf(tarefa.id.toString())

            db.update(DataBaseConstants.TAREFA.TABLE_NAME, updateValues, selecao, selecaoArgumentos)

        } catch (e: Exception) {
            throw e
        }
    }

    fun delete(tarefaId: Int) {
        try {
            val db = mPersonalTasksDataBaseHelper.writableDatabase

            val where = "${DataBaseConstants.TAREFA.COLUMNS.ID} = ? "
            val whereArgumentos = arrayOf(tarefaId.toString())

            db.delete(DataBaseConstants.TAREFA.TABLE_NAME, where, whereArgumentos)

        } catch (e: Exception) {
            throw e
        }

    }


}