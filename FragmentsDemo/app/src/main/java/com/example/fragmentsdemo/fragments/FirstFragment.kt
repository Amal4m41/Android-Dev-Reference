package com.example.fragmentsdemo.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentsdemo.R

class FirstFragment : Fragment(R.layout.fragment_first) {

//    //androidx.fragment.app.Fragment Called to do initial creation of a fragment. This is called
//    //after onAttach(Activity) and before onCreateView(LayoutInflater, ViewGroup, Bundle).
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    // This will be called between onCreate(Bundle) and onViewCreated(View, Bundle).
//    //The views are created in onCreateView(), i.e. we cannot access view elements inside of onCreate()
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_first, container, false)
//    } OR just pass the layout in the Fragment constructor parameter.
//
//    //To access the fragments views, we do that in onViewCreated(), this function is called after all the views are created.
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }


}