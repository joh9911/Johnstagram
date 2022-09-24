package com.example.johnstagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.johnstagram.databinding.LoginFragmentBinding

class HAHA: Fragment() {
    private var binding:LoginFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LoginFragmentBinding.inflate(inflater,container,false)
        return view.root
    }
}