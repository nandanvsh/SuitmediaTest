package com.example.suitmediatest.data.adapter

import android.animation.ArgbEvaluator
import android.animation.TimeAnimator
import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmediatest.R
import com.example.suitmediatest.data.response.DataItem
import com.example.suitmediatest.databinding.ItemCardBinding
import com.squareup.picasso.Picasso

class AccountAdapter : PagingDataAdapter<DataItem, AccountAdapter.MyViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private lateinit var binding: ItemCardBinding


    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem)
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class MyViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(list: DataItem){
            binding.tvItemUsername.text = "${list.firstName} ${list.lastName}"
            binding.email.text = "${list.email}"

            Glide.with(binding.root)
                .load(list.avatar)
                .into(binding.imgItemPhoto)


            binding.root.setOnClickListener{
                onItemClickCallback.onItemClicked(list)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>(){
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}