package com.fedyshyn.taskapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fedyshyn.taskapp.data.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun tasksDao(): TasksDao
}