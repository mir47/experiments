package com.experiments.android.presentation.txn.legacy.add_edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.experiments.android.ExperimentsApplication
import com.experiments.android.databinding.FragmentLegacyTxnAddEditBinding

class LegacyTxnAddEditFragment : Fragment() {

    private val vm by viewModels<LegacyTxnAddEditViewModel> {
        LegacyTxnAddEditViewModel.LegacyTxnAddEditViewModelFactory(
            LegacyTxnAddEditFragmentArgs.fromBundle(requireArguments()).transactionId,
            LegacyTxnAddEditFragmentArgs.fromBundle(requireArguments()).sms,
            (requireContext().applicationContext as ExperimentsApplication).txnRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentLegacyTxnAddEditBinding.inflate(inflater)

        binding.viewModel = vm

        // Make data binding lifecycle aware, to automatically update layout with LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        vm.navigateExit.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().popBackStack()
                vm.doneNavigating()
            }
        }

        return binding.root
    }
}
