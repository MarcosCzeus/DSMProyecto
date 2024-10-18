package com.example.koffe.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.koffe.databinding.ViewholderOfferBinding
import com.example.koffe.model.ItemsModel

class OffersAdapter(val items: MutableList<ItemsModel>) :
    RecyclerView.Adapter<OffersAdapter.ViewHolder>() {

    private var context: Context? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OffersAdapter.ViewHolder {
        context = parent.context
        val binding = ViewholderOfferBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OffersAdapter.ViewHolder, position: Int) {
        val item = items[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$"+ item.price.toString()

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .into(holder.binding.shapeableImageView)

        holder.itemView.setOnClickListener {

        }

    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(val binding: ViewholderOfferBinding) :
        RecyclerView.ViewHolder(binding.root)
}