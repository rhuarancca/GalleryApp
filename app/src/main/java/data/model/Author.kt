package data.model

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class Author(contex: Context) {

    private val preferences: SharedPreferences =
        contex.getSharedPreferences("author_prefs", Context.MODE_PRIVATE)
    companion object {
        private const val TAG = "AuthorPreferences"
        private const val NAME = "name"
        private const val BIOGRAPHY = "Lore ipsum"
    }

    fun getAuthor():Pair<String?, String?>{
        val name = preferences.getString(NAME, null)
        val biography = preferences.getString(BIOGRAPHY, null)
        Log.d(TAG, "Author retrieved: $name with biograpghy $biography")

        return Pair(name, biography)

    }
}