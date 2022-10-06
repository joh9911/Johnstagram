package com.example.Johnstagram

import android.Manifest.permission.*
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Context.TELEPHONY_SERVICE
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.Johnstagram.databinding.SignupFragmentBinding
import com.google.android.material.tabs.TabLayout
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit


class SignUpFragment: Fragment() {
    private var myBinding: SignupFragmentBinding? = null
    private val binding get() = myBinding!!
    val auth = Firebase.auth
    var verificationId = ""

    companion object {
        private val PERMISSIONS = arrayOf(
            READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE, SEND_SMS
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myBinding = SignupFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        initEvent()
        binding.signUpEmailEdittext.visibility = View.GONE

        return view
    }

    fun initEvent() {
        requestPermissions(getPermissionsRequest(), PERMISSIONS)

        setFocusOnEditText()
        tabLayoutEvent()
        returnToLoginFragmentEvent()
        nextButtonActivateEvent()
        registerWithPhoneNumberButtonEvent()
    }

    fun setFocusOnEditText() {
        val imm = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.signUpPhoneNumberEdittext.requestFocus()
        if (binding.signUpPhoneNumberEdittext.isFocused) {
            imm.showSoftInput(binding.signUpPhoneNumberEdittext, InputMethodManager.SHOW_FORCED)
        }
    }

    fun tabLayoutEvent() {
        binding.signUpPageTabLayout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab!!.position) {
                    0 -> {
                        binding.phoneNumberRelativeLayout.visibility = View.VISIBLE
                        binding.signUpPhoneNumberDetailTextview.visibility = View.VISIBLE
                        binding.signUpEmailEdittext.visibility = View.GONE
                        binding.signUpPhoneNumberEdittext.requestFocus()
                    }
                    1 -> {
                        binding.signUpPhoneNumberDetailTextview.visibility = View.GONE
                        binding.phoneNumberRelativeLayout.visibility = View.GONE
                        binding.signUpEmailEdittext.visibility = View.VISIBLE
                        binding.signUpEmailEdittext.requestFocus()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })
    }

    fun requestPermissions(
        request: ActivityResultLauncher<Array<String>>,
        permissions: Array<String>
    ) = request.launch(permissions)

    fun isAllPermissionsGranted(permissions: Array<String>) = permissions.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    fun getPermissionsRequest() =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (isAllPermissionsGranted(PERMISSIONS)) {             //extension function
                binding.signUpPhoneNumberEdittext.setText(getPhoneNumber())
            } else {
            }

        }

    fun getPhoneNumber(): String {
        var tm = requireContext().getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        val myPhoneNumber = tm.line1Number
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                READ_SMS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                READ_PHONE_NUMBERS
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return ""
        }
        return myPhoneNumber
    }


    fun returnToLoginFragmentEvent() {
        binding.signUpPageBottomLinearLayout.setOnClickListener {
            val dataInterface = context as ReplaceFragmentToLoginFragment
            dataInterface.replaceFragmentToLoginFragment()
            binding.signUpPhoneNumberEdittext.text.clear()
            binding.signUpPhoneNumberEdittext.text.clear()
        }
    }

    fun moveToVerifyFragment() {
        val dataInterface = context as ReplaceFragmentToVerifyFragment
        dataInterface.replaceFragmentToVerifyFragment()
    }

    fun phoneNumEditTextEraseButtonEvent() {
        binding.signUpPageClearButton.setOnClickListener {
            binding.signUpPhoneNumberEdittext.text.clear()
        }
    }

    fun nextButtonActivateEvent() {
        binding.signPageNextButton.isEnabled = false
        var lengthOfPhoneNumText = 0
        phoneNumEditTextEraseButtonEvent()

        binding.signUpPhoneNumberEdittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (lengthOfPhoneNumText >= 1) {
                    binding.signUpPageClearButton.visibility = View.VISIBLE
                    binding.signPageNextButton.isEnabled = true
                    binding.signPageNextButton.setBackgroundResource(R.drawable.button_box_border)
                    binding.signPageNextButton.setTextColor(resources.getColor(R.color.white))
                } else {
                    binding.signUpPageClearButton.visibility = View.GONE
                    binding.signPageNextButton.isEnabled = false
                    binding.signPageNextButton.setBackgroundResource(R.drawable.disabled_button_box_border)
                    binding.signPageNextButton.setTextColor(resources.getColor(R.color.button_text_disabled_color))
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                lengthOfPhoneNumText = binding.signUpPhoneNumberEdittext.text.toString().length
            }
        })
        binding.signUpPhoneNumberEdittext.addTextChangedListener(PhoneNumberFormattingTextWatcher())
    }

    fun registerWithPhoneNumberButtonEvent() {
        val range = (1000..9999)
        val verificationNumber = range.random().toString()
        val myPhoneNumber = "1"

        binding.signPageNextButton.setOnClickListener {
            moveToVerifyFragment()
            try {
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage(myPhoneNumber, null, verificationNumber, null, null)
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Please enter all the data.." + e.message.toString(),
                    Toast.LENGTH_LONG
                )
                    .show()
            }
        }
    }

    fun getAuthorized(){
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) { }
            override fun onVerificationFailed(e: FirebaseException) {
            }
            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                this@SignUpFragment.verificationId = verificationId
            }
        }
        val optionsCompat =  PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber("+821012345678")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(optionsCompat)
        auth.setLanguageCode("kr")
    }

}
