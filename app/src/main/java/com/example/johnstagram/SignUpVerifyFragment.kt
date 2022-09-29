package com.example.johnstagram

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.johnstagram.databinding.SignupVerifyFragmentBinding

class SignUpVerifyFragment: Fragment() {
    var myBinding: SignupVerifyFragmentBinding? = null
    val binding get() = myBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBinding = SignupVerifyFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

}