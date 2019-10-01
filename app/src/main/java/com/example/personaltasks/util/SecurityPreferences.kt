package com.example.personaltasks.util

import android.content.Context
import android.content.SharedPreferences

/**
 * Usar o shared preferences para armazenar id, nome e e-mail do usuário cadastrado
 */
class SecurityPreferences (context: Context) {

    private val mSharedPreferences: SharedPreferences = context.getSharedPreferences("personalTasks", Context.MODE_PRIVATE) //mode private somente acessível para este app

    fun storeSrings(key: String, value: String){
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun getStoredStrings(key: String) : String? {
        return mSharedPreferences.getString(key, "")
    }

    fun removeStoredStrings(key: String){
        mSharedPreferences.edit().remove(key).apply()
    }
}