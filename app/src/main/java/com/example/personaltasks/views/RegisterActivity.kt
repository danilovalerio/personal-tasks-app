package com.example.personaltasks.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.personaltasks.R
import com.example.personaltasks.business.UsuarioBusiness
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    //iniciará posteriormente
    private lateinit var mUsuarioBusiness : UsuarioBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Eventos
        setListeners()

        //Instanciar variáves da classe
        mUsuarioBusiness = UsuarioBusiness(this)
    }

    override fun onClick(view: View) {

        when(view.id){
            R.id.btnSalvar -> {
                Toast.makeText(this, "BTN SALVAR", Toast.LENGTH_SHORT).show()
                salvarUsuario()
            }
        }

    }

    private fun setListeners(){
        btnSalvar.setOnClickListener(this)

    }

    private fun salvarUsuario(){

        val nome = et_nome.text.toString()
        val email = et_email.text.toString()
        val senha = et_senha.text.toString()

        //Faz a inserção do usuário (ainda sem validações)
        mUsuarioBusiness.insert(nome, email, senha)

    }
}
