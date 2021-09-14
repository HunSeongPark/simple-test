package hunseong.com.simple_test_mvvm.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hunseong.com.simple_test_mvvm.data.entity.ResultEntity

@Dao
interface ResultDao {

    @Query("SELECT * FROM ResultEntity")
    suspend fun getAllResult(): List<ResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResult(resultEntity: ResultEntity)
}