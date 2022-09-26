package com.example.johnstagram

import android.Manifest
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context.TELEPHONY_SERVICE
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat
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
        binding.signUpPhoneNumberEdittext.setText(getPhoneNum(requireContext()))
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

    fun getPhoneNum(context: Context): String? {
        var phoneNum = ""
        val telManager = context.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.READ_SMS
//            ) != PackageManager.PERMISSION_GRANTED )
//            return "0"
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED )
            return "123"
        else if(ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED)
            return "01"
        else if(
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "010"
        }
        phoneNum = telManager.line1Number.toString()
        if (phoneNum.startsWith("+82")) {
            phoneNum = phoneNum.replace("+82", "0")
        }
        return phoneNum
    }

    fun phoneNumEditTextEraseButtonEvent(){
        binding.signUpPageClearButton.setOnClickListener {
            binding.signUpPhoneNumberEdittext.text.clear()
        }
    }
}