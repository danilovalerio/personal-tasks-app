package com.example.personaltasks.repository

import android.content.Context
import android.database.Cursor
import com.example.personaltasks.constants.DataBaseConstants
import com.example.personaltasks.entities.PrioridadeEntity
import java.lang.Exception

class PrioridadeRepository private constructor(context: Context){
    //para acessar o banco
    private var mPersonalTasksDataBaseHelper : PersonalTasksDataBaseHelper = PersonalTasksDataBaseHelper(context)

    companion object{
        fun getInstance(context: Context) : PrioridadeRepository {
            if(INSTANCE == null){
                INSTANCE = PrioridadeRepository(context)
            }
            return INSTANCE as PrioridadeRepository
        }
        //variável que armazena a instância da classe
        private var INSTANCE: PrioridadeRepository? = null
    }

    //Lita de prioridades disponíveis
    fun getList(): MutableList<PrioridadeEntity>{
        val lista = mutableListOf<PrioridadeEntity>()
        try {
            val cursor: Cursor
            val db = mPersonalTasksDataBaseHelper.readableDatabase

            cursor = db.rawQuery("SELECT * FROM ${DataBaseConstants.PRIORIDADE.TABLE_NAME}", null)//sem filtro no 2º argumento
            if(cursor.count > 0){
                cursor.moveToFirst()

                while (cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.PRIORIDADE.COLUMNS.ID))
                    val descricao = cursor.getString(cursor.getColumnIndex(DataBaseConstants.PRIORIDADE.COLUMNS.DESCRICAO))
                    //Adiciona o item prioridade à lista
                    lista.add(PrioridadeEntity(id, descricao))

                }
            }
            cursor.close()
        } catch (e: Exception){
            //caso der erro retornamos a lista vazia
            return lista
        }

        return lista
    }


}