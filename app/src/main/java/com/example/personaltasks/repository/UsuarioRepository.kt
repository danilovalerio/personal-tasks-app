package com.example.personaltasks.repository

import android.content.Context

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
}