package hunseong.com.simple_test_mvvm.data.repository

import hunseong.com.simple_test_mvvm.data.db.ResultDao
import hunseong.com.simple_test_mvvm.data.entity.ResultEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ResultRepositoryImpl(
    private val resultDao: ResultDao,
    private val ioDispatcher: CoroutineDispatcher
) : ResultRepository {

    override suspend fun getAllResult(): List<ResultEntity> = withContext(ioDispatcher) {
        resultDao.getAllResult()
    }

    override suspend fun insertResult(resultEntity: ResultEntity) = withContext(ioDispatcher) {
        resultDao.insertResult(resultEntity)
    }
}