package com.example.personaltasks.business

import android.content.Context
import com.example.personaltasks.repository.UsuarioRepository

/**
 * Essa classe é responsável por tratar toda a lógica de negócio do usuário
 */

class UsuarioBusiness (val context: Context) {
    //instancia a usuario repository e executa a função insert
    val mUsuarioRepository : UsuarioRepository = UsuarioRepository.getInstance(context)

    fun insert(nome: String, email: String, senha: String){
        val userId = mUsuarioRepository.insert(nome, email, senha)
        val  str = ""
    }
}