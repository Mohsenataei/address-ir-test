package com.avalinejad.addressirtest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.avalinejad.addressirtest.R
import com.avalinejad.addressirtest.data.model.Detail
import com.avalinejad.addressirtest.data.model.Response
import kotlinx.android.synthetic.main.recycler_row_item.view.*

class DetailsRecyclerViewAdapter(
    val context: Context,
    items: MutableList<Detail> = mutableListOf()
) : RecyclerView.Adapter<DetailsRecyclerViewAdapter.DetailsViewHolder>() {

    var items: MutableList<Detail> = items
        set(value) {
        if (value == field)
            return
            field = value
            notifyDataSetChanged()
        }
    inner class DetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(detail: Detail) {
            itemView.textTitle.text = detail.title
        }
    }
    private fun ViewGroup.inflater(layoutId: Int): View {
        return LayoutInflater.from(context).inflate(
            layoutId,
            this,
            false
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        return DetailsViewHolder(parent.inflater(R.layout.recycler_row_item))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(items[position])
    }


}