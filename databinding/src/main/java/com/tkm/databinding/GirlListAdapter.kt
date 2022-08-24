package com.tkm.databinding

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tkm.databinding.databinding.LayoutGirlListItemBinding

class GirlListAdapter : RecyclerView.Adapter<GirlListAdapter.ViewHolder>() {

    private val mGirls = mutableListOf<Girl>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(girls: List<Girl>) {
        mGirls.clear()
        mGirls.addAll(girls)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: LayoutGirlListItemBinding = DataBindingUtil.inflate(
            inflater, R.layout.layout_girl_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val girl = mGirls[position]
        holder.bind(girl)
    }

    override fun getItemCount(): Int {
        return mGirls.size
    }

    class ViewHolder(private val binding: LayoutGirlListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(girl: Girl) {
            binding.girl = girl
        }
    }
}