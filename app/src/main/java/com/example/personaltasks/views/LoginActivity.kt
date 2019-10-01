package com.example.personaltasks.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.personaltasks.R
import com.example.personaltasks.business.UsuarioBusiness
import com.example.personaltasks.util.toastLong
import com.example.personaltasks.util.toastShort
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mUsuarioBusiness : UsuarioBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Inicialização das variáveis
        mUsuarioBusiness = UsuarioBusiness(this)

        //Eventos
        setListeners()
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btnLogin -> fazerLogin()
        }
    }

    private fun setListeners() {
        btnLogin.setOnClickListener(this)
    }

    private fun fazerLogin(){
        val email = et_email.text.toString()
        val senha = et_senha.text.toString()

        if(mUsuarioBusiness.login(email, senha)) {
            toastLong("Login efetuado com sucesso!")
        } else {
           toastShort(getString(R.string.usuario_senha_incorretos))
        }

    }
}
