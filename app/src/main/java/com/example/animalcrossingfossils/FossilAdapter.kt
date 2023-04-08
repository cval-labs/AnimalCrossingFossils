package com.example.animalcrossingfossils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FossilAdapter(private val fossilList: MutableList<Triple <String, String, String>>) :  RecyclerView.Adapter<FossilAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fossilImage: ImageView
        val fossilName: TextView
        val fossilPrice: TextView

        init {
            // Find our RecyclerView item's ImageView for future use
            fossilImage = view.findViewById(R.id.fossil_image)
            fossilName = view.findViewById(R.id.fossil_name)
            fossilPrice = view.findViewById(R.id.fossil_price)
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fossil_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fossilList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val priceText = "Price: "
        val currency = " Bells"

        Glide.with(holder.itemView)
            .load(fossilList[position].first)
            .centerCrop()
            .into(holder.fossilImage)

        holder.fossilName.text = fossilList[position].second
        holder.fossilPrice.text = String.format("%s%s%s", priceText,fossilList[position].third, currency )

        holder.fossilImage.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Fossil at position $position clicked", Toast.LENGTH_SHORT).show()
        }


    }



}