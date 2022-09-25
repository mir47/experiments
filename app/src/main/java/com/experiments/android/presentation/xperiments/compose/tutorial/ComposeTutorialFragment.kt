package com.experiments.android.presentation.xperiments.compose.tutorial

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.experiments.android.presentation.ui.theme.ExperimentsTheme

class ComposeTutorialFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ExperimentsTheme {
                    // single message
//                    MessageCard(Message("Android", "Jetpack Compose"))

                    // list of messages
                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }
}
