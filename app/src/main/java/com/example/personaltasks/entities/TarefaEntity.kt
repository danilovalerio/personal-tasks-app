package com.example.personaltasks.entities

data class TarefaEntity(
    val id: Int,
    val usuarioid: Int,
    val prioridadeid: Int,
    val descricao: String,
    val datafim: String,
    val completa: Boolean
)

