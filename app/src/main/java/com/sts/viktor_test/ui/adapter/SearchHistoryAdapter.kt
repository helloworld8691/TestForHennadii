package com.sts.viktor_test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sts.viktor_test.databinding.ItemSearchHistoryBinding

class SearchHistoryAdapter(private val listener: Listener) : RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>() {

    private val allData = mutableListOf<String>()

    fun update(list : List<String>){
        allData.clear()
        allData.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder {
        val binding = ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHistoryViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int) {
        holder.bind(allData[position])
    }

    override fun getItemCount(): Int = allData.size

    interface  Listener {
        fun onItemClearInSearchHistory(position : Int)
        fun onItemClicked(position: Int)
    }

    inner class SearchHistoryViewHolder(private val binding: ItemSearchHistoryBinding, private val listener : SearchHistoryAdapter.Listener) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(searchKey : String) {
            binding.apply {
                txvSearchKey.text = searchKey
                imvClear.setOnClickListener { onClick(it) }
                txvSearchKey.setOnClickListener { onClick(it) }
            }
        }

        override fun onClick(view: View?) {
            when(view) {
                binding.imvClear -> {
                    listener.onItemClearInSearchHistory(adapterPosition)
                }
                binding.txvSearchKey -> {
                    listener.onItemClicked(adapterPosition)
                }
            }
        }
    }
}