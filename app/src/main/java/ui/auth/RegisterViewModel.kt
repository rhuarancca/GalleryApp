package ui.auth

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel(context: Context) : ViewModel() {

    private val userPreferences = User(context)
    private val TAG = "RegisterViewModel"

    private val _registerState = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val registerState: StateFlow<RegisterState> get()= _registerState

    fun register(email: String, password: String, confirmPassword: String){
        viewModelScope.launch {
            _registerState.value = RegisterState.Loading
            Log.d(TAG, "register called with email: $email and password: $password ")
            // Simulate network call
            kotlinx.coroutines.delay(1000)
            if (password == confirmPassword){
                // Simulate a successful registration
                Log.d(TAG, "Passwords match, calling saveUser")
                userPreferences.saveUser(email,password)
                Log.d(TAG, "After saveUser call")
                _registerState.value = RegisterState.Success
            }else{
                Log.d(TAG, "Passwords do not match")
                _registerState.value = RegisterState.Error("Password do not match")
            }


        }
    }
}

sealed class RegisterState{
    object Idle : RegisterState()
    object Loading : RegisterState()
    object Success : RegisterState()
    data class Error(val message: String) : RegisterState()
}