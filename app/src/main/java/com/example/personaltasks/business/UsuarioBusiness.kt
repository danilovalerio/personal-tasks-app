package com.example.personaltasks.business

import android.content.Context
import com.example.personaltasks.repository.UsuarioRepository
import com.example.personaltasks.util.ValidationException
import java.lang.Exception

/**
 * Essa classe é responsável por tratar toda a lógica de negócio do usuário
 */

class UsuarioBusiness (val context: Context) {
    //instancia a usuario repository e executa a função insert
    val mUsuarioRepository : UsuarioRepository = UsuarioRepository.getInstance(context)

    fun insert(nome: String, email: String, senha: String){

       try {
           if(nome.isNullOrEmpty() || email.isNullOrEmpty() || senha.isNullOrEmpty() ){
               throw ValidationException("Informe os campos!")
           }

           val userId = mUsuarioRepository.insert(nome, email, senha)

       } catch (e: Exception){
           throw e
       }
    }
}