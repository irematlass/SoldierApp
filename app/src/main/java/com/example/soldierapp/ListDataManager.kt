package com.example.soldierapp

import android.content.Context
import androidx.preference.PreferenceManager


class ListDataManager(private val context: Context) {

    fun saveList(listTag: String, list: ArrayList<String>) {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPrefs.putStringSet(listTag, list.toHashSet())
        sharedPrefs.apply()
    }

    fun readLists(listTag: String): ArrayList<String> {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val contents = sharedPrefs.getStringSet(listTag, null)
        val returnList = ArrayList<String>()
        if (contents != null) {
            returnList.addAll(contents)
        }

        return returnList
    }

    fun deleteLists() {
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPrefs.edit().clear().commit()
    }
}