package com.example.Johnstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.Johnstagram.databinding.ActivityMainBinding

interface ReplaceFragmentToSignupFragment {
    fun replaceFragmentToSignupFragment()
}
interface ReplaceFragmentToLoginFragment {
    fun replaceFragmentToLoginFragment()
}
interface ReplaceFragmentToVerifyFragment{
    fun replaceFragmentToVerifyFragment(myPhoneNumber: String)
}

class MainActivity : AppCompatActivity(),ReplaceFragmentToSignupFragment, ReplaceFragmentToLoginFragment, ReplaceFragmentToVerifyFragment {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_page_frame_layout, fragment).commit()
    }


    override fun replaceFragmentToSignupFragment(){
        val fragment = SignUpFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_page_frame_layout, fragment).commit()
    }

    override fun replaceFragmentToLoginFragment() {
        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_page_frame_layout, fragment).commit()    }

    override fun onStop() {
        super.onStop()
//        Toast.makeText(this,"mainOnStop",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
//        Toast.makeText(this,"mainOnDestroy",Toast.LENGTH_SHORT).show()
    }

    override fun replaceFragmentToVerifyFragment(myPhoneNumber: String) {
        val myPhoneNumber = myPhoneNumber
        val bundle = Bundle()
        bundle.putString("myPhoneNumber",myPhoneNumber)
        val fragment = SignUpVerifyFragment()
        fragment.arguments = bundle  // 이 4줄
        supportFragmentManager.beginTransaction().replace(R.id.main_page_frame_layout, fragment).commit()
    }

}