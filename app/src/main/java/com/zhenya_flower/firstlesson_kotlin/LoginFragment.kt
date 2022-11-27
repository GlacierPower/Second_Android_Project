package com.zhenya_flower.firstlesson_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    lateinit var btnLogin: Button
    lateinit var auth: FirebaseAuth
    lateinit var singUpText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        singUpText = view.findViewById(R.id.sign_up)
        btnLogin = view.findViewById(R.id.btn_login)
        etEmail = view.findViewById(R.id.email)
        etPass= view.findViewById(R.id.password)
        auth = FirebaseAuth.getInstance()


        btnLogin.setOnClickListener {
            login()
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer, MainFragment())
                .commit()
        }
        singUpText.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .add(R.id.activityContainer, RegistrationFragment())
                .commit()
        }
        super.onViewCreated(view, savedInstanceState)
    }
    private fun login(){
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener() {
            if (it.isSuccessful) {
                Toast.makeText(view?.context, "Successfully LoggedIn",
                    Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(view?.context, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

}