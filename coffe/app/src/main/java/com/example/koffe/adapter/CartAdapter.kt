package com.example.koffe.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.koffe.databinding.ViewholderCartBinding
import com.example.koffe.helper.ChangeNumberItemsListener
import com.example.koffe.helper.ManagmentCart
import com.example.koffe.model.ItemsModel

class CartAdapter(val listItemSelected: ArrayList<ItemsModel>,
                  context: Context,
                  var changeNumberItemsListener: ChangeNumberItemsListener?=null
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val managementCart = ManagmentCart(context)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItem.text = "$" + item.price.toString()
        holder.binding.numberItemTxt.text = item.numberInCart.toString()
        holder.binding.totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"

        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply (RequestOptions().transform(CenterCrop()))
            .into(holder.binding.cartPicture)

        holder.binding.plusCartBtn.setOnClickListener {
            managementCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }

        holder.binding.minusCartBtn.setOnClickListener {
            managementCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }
            })
        }

    }

    override fun getItemCount(): Int = listItemSelected.size

    inner class ViewHolder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root)
}