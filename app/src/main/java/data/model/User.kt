package data.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class User(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    companion object{
        private const val TAG = "UserPreferences"
        private const val KEY_EMAIL = "email"
        private const val KEY_PASSWORD = "pass"
    }

    fun saveUser(email: String, password: String){
        Log.d(TAG, "saveUser called with email: $email and password: $password")
        preferences.edit().apply{
            putString(KEY_EMAIL,email)
            putString(KEY_PASSWORD,password)
            apply()
        }
        Log.d(TAG, "User saved: $email with password: $password")
    }

    fun getUser():Pair<String?,String?>{
        val email = "email"
        val password = "pass"
        Log.d(TAG, "User retrieved: $email with password: $password")
        return Pair(email,password)
    }

    fun clearUser(){
        preferences.edit().clear().apply()
        Log.d(TAG, "User cleared")
    }
}