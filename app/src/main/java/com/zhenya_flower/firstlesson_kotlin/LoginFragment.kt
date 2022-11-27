package com.zhenya_flower.firstlesson_kotlin

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var singUpText: TextView
    private lateinit var forgotPass: TextView
    private lateinit var layoutEmail: TextInputLayout
    private lateinit var layoutPass: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        singUpText = view.findViewById(R.id.sign_up)
        forgotPass = view.findViewById(R.id.forgot_pass)
        btnLogin = view.findViewById(R.id.btn_login)
        etEmail = view.findViewById(R.id.email)
        etPass = view.findViewById(R.id.password)
        layoutEmail = view.findViewById(R.id.layoutEmail)
        layoutPass = view.findViewById(R.id.layoutPassword)
        auth = FirebaseAuth.getInstance()



        btnLogin.setOnClickListener {
            login()

        }
        singUpText.setOnClickListener {
            singUp()
        }

        forgotPass.setOnClickListener {
            forgotPass()
        }

    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)) {
            auth!!.signInWithEmailAndPassword(email!!, pass!!)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        view?.showsnackBar("Successfully LoggedIn")
                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.activityContainer, MainFragment())
                            .commit()
                    } else {
                        layoutEmail.helperText = "Enter email"
                        layoutPass.helperText = "Enter password"
                        view?.showsnackBar("Authentication failed.")
                    }
                }
        }

    }

    private fun singUp() {
        parentFragmentManager
            .beginTransaction()
            .add(R.id.activityContainer, RegistrationFragment())
            .addToBackStack("add")
            .commit()
    }

    private fun forgotPass() {
        parentFragmentManager
            .beginTransaction()
            .add(R.id.activityContainer, ForgotPassFragment())
            .addToBackStack("add")
            .commit()
    }
}
