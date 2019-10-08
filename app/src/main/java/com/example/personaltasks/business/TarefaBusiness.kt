package com.example.personaltasks.business

import android.content.Context
import com.example.personaltasks.entities.TarefaEntity
import com.example.personaltasks.repository.TarefaRepository

class TarefaBusiness (val context: Context){

    private val mTarefaRepository : TarefaRepository = TarefaRepository.getInstance(context)

    fun getList(usuarioId: Int): MutableList<TarefaEntity> = mTarefaRepository.getList(usuarioId)


}