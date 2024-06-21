package data.model.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ui.auth.RegisterViewModel

class RegisterViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return RegisterViewModel(context) as T
    }
}