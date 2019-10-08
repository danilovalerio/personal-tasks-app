package com.example.personaltasks.business

import android.content.Context
import com.example.personaltasks.entities.PrioridadeEntity
import com.example.personaltasks.repository.PrioridadeRepository

class PrioridadeBusiness (val context: Context){

    private val mPrioridadeRepository : PrioridadeRepository = PrioridadeRepository.getInstance(context)

    fun getList(): MutableList<PrioridadeEntity> = mPrioridadeRepository.getList()

}