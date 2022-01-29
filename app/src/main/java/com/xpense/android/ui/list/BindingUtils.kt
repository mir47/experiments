package com.xpense.android.ui.list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.xpense.android.db.Transaction

@BindingAdapter("transactionAmountString")
fun TextView.setTransactionAmountString(item: Transaction?) {
    item?.let {
        text = item.amount.toString()
    }
}
