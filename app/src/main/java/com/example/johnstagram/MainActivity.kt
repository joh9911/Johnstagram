package com.example.johnstagram

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.johnstagram.databinding.ActivityMainBinding
import kotlin.reflect.typeOf

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        signUpEvent()
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
                binding.loginPageSignupInquireText.text = "이미 계정이 있으신가요?"
                binding.loginPageSignupText.text = "로그인하기."
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
                binding.loginPageSignupInquireText.text = "계정이 없으신가요?"
                binding.loginPageSignupText.text = "가입하기."
                binding.loginPageSignupText.setTypeface(null, Typeface.BOLD)

                tag -= 1
            }

        }
    }



}