package com.example.personaltasks.business

import android.content.Context
import com.example.personaltasks.R
import com.example.personaltasks.constants.PersonalTasksConstants
import com.example.personaltasks.entities.UsuarioEntity
import com.example.personaltasks.repository.UsuarioRepository
import com.example.personaltasks.util.SecurityPreferences
import com.example.personaltasks.util.ValidationException
import java.lang.Exception

/**
 * Essa classe é responsável por tratar toda a lógica de negócio do usuário
 */

class UsuarioBusiness (val context: Context) {
    //instancia a usuario repository e executa a função insert
    private val mUsuarioRepository : UsuarioRepository = UsuarioRepository.getInstance(context)
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)

    fun login(email: String, senha: String) : Boolean{
        val user: UsuarioEntity? = mUsuarioRepository.get(email, senha)
        return if(user != null){
            mSecurityPreferences.storeSrings(PersonalTasksConstants.KEY.USER_ID, user.id.toString())
            mSecurityPreferences.storeSrings(PersonalTasksConstants.KEY.USER_NOME, user.nome)
            mSecurityPreferences.storeSrings(PersonalTasksConstants.KEY.USER_EMAIL, user.email)
            true
        } else {
            false
        }
    }

    fun insert(nome: String, email: String, senha: String){

       try {
           if(nome.isNullOrEmpty() || email.isNullOrEmpty() || senha.isNullOrEmpty() ){
               throw ValidationException("Informe os campos!")
           }

           if(mUsuarioRepository.emailExistente(email)){
               throw ValidationException(context.getString(R.string.erro_email_existente))
           }

           val userId = mUsuarioRepository.insert(nome, email, senha)

           //salvar dados do usuário no shared preferences
           mSecurityPreferences.storeSrings(PersonalTasksConstants.KEY.USER_ID, userId.toString())
           mSecurityPreferences.storeSrings(PersonalTasksConstants.KEY.USER_NOME, nome)
           mSecurityPreferences.storeSrings(PersonalTasksConstants.KEY.USER_EMAIL, email)



       } catch (e: Exception){
           throw e
       }
    }
}