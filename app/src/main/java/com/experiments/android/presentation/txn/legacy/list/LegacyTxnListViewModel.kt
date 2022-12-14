package com.experiments.android.presentation.txn.legacy.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.experiments.android.data.Result.Error
import com.experiments.android.data.Result.Success
import com.experiments.android.domain.repository.TxnRepository
import kotlinx.coroutines.launch

class LegacyTxnListViewModel(
    private val txnRepository: TxnRepository
) : ViewModel() {

    val transactions = txnRepository.observeTransactionsResult()

    /**
     * Variable that tells the Fragment to navigate to a specific
     * [com.experiments.android.presentation.txn.legacy.add_edit.LegacyTxnAddEditFragment]
     *
     * This is private because we don't want to expose setting this value to the Fragment.
     */
    private val _navigateToTxnAddEdit = MutableLiveData<Boolean>()

    /**
     * If this is true, immediately navigate to
     * [com.experiments.android.presentation.txn.legacy.add_edit.LegacyTxnAddEditFragment]
     * and call [doneNavigating]
     */
    val navigateToTxnAddEdit: LiveData<Boolean>
        get() = _navigateToTxnAddEdit

    private val _dataLoading = MutableLiveData(false)
    val dataLoading: LiveData<Boolean> = _dataLoading

    val error: LiveData<Boolean> = transactions.map { it is Error }
    val empty: LiveData<Boolean> = transactions.map { (it as? Success)?.data.isNullOrEmpty() }

    /**
     * Call this immediately after navigating to
     * [com.experiments.android.presentation.txn.legacy.add_edit.LegacyTxnAddEditFragment]
     * It will clear the navigation request, so if the device is rotated it won't navigate twice.
     */
    fun doneNavigating() {
        _navigateToTxnAddEdit.value = false
    }

    fun onFabClick() {
        _navigateToTxnAddEdit.value = true
    }

    fun refresh() {
        _dataLoading.value = true
        viewModelScope.launch {
            txnRepository.refreshTransactions()
            _dataLoading.value = false
        }
    }

    @Suppress("UNCHECKED_CAST")
    class TxnListViewModelFactory (
        private val txnRepository: TxnRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>) =
            LegacyTxnListViewModel(txnRepository) as T
    }
}
