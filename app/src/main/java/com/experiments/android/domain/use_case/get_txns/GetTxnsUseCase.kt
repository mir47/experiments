package com.experiments.android.domain.use_case.get_txns

import com.experiments.android.common.Resource
import com.experiments.android.domain.model.Txn
import com.experiments.android.domain.repository.TxnRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetTxnsUseCase(
    private val txnRepo: TxnRepository
) {
    operator fun invoke(): Flow<Resource<List<Txn>>> = flow {
        try {
            emit(Resource.Loading())
            val txns = txnRepo.getTransactions()
            emit(Resource.Success(txns))
        } catch (e: IOException) {
            emit(Resource.Error("error"))
        }
    }
}
