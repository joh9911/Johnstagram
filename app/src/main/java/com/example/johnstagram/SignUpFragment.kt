package com.example.johnstagram

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.johnstagram.databinding.SignupFragmentBinding
import com.google.android.material.tabs.TabLayout


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
        initEvent()
        return view
    }
    fun initEvent(){
        binding.signUpEmailEdittext.visibility = View.GONE
        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.signUpPhoneNumberEdittext.requestFocus()
        if(binding.signUpPhoneNumberEdittext.isFocused){
            imm.showSoftInput(binding.signUpPhoneNumberEdittext, InputMethodManager.SHOW_FORCED)
        }
        binding.signUpPageTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    0 -> {
                        binding.signUpPhoneNumberEdittext.visibility = View.VISIBLE
                        binding.signUpPhoneNumberDetailTextview.visibility = View.VISIBLE
                        binding.signUpEmailEdittext.visibility = View.GONE
                        binding.signUpPhoneNumberEdittext.requestFocus()
                        binding.signUpPhoneNumberEdittext.text.clear()
                    }
                    1 -> {
                        binding.signUpPhoneNumberDetailTextview.visibility = View.GONE
                        binding.signUpPhoneNumberEdittext.visibility = View.GONE
                        binding.signUpEmailEdittext.visibility = View.VISIBLE
                        binding.signUpEmailEdittext.requestFocus()
                        binding.signUpEmailEdittext.text.clear()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
        returnToLoginFragmentEvent()
    }
    fun returnToLoginFragmentEvent(){
        binding.signUpPageBottomLinearLayout.setOnClickListener {
            val dataInterface = context as ReplaceFragmentToLoginFragment
            dataInterface.replaceFragmentToLoginFragment()
            binding.signUpPhoneNumberEdittext.text.clear()
            binding.signUpPhoneNumberEdittext.text.clear()
        }
    }
    fun phoneNumEditTextEraseButtonEvent(){

    }
}