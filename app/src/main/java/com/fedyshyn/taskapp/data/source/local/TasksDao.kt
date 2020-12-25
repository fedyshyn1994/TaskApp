package com.fedyshyn.taskapp.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fedyshyn.taskapp.data.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTask(task: Task)
}