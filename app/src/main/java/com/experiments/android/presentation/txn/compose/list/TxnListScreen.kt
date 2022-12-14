package com.experiments.android.presentation.txn.compose.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.experiments.android.presentation.txn.compose.list.components.TxnListItem

@ExperimentalMaterialApi
@Composable
fun TxnListScreen(
    navController: NavController,
    vm: ComposeTxnListViewModel
) {

    val state = vm.state.value

//    Surface {
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.txns) { txn ->
                    TxnListItem(
                        txn = txn,
                        onItemClick = {
                            navController.navigate(
                                ComposeTxnListFragmentDirections
                                    .actionComposeTxnListFragmentToComposeTxnAddEditFragment()
                                    .setTransactionId(txn.id)
                            )
                        }
                    )
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.BottomEnd),
                onClick = {
                    navController.navigate(
                        ComposeTxnListFragmentDirections
                            .actionComposeTxnListFragmentToComposeTxnAddEditFragment()
                    )
                }
            ) {
                Image(
                    painter = painterResource(android.R.drawable.ic_input_add),
                    contentDescription = "FAB"
                )
            }

            if (state.error.isNotBlank()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
//    }
}
