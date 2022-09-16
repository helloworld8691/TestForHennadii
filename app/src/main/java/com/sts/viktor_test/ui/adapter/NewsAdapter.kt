package com.sts.viktor_test.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sts.viktor_test.R
import com.sts.viktor_test.databinding.ItemNewsBinding
import com.sts.viktor_test.models.NewsModel

class NewsAdapter(private val listener: Listener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val allData = mutableListOf<NewsModel>()

    fun update(list : List<NewsModel>) {
        allData.clear()
        allData.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(allData[position])
    }

    override fun getItemCount(): Int = allData.size

    interface  Listener {
        fun newsItemClicked(position : Int)
    }

    inner class NewsViewHolder(private val binding : ItemNewsBinding, private val listener : NewsAdapter.Listener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        fun bind(newsModel: NewsModel) {
            binding.apply {
                txvTitle.text = newsModel.title
                txvDescription.text = newsModel.description
                Glide.with(binding.root.context).load(newsModel.image).placeholder(R.drawable.default_img).into(imvPic)

                cdvContainer.setOnClickListener { onClick(it) }
            }
        }
        override fun onClick(p: View?) {
            listener.newsItemClicked(adapterPosition)
        }
    }
}
