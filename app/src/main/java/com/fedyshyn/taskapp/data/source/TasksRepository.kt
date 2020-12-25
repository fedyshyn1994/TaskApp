package com.fedyshyn.taskapp.data.source

import com.fedyshyn.taskapp.data.Task
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class TasksRepository(
    private val tasksRemoteDataSource: ITasksDataSource,
    private val tasksLocalDataSource: ITasksDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ITasksRepository {

    override fun getAllTasks(): Flow<List<Task>> {
        return tasksLocalDataSource.getAllTasks()
    }

    override fun saveTask(title: String, description: String) {
        tasksLocalDataSource.saveTask(title, description)
    }
}