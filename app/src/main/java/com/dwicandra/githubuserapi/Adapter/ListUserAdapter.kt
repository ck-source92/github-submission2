package com.dwicandra.githubuserapi.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dwicandra.githubuserapi.DetailActivity
import com.dwicandra.githubuserapi.Model.ItemsItem
import com.dwicandra.githubuserapi.R
import com.dwicandra.githubuserapi.databinding.ItemUserBinding

class ListUserAdapter(private val listUser: ArrayList<ItemsItem>) : RecyclerView.Adapter<ListUserAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView)
                .load(listUser[position].avatarUrl)
                .circleCrop()
                .error(R.mipmap.ic_launcher)
                .into(ivAvatar)
            tvName.text = listUser[position].login
        }
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_USERNAME, listUser[position].login)
            holder.itemView.context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return listUser.size
    }

}