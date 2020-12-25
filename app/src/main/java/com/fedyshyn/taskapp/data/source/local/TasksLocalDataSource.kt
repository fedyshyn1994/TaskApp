package com.fedyshyn.taskapp.data.source.local

import com.fedyshyn.taskapp.data.Task
import com.fedyshyn.taskapp.data.source.ITasksDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class TasksLocalDataSource internal constructor(
    private val tasksDao: TasksDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ITasksDataSource {

    override fun getAllTasks(): Flow<List<Task>> = tasksDao.getAllTasks()

    override fun saveTask(title: String, description: String) {
        val newTask = Task(title = title, description = description)
        tasksDao.saveTask(newTask)
    }
}