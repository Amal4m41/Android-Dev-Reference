package com.example.retrofit_mvvm_coroutine_demo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.retrofit_mvvm_coroutine_demo.databinding.CharacterRvItemBinding
import com.example.retrofit_mvvm_coroutine_demo.network.Character


class CharacterItemsAdapter(val context: Context, val items: List<Character>) :
        RecyclerView.Adapter<CharacterItemsAdapter.MyViewHolder>(){

//    private var onClickListenerRecyclerListItem:OnClickListenerRecyclerListItem?=null
//


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = CharacterRvItemBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.positionValue=position  //while binding the data to this view, the position value is updated.


        holder.binding.tvNameCharacter.text = item.name
        holder.binding.imageViewCharacter.load(item.image){
            transformations(CircleCropTransformation())
        }

    }

    override fun getItemCount(): Int = items.size

//
//    fun attachOnClickListenerInterfaceInstance(interfaceInstance:OnClickListenerRecyclerListItem){
//        this.onClickListenerRecyclerListItem=interfaceInstance
//    }
//
//    interface OnClickListenerRecyclerListItem{
//        fun onClickCustom(position:Int, userObj:User, action:String)
//    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    inner class MyViewHolder(val binding: CharacterRvItemBinding)
        : RecyclerView.ViewHolder(binding.root) {

        var positionValue:Int = -1  //to get the clicked item index from the recycler view

//        init {
//            binding.root.setOnClickListener {
//                if(positionValue!=-1 && onClickListenerRecyclerListItem!=null){
//                    val item = items[positionValue]
//                    if(item.selected){
//                        onClickListenerRecyclerListItem?.onClickCustom(positionValue,item, Constants.UNSELECTED)
//                    }
//                    else{
//                        onClickListenerRecyclerListItem?.onClickCustom(positionValue,item, Constants.SELECTED)
//                    }
//
//                }
//            }
//        }

    }

}