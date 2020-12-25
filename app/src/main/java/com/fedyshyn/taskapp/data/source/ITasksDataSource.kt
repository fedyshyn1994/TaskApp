package com.fedyshyn.taskapp.data.source

import com.fedyshyn.taskapp.data.Task
import kotlinx.coroutines.flow.Flow

interface ITasksDataSource {

    fun getAllTasks(): Flow<List<Task>>

    fun saveTask(title: String, description: String)
}