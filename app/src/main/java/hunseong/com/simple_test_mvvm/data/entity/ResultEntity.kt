package hunseong.com.simple_test_mvvm.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResultEntity(
    @PrimaryKey val id: Long,
    val percent: String,
    val result: String
)