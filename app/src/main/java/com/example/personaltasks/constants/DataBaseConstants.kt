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
}