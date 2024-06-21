package data.model.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ui.painting.PaintingViewModel

class PaintingViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return PaintingViewModel(context) as T
    }
}