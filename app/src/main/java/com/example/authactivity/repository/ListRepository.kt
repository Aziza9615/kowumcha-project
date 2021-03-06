package com.example.authactivity.repository

import androidx.lifecycle.MutableLiveData
import com.example.authactivity.database.ListDao
import com.example.authactivity.model.ContactData
import com.example.authactivity.model.ListData

interface ListRepository{
    fun getList()
    fun insertList(data: ListData)
    fun updateList(data: ListData)
    fun restoreList(data: ListData)
    fun deleteList(data: ListData)
}

class ListRepositoryImpl(private val database: ListDao): ListRepository {

    val data: MutableLiveData<MutableList<ListData>> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()

    override fun getList() {
        data.value = database.getList()
    }

    override fun insertList(data: ListData) {
        database.insertList(data)
    }

    override fun updateList(data: ListData) {
        database.updateItem(data)
    }

    override fun restoreList(data: ListData) {
        database.restoreList(data)
    }

    override fun deleteList(data: ListData) {
        database.deleteList(data)
    }
}