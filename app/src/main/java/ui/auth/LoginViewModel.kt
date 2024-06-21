package ui.auth
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(context: Context) : ViewModel(){

    private val userPreferences = User(context)

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(email: String, password: String){
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            //Simulate network call
            kotlinx.coroutines.delay(1000)

            val savedUser = userPreferences.getUser()
            if (savedUser.first == email && savedUser.second == password){
                _loginState.value = LoginState.Success
            } else{
                _loginState.value = LoginState.Error("Invalid email or password")
            }
        }
    }

}

sealed class LoginState{
    object Idle : LoginState()
    object Loading : LoginState()
    object Success : LoginState()
    data class Error(val message: String) : LoginState()
}