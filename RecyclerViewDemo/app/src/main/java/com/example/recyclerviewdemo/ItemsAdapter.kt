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


/*
-Adapter will actually populate the data into the RecyclerView.

-The adapter's role is to convert an object at a position into a list row item to be inserted.

-RecyclerView the adapter requires the existence of a "ViewHolder" object which describes
and provides access to all the views within each item row.

-Every adapter has three primary methods:
-> onCreateViewHolder to inflate the item layout and create the holder,
-> onBindViewHolder to set the view attributes based on the data(position value is the
index of an item from the list/data we pass, holder is the ViewHolder to which that item(given the position) should
be binded to....RecyclerView is just a viewgroup(collection of views) of viewholders) and
-> getItemCount to determine the number of items
 */
class ItemsAdapter(val context: Context, val items: ArrayList<String>) :
    RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    /**
     * Inflates the item views which is designed in xml layout file
     * This method is called right when the adapter is created and is used to initialize each ViewHolder so that
     * our onBindViewHolder method can bind the data to the ViewHolder
     *
     * create a new
     * {@link ViewHolder} and initializes some private fields to be used by RecyclerView.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row, parent, false))

        Toast.makeText(context, "onCreateViewHolder", Toast.LENGTH_SHORT).show()
        val layoutInflater = LayoutInflater.from(context)
        val binding = ItemRowBinding.inflate(layoutInflater, parent, false)
        return MyViewHolder(binding)
    }

    /**
     * Binds each item(data) in the ArrayList to a view
     *
     * This method is called for each ViewHolder to bind it to the adapter.
     * This is where we will pass our data to our ViewHolder.
     *
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent
     * an item.
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     */
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        Toast.makeText(context, "OnBindViewHolder + $position", Toast.LENGTH_SHORT).show()
        val item = items[position]
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
//        holder.binding.llItemRow.setOnClickListener {
//            Toast.makeText(context, "Clicked on ${holder.binding.itemText.text}", Toast.LENGTH_SHORT).show()
//        }
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
        // Through binding object we can access the TextView and ImageView (for each row)
    }
}