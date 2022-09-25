package com.experiments.android.presentation.xperiments.compose.playground

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.experiments.android.presentation.ui.theme.ExperimentsTheme

class ComposePlaygroundFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ExperimentsTheme {
                    ComposePlaygroundScreen()
                }
            }
        }
    }
}
