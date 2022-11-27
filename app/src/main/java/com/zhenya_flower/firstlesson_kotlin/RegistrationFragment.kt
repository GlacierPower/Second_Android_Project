package com.zhenya_flower.firstlesson_kotlin

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationFragment : Fragment() {

    lateinit var etEmail: EditText
    lateinit var etConfirm: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSingUp: Button
    private lateinit var auth: FirebaseAuth
    lateinit var  btnCheck: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etEmail = view.findViewById(R.id.emailRegister)
        etPass = view.findViewById(R.id.passwordRegister)
        etConfirm = view.findViewById(R.id.confirmPass)
        btnSingUp = view.findViewById(R.id.btn_registration)
        btnCheck = view.findViewById(R.id.checkBox)
        auth = Firebase.auth


        btnSingUp.setOnClickListener {
            singUpUser()

        }

        if (btnCheck.isActivated) {
            Toast.makeText(view.context, "Agree with terms", Toast.LENGTH_SHORT).show()
        } else {
            btnSingUp.setOnClickListener {

            }
        }
    }

    private fun singUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfirm.text.toString()

        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(
                activity, "Email and Password can't be blank",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (pass != confirmPassword) {
            Toast.makeText(
                activity, "Password and Confirm Password do not match",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(activity as Activity) {
            if (it.isSuccessful) {
                Toast.makeText(activity, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                parentFragmentManager
                    .beginTransaction()
                    .add(R.id.activityContainer, LoginFragment())
                    .commit()

            } else {
                Toast.makeText(activity, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}


