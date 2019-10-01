package com.example.personaltasks.entities

/**
 * Classe somente de dados que servirá para transitar entre as camadas
 */
data class UsuarioEntity (val id: Int, var nome: String, var email: String, var senha: String = "")