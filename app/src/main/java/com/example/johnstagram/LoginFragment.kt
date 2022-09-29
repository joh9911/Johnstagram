package com.example.johnstagram

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.example.johnstagram.databinding.LoginFragmentBinding


class LoginFragment: Fragment() {
    private var myBinding: LoginFragmentBinding? = null
    private val binding get() = myBinding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBinding = LoginFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        initEvent()
        return view
    }
    fun initEvent(){
        signUpEvent()
        signUpWithEmailAndPhoneNumberBtnEvent()
        passwordIconEvent()
        editTextEvent()
        loginButtonEvent()
    }


    fun signUpEvent(){
        var tag = 0
        binding.loginPageSignupText.setOnClickListener {
            if (tag == 0){
                binding.loginPageIdEdittext.visibility = View.GONE
                binding.loginPagePwEdittext.visibility = View.GONE
                binding.loginPageLoginButton.visibility = View.GONE
                binding.loginPageFindLoginInfoLinearLayout.visibility = View.GONE
                binding.loginPageFacebookLoginButtonWhenSignInMode.visibility = View.GONE
                binding.loginPageFacebookLoginButtonWhenSignupMode.visibility = View.VISIBLE
                binding.loginPageSignupWithEmailPhoneNum.visibility = View.VISIBLE
                binding.loginPagePasswordIcon.visibility = View.INVISIBLE
                binding.loginPageSignupInquireText.text = resources.getString(R.string.account_already_have)
                binding.loginPageSignupText.text = resources.getString(R.string.account_already_have_login)
                binding.loginPageSignupText.setTypeface(null, Typeface.BOLD)
                tag += 1
            }
            else{
                binding.loginPageIdEdittext.visibility = View.VISIBLE
                binding.loginPagePwEdittext.visibility = View.VISIBLE
                binding.loginPageLoginButton.visibility = View.VISIBLE
                binding.loginPageFindLoginInfoLinearLayout.visibility = View.VISIBLE
                binding.loginPagePasswordIcon.visibility = View.VISIBLE
                binding.loginPageFacebookLoginButtonWhenSignInMode.visibility = View.VISIBLE
                binding.loginPageFacebookLoginButtonWhenSignupMode.visibility = View.GONE
                binding.loginPageSignupWithEmailPhoneNum.visibility = View.GONE
                binding.loginPageSignupInquireText.text = resources.getString(R.string.no_account)
                binding.loginPageSignupText.text = resources.getString(R.string.signup)
                binding.loginPageSignupText.setTypeface(null, Typeface.BOLD)
                tag -= 1
            }
        }
    }

    fun passwordIconEvent(){
        var tag = 0
        binding.loginPagePasswordIcon.setOnClickListener {
            if (tag == 0){
                binding.loginPagePwEdittext.transformationMethod = HideReturnsTransformationMethod.getInstance()
                binding.loginPagePwEdittext.setSelection(binding.loginPagePwEdittext.length())
                binding.loginPagePasswordIcon.setBackgroundResource(R.mipmap.show_password)
                tag += 1
            }
            else{
                binding.loginPagePwEdittext.transformationMethod = PasswordTransformationMethod.getInstance()
                binding.loginPagePwEdittext.setSelection(binding.loginPagePwEdittext.length())
                binding.loginPagePasswordIcon.setBackgroundResource(R.mipmap.hide_password)
                tag -= 1
            }
        }
    }

    fun signUpWithEmailAndPhoneNumberBtnEvent(){
        binding.loginPageSignupWithEmailPhoneNum.setOnClickListener {
            val dataInterface = context as ReplaceFragmentToSignupFragment
            dataInterface.replaceFragmentToSignupFragment()
            binding.loginPageIdEdittext.text.clear()
            binding.loginPagePwEdittext.text.clear()
        }
    }
    fun loginButtonEvent(){
        binding.loginPageLoginButton.setOnClickListener {
            Log.d("로그인버튼","클릭함")
        }
    }

    fun editTextEvent(){
        binding.loginPageLoginButton.isEnabled = false
        var lengthOfIdText = 0
        var lengthOfPwText = 0
        binding.loginPageIdEdittext.addTextChangedListener(object: TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lengthOfIdText = binding.loginPageIdEdittext.text.toString().length
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(lengthOfIdText >= 1 && lengthOfPwText >= 1){
                    binding.loginPageLoginButton.isEnabled = true
                    binding.loginPageLoginButton.setBackgroundResource(R.drawable.button_box_border)
                    binding.loginPageLoginButton.setTextColor(resources.getColor(R.color.white))
                }
                else{
                    binding.loginPageLoginButton.isEnabled = false
                    binding.loginPageLoginButton.setBackgroundResource(R.drawable.disabled_button_box_border)
                    binding.loginPageLoginButton.setTextColor(resources.getColor(R.color.button_text_disabled_color))
                }
            }
        })
        binding.loginPagePwEdittext.addTextChangedListener(object: TextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lengthOfPwText = binding.loginPagePwEdittext.text.toString().length

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if(lengthOfIdText >= 1 && lengthOfPwText >= 1){
                    binding.loginPageLoginButton.isEnabled = true
                    binding.loginPageLoginButton.setTextColor(resources.getColor(R.color.white))
                    binding.loginPageLoginButton.setBackgroundResource(R.drawable.button_box_border)
                }
                else{
                    binding.loginPageLoginButton.isEnabled = false
                    binding.loginPageLoginButton.setBackgroundResource(R.drawable.disabled_button_box_border)
                    binding.loginPageLoginButton.setTextColor(resources.getColor(R.color.button_text_disabled_color))
                }
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        myBinding = null
    }
}