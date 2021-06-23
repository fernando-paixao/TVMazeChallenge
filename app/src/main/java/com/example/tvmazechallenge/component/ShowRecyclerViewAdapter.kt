package com.example.tvmazechallenge.component

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tvmazechallenge.databinding.FragmentShowsBinding
import com.example.tvmazechallenge.model.Show
import com.squareup.picasso.Picasso


/**
 * [RecyclerView.Adapter] that can display a [Show].
 */
class ShowRecyclerViewAdapter(
    private val values: List<Show>
) : RecyclerView.Adapter<ShowRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentShowsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.contentView.text = item.name

        try{
            Picasso.get().load(item.image["original"]).into(holder.contentImage)
        } catch (ex: Exception) {
            print(ex.message)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentShowsBinding) : RecyclerView.ViewHolder(binding.root) {
        val contentView: TextView = binding.textView
        val contentImage: ImageView = binding.imageView

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}