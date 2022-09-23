package com.example.johnstagram

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.johnstagram.databinding.LoginFragmentBinding


class LoginFragment: Fragment() {
    private var myBinding: LoginFragmentBinding? = null
    private val binding get() = myBinding!!

    lateinit var replaceLoginFragmentToSignupFragment: ReplaceLoginFragmentToSignupFragment

    interface ReplaceLoginFragmentToSignupFragment{
        fun replaceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBinding = LoginFragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        Log.d("로그인 ","프레그먼트")
        signUpEvent()
        signUpWithEmailAndPhoneNumberBtnEvent()
        return view
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        replaceLoginFragmentToSignupFragment = activity as ReplaceLoginFragmentToSignupFragment

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

    fun signUpWithEmailAndPhoneNumberBtnEvent(){
        replaceLoginFragmentToSignupFragment.replaceFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        myBinding = null
    }
}