package com.example.johnstagram

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.johnstagram.databinding.ActivityMainBinding
import kotlin.reflect.typeOf

interface DataFromFragment {
    fun replaceFragmentToSignupFragment()
}

class MainActivity : AppCompatActivity(),DataFromFragment {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_page_frame_layout, fragment).commit()
        Log.d("메인액","프레그먼트 교체")
    }


    override fun replaceFragmentToSignupFragment(){
        val fragment = SignUpFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_page_frame_layout, fragment).commit()
    }

}