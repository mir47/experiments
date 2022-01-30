package com.xpense.android.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xpense.android.databinding.ItemTransactionBinding
import com.xpense.android.db.Transaction

class TransactionAdapter :
    ListAdapter<Transaction, TransactionAdapter.TransactionViewHolder>(TransactionDiffCallback()) {

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TransactionViewHolder(ItemTransactionBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))

    class TransactionViewHolder(private val binding: ItemTransactionBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Transaction) {
            binding.transaction = item
//            binding.executePendingBindings()
        }
    }

    /**
     * Callback for calculating the diff between two non-null items in a list.
     *
     * Used by ListAdapter to calculate the minimum number of changes between
     * and old list and a new list that's been passed to `submitList`.
     */
    class TransactionDiffCallback : DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction) =
            oldItem.transactionId == newItem.transactionId

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction) =
            oldItem == newItem
    }
}
