package com.xpense.android.presentation.txn.compose.add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xpense.android.XpenseApplication
import com.xpense.android.presentation.ui.theme.XpenseTheme

@ExperimentalMaterialApi
class ComposeTxnAddEditFragment : Fragment() {

    private val viewModel by viewModels<ComposeTxnAddEditViewModel> {
        ComposeTxnAddEditViewModel.TxnAddEditComposeViewModelFactory(
            ComposeTxnAddEditFragmentArgs.fromBundle(requireArguments()).transactionId,
            (requireContext().applicationContext as XpenseApplication).txnRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.navigateExit.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
                viewModel.doneNavigating()
            }
        }

        return ComposeView(requireContext()).apply {
            setContent {
                XpenseTheme {
                    TxnAddEditScreen()
                }
            }
        }
    }
}
