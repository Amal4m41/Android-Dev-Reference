package com.example.recyclerviewdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))
    }

    /**
     * Binds each item(data) in the ArrayList to a view
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = items.get(position)
        holder.itemText.text = item

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.itemText.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_200))
            holder.itemImage.setImageResource(R.drawable.ic_baseline_android_24)
        }
        else {
            holder.itemText.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200))
            holder.itemImage.setImageResource(R.drawable.ic_baseline_accessibility_new_24)
        }
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // Holds the TextView and ImageView that will add each item to
        val itemImage:ImageView = view.findViewById<ImageView>(R.id.itemImage)
        val itemText:TextView = view.findViewById<TextView>(R.id.itemText)
    }
}