package com.zhenya_flower.firstlesson_kotlin

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class ForgotPassFragment : Fragment() {
    private lateinit var etEmail: EditText
    private lateinit var btnForgot: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var layoutEmail: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_forgot_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        etEmail = view.findViewById(R.id.emailForfot)
        btnForgot = view.findViewById(R.id.btn_forgot)
        layoutEmail = view.findViewById(R.id.layoutEmailForgot)
        auth = FirebaseAuth.getInstance()

        btnForgot.setOnClickListener { resetPass() }
        super.onViewCreated(view, savedInstanceState)
    }

    fun resetPass() {
        val email = etEmail?.text.toString()
        if (!TextUtils.isEmpty(email)) {
            auth!!
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        view?.showsnackBar(getString(R.string.email_sent))
                        parentFragmentManager
                            .beginTransaction()
                            .replace(R.id.activityContainer, LoginFragment())
                            .commit()
                    } else {
                        layoutEmail.helperText = "Enter email"
                        view?.showsnackBar(
                            getString(R.string.user_not_found)
                        )
                    }
                }
        } else {
            view?.showsnackBar(getString(R.string.ent_email))
        }
    }
}