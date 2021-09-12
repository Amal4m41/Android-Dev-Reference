package com.example.fragmentsdemo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.fragmentsdemo.R
import com.example.fragmentsdemo.databinding.FragmentFirstBinding
import com.example.fragmentsdemo.databinding.FragmentSecondBinding

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding  get() = _binding!!

    // This will be called between onCreate(Bundle) and onViewCreated(View, Bundle).
    //The views are created in onCreateView(), i.e. we cannot access view elements inside of onCreate()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
///*
////        method-1
//        _binding = FragmentSecondBinding.inflate(inflater, container, false)
//        return _binding?.root
//*/
//    }
    //    OR just pass the layout in the Fragment constructor parameter.


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSecondBinding.bind(view)  //if the view is already inflated then we can just bind it to view binding.
        changeDummyText()
    }

    private fun changeDummyText(){
        binding.tvDummyText.text="Changed text"
//        view.findViewById<TextView>(R.id.tvDummyText).text="Changed text"

    }


    //Note: Fragments outlive their views. Make sure you clean up any references to the binding class
    // instance in the fragment's onDestroyView() method.
    override fun onDestroyView() {
        Toast.makeText(activity, "On destroy", Toast.LENGTH_SHORT).show()
        super.onDestroyView()
        _binding = null
    }

}