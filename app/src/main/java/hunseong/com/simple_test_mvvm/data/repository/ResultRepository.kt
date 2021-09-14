package hunseong.com.simple_test_mvvm.data.repository

import hunseong.com.simple_test_mvvm.data.entity.ResultEntity

interface ResultRepository {

    suspend fun getAllResult(): List<ResultEntity>

    suspend fun insertResult(resultEntity: ResultEntity)
}