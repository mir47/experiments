package com.experiments.android.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.experiments.android.data.Result
import com.experiments.android.data.Result.Error
import com.experiments.android.data.Result.Success
import com.experiments.android.data.source.TxnDataSource
import com.experiments.android.data.source.local.model.TxnEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class TxnDataSourceLocal internal constructor(
    private val txnDao: TxnDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): TxnDataSource {
    override fun observeTransactionsResult(): LiveData<Result<List<TxnEntity>>> =
        txnDao.observeTransactions().map { Success(it) }

    override suspend fun saveTransaction(txnEntity: TxnEntity) =
        txnDao.insert(txnEntity)

    override suspend fun getTransactionResultById(txnId: Long) =
        txnDao.getTransactionWithId(txnId)?.let { Success(it) }
            ?: Error(Exception("Error"))

    override suspend fun getTransactionsResult(): Result<List<TxnEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactions(): List<TxnEntity> =
        txnDao.getAllTransactions()

    override suspend fun updateTransaction(txnEntity: TxnEntity) =
        txnDao.update(txnEntity)

    override suspend fun flagTransaction(txnId: Long, flagged: Boolean) =
        txnDao.updateFlagged(txnId, flagged)

    override suspend fun deleteAllTransactions() =
        txnDao.clear()
}
