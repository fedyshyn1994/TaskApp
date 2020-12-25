package com.fedyshyn.taskapp

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.fedyshyn.taskapp.data.Task
import com.fedyshyn.taskapp.data.source.ITasksRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TasksViewModel @ViewModelInject constructor(
    private val tasksRepository: ITasksRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            tasksRepository.saveTask("title 1", "desc")
            tasksRepository.saveTask("title 2", "desc")
        }
    }

    private val _items: LiveData<List<Task>> = tasksRepository.getAllTasks().asLiveData(Dispatchers.IO)

    val items: LiveData<List<Task>> = _items
}