package com.example.johnstagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.johnstagram.databinding.SignupFragmentBinding

class SignUpFragment: Fragment() {
    private var myBinding: SignupFragmentBinding? = null
    private val binding get() = myBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBinding = SignupFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
    }
}