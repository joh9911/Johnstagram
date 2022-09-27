package com.example.johnstagram

import android.Manifest
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.signUpPhoneNumberEdittext.requestFocus()
        binding.signUpPhoneNumberEdittext.setText(getPhoneNumber())
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
        phoneNumEditTextEraseButtonEvent()
    }

    fun returnToLoginFragmentEvent(){
        binding.signUpPageBottomLinearLayout.setOnClickListener {
            val dataInterface = context as ReplaceFragmentToLoginFragment
            dataInterface.replaceFragmentToLoginFragment()
            binding.signUpPhoneNumberEdittext.text.clear()
            binding.signUpPhoneNumberEdittext.text.clear()
        }
    }

    @Throws(SecurityException::class)
    private fun getPhoneNumber(): String? {
        val telephonyManager = ContextCompat.getSystemService(
            requireContext(),
            TelephonyManager::class.java
        )
        return if (telephonyManager != null) {
            if (//권한이 없다면 SecurityException를 발생시킵니다
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_PHONE_NUMBERS
                ) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_PHONE_STATE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                throw SecurityException("Permission Denial : requires android.permission.READ_PHONE_NUMBERS, android.permission.READ_PHONE_STATE")
            } else { //권한이 있다면 getLine1Number()를 반환합니다 (null을 반환할 수 있습니다)
                telephonyManager.line1Number
            }
        } else null

        // SystemService 가 null이라면 null을 반환합니다
    }
    fun phoneNumEditTextEraseButtonEvent(){
        binding.signUpPageClearButton.setOnClickListener {
            binding.signUpPhoneNumberEdittext.text.clear()
        }
    }
}