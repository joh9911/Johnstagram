package com.example.Johnstagram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.Johnstagram.databinding.SignupVerifyFragmentBinding

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