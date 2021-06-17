package com.example.sqlitedemo.Adapters

import android.content.Context
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlitedemo.MainActivity
import com.example.sqlitedemo.Models.EmpModelClass
import com.example.sqlitedemo.R
import com.example.sqlitedemo.databinding.RecordItemRowBinding

class ItemAdapter(val context: Context, val items: ArrayList<EmpModelClass>) :
        RecyclerView.Adapter<ItemAdapter.MyViewHolder>() {

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

        val layoutInflater = LayoutInflater.from(context)
        val binding = RecordItemRowBinding.inflate(layoutInflater, parent, false)
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

        val item = items[position]
        holder.binding.apply {
            empNameTextView.text=item.name
            empEmailTextView.text=item.email
        }

        // Updating the background color according to the odd/even positions in list.
        if (position % 2 == 0) {
            holder.binding.llRecordItemRow.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_200))
        } else {
            holder.binding.llRecordItemRow.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200))
        }

        //Setting onclick feature for the list items:
        holder.binding.empEditImageView.setOnClickListener {
            //Toast.makeText(context, "${item.id}, ${item.name}", Toast.LENGTH_SHORT).show()
            if(context is MainActivity){
                //context is casted to type of MainActivity
                context.updateRecordDialog(item)
            }
        }

        holder.binding.empDeleteImageView.setOnClickListener {
            if(context is MainActivity){
                context.deleteRecordAlertDialog(item)
            }
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
    inner class MyViewHolder(val binding: RecordItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        // Through binding object we can access the TextView and ImageView (for each row)
    }
}