package com.example.personaltasks.repository

/**
 * Responsável pela inserção da classe usuário
 * Para evitar concorrência todas as classes repository deve ser Singleton
 * Regras singleton:
 *  - Construtor tem que ser privado
 *  - Tenha um método que retorna a instância da classe (getInstance)
 */

class UsuarioRepository private constructor(){

    companion object{
        fun getInstance() : UsuarioRepository {
            if(INSTANCE == null){
                INSTANCE = UsuarioRepository()
            }
            return INSTANCE as UsuarioRepository
        }
        //variável que armazena a instância da classe
        private var INSTANCE: UsuarioRepository? = null
    }
}