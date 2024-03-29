package com.example.personaltasks.constants

/**
 * Mapear as tabelas em constantes para evitar erros com identificação das mesmas
 */
class DataBaseConstants {

    object USUARIO {
        val TABLE_NAME = "usuario"

        object COLUMNS {
            val ID = "id"
            val NOME = "nome"
            val EMAIL = "email"
            val SENHA = "senha"
        }
    }

    object PRIORIDADE {
        val TABLE_NAME = "prioridade"

        object COLUMNS {
            val ID = "id"
            val DESCRICAO = "descricao"
        }
    }

    object TAREFA {
        val TABLE_NAME = "tarefa"

        object COLUMNS {
            val ID = "id"
            val USUARIOID = "usuarioid"
            val PRIORIDADEID = "prioridadeid"
            val DESCRICAO = "descricao"
            val DATAFIM= "datafim"
            val COMPLETA = "completa"
        }
    }
}