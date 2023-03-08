package uz.ghost.myapplication1.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uz.ghost.myapplication1.models.User
import java.util.jar.Attributes.Name

object Mysherefrens {
    private const val NAME = "catch_file_name"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }
    var userList:ArrayList<User>
    get() = gsonStringToArreyList(preferences.getString("my_list","[]")!!)
    set(value) = preferences.edit {
            it.putString("my_list", listToString(value))
    }

    fun gsonStringToArreyList(gsonString : String):ArrayList<User>{
        val list = ArrayList<User>()
        val type = object  : TypeToken<ArrayList<User>>(){}.type
        list.addAll(Gson().fromJson(gsonString , type))
        return list
    }

    fun listToString(list:ArrayList<User>):String {
        return  Gson().toJson(list)
    }
}