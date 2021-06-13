package com.example.recyclerviewdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.databinding.ItemRowBinding

// Adapter using viewbinding

class ItemsAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemRowBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
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
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = items.get(position)
        holder.binding.itemText.text = item

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.binding.apply {
                llItemRow.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_200))
                itemImage.setImageResource(R.drawable.ic_baseline_android_24)
            }
        }
        else {
            holder.binding.llItemRow.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200))
            holder.binding.itemImage.setImageResource(R.drawable.ic_baseline_accessibility_new_24)
        }

        //Setting onclick feature for the list items:
        holder.binding.llItemRow.setOnClickListener {
            Toast.makeText(context, "Clicked on ${holder.binding.itemText.text}", Toast.LENGTH_SHORT).show()
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
    inner class MyViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        // Holds the TextView and ImageView that will add each item to
    }
}