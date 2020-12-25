package com.fedyshyn.taskapp.data.source.remote

import com.fedyshyn.taskapp.data.Task
import com.fedyshyn.taskapp.data.source.ITasksDataSource
import kotlinx.coroutines.flow.Flow

object TasksRemoteDataSource : ITasksDataSource {

    override fun getAllTasks(): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun saveTask(title: String, description: String) {
        TODO("Not yet implemented")
    }
}