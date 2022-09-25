package com.experiments.android.presentation.txn.compose.list

import com.experiments.android.domain.model.Txn

data class TxnListState(
    val isLoading: Boolean = false,
    val txns: List<Txn> = emptyList(),
    val error: String = ""
)
