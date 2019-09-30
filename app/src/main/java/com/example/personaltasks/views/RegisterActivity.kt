package com.example.personaltasks.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.personaltasks.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        setListeners()
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

    }
}
