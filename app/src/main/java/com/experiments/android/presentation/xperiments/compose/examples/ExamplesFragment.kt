package com.experiments.android.presentation.xperiments.compose.examples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.experiments.android.presentation.ui.theme.ExperimentsTheme

@ExperimentalMaterialApi
class ExamplesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                ExperimentsTheme {
                    ExamplesScreen(
                        Modifier.padding(4.dp)
                    )
                }
            }
        }
    }
}
