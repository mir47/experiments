package com.experiments.android.presentation.txn.compose.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.experiments.android.ExperimentsApplication
import com.experiments.android.domain.use_case.get_txns.GetTxnsUseCase
import com.experiments.android.presentation.ui.theme.ExperimentsTheme

@ExperimentalMaterialApi
class ComposeTxnListFragment : Fragment() {

    private val vm by viewModels<ComposeTxnListViewModel> {
        ComposeTxnListViewModel.ComposeTxnListViewModelFactory(
            GetTxnsUseCase(
                (requireContext().applicationContext as ExperimentsApplication).txnRepository
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ExperimentsTheme {
                    TxnListScreen(findNavController(), vm)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        vm.getTxns()
    }
}
