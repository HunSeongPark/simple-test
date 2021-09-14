package hunseong.com.simple_test_mvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import hunseong.com.simple_test_mvvm.data.entity.ResultEntity

@Database(
    entities = [ResultEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ResultDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "ResultDatabase.db"
    }

    abstract fun resultDao(): ResultDao
}