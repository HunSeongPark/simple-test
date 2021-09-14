package hunseong.com.simple_test_mvvm.di

import android.content.Context
import android.security.identity.ResultData
import androidx.room.Room
import hunseong.com.simple_test_mvvm.data.db.ResultDatabase

fun provideDB(context: Context) : ResultDatabase =
    Room.databaseBuilder(context, ResultDatabase::class.java, ResultDatabase.DB_NAME).build()

fun provideResultDao(database: ResultDatabase) = database.resultDao()