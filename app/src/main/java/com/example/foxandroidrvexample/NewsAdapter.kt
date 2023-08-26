package com.example.foxandroidrvexample

import android.R
import android.annotation.SuppressLint
import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foxandroidrvexample.DataPackage.News
import com.example.foxandroidrvexample.databinding.RvItemRowBinding


class NewsAdapter(private val newsList: ArrayList<News>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var mlistener: OnItemClickListener

    interface OnItemClickListener{

        fun onItemCLick(position: Int)

    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mlistener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<News>) {
        newsList.clear()
        newsList.addAll(newList)
        notifyDataSetChanged()
    }


    inner class NewsViewHolder(private val binding: RvItemRowBinding, listener: OnItemClickListener)
        : RecyclerView.ViewHolder(binding.root) {
        private val title = binding.titleText
        private val image = binding.titleImage

        init {
            binding.root.setOnClickListener {
                listener.onItemCLick(bindingAdapterPosition)
            }
        }

        fun bind(news: News) {
            //loading image to imageview
            val requestOptions = RequestOptions()
                .placeholder(R.mipmap.sym_def_app_icon)
                .error(R.mipmap.sym_def_app_icon)
            Glide.with(binding.root.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(news.imageURL)
                .into(image)

            //setting title text
            title.text = news.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(RvItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false), mlistener)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }
}