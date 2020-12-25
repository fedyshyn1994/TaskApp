package com.fedyshyn.taskapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "tasks")
data class Task @JvmOverloads constructor(
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "description") var description: String = "",
    @ColumnInfo(name = "completed") var completed: Boolean = false
)