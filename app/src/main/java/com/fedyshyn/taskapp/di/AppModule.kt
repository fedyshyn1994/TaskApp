package com.fedyshyn.taskapp.di

import android.content.Context
import androidx.room.Room
import com.fedyshyn.taskapp.data.source.ITasksDataSource
import com.fedyshyn.taskapp.data.source.ITasksRepository
import com.fedyshyn.taskapp.data.source.TasksRepository
import com.fedyshyn.taskapp.data.source.local.TaskDatabase
import com.fedyshyn.taskapp.data.source.local.TasksLocalDataSource
import com.fedyshyn.taskapp.data.source.remote.TasksRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RemoteTasksDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class LocalTasksDataSource

    @Singleton
    @RemoteTasksDataSource
    @Provides
    fun provideTasksRemoteDataSource(): ITasksDataSource = TasksRemoteDataSource

    @Singleton
    @LocalTasksDataSource
    @Provides
    fun provideTasksLocalDataSource(
        database: TaskDatabase,
        ioDispatcher: CoroutineDispatcher
    ): ITasksDataSource = TasksLocalDataSource(database.tasksDao(), ioDispatcher)

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            TaskDatabase::class.java,
            "tasks.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

    @Module
    @InstallIn(ApplicationComponent::class)
    object TasksRepositoryModule {

        @Singleton
        @Provides
        fun provideTasksRepository(
            @AppModule.RemoteTasksDataSource remoteTasksDataSource: ITasksDataSource,
            @AppModule.LocalTasksDataSource localTasksDataSource: ITasksDataSource,
            ioDispatcher: CoroutineDispatcher): ITasksRepository = TasksRepository(remoteTasksDataSource, localTasksDataSource, ioDispatcher)
    }
}