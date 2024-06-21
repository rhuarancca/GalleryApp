package data.storage

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "settings")

class DataStoreManager(private val context: Context) {

    companion object {
        val SELECTED_OPTION_KEY = stringPreferencesKey("selected_option")      
    }

    val selectedOptionFlow: Flow<String> by lazy {
        val map = context.dataStore.javaClass
            .map<Any>(transform = { preferences ->
                preferences[SELECTED_OPTION_KEY] ?: "Option 1"
            })
        map
    }

    suspend fun saveSelectedOption(option: String) {
        context.dataStore.edit { preferences -> preferences[SELECTED_OPTION_KEY] = option }
    }
}

private fun <T> Class<T>.map(transform: T): Flow<String> {
    TODO("Not yet implemented")
}

fun preferencesDataStore(name: String): Any {

}
private fun stringPreferencesKey(s: String): Any {

}
private operator fun Any.getValue(context: Context, property: KProperty<*>): Any {

}