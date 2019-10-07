package com.example.personaltasks.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.personaltasks.R
import com.example.personaltasks.business.UsuarioBusiness
import com.example.personaltasks.constants.PersonalTasksConstants
import com.example.personaltasks.util.SecurityPreferences
import com.example.personaltasks.util.toastLong
import com.example.personaltasks.util.toastShort
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUsuarioBusiness : UsuarioBusiness
    private lateinit var mSecurityPreferences :SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Inicialização das variáveis
        mUsuarioBusiness = UsuarioBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)

        //Eventos
        setListeners()

        verificaUsuarioLogado()
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btnLogin -> fazerLogin()
        }
    }

    private fun setListeners() {
        btnLogin.setOnClickListener(this)
    }

    private fun verificaUsuarioLogado(){
        val userid = mSecurityPreferences.getStoredStrings(PersonalTasksConstants.KEY.USER_ID)
        val usernome = mSecurityPreferences.getStoredStrings(PersonalTasksConstants.KEY.USER_NOME)

        //usuário logado
        if(!userid.isNullOrEmpty() && !usernome.isNullOrEmpty()){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun fazerLogin(){
        val email = et_email.text.toString()
        val senha = et_senha.text.toString()

        if(mUsuarioBusiness.login(email, senha)) {
            toastLong("Login efetuado com sucesso!")
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
           toastShort(getString(R.string.usuario_senha_incorretos))
        }
    }


}
