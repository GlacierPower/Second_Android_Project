package com.zhenya_flower.firstlesson_kotlin


import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.app.NotificationCompat.getColor
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegistrationFragment : Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var etConfirm: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSingUp: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var btnCheck: CheckBox
    private lateinit var layoutEmail: TextInputLayout
    private lateinit var layoutPass: TextInputLayout
    private lateinit var layoutConfirm: TextInputLayout


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
        layoutEmail = view.findViewById(R.id.layoutEmailRegister)
        layoutPass = view.findViewById(R.id.layoutPasswordRegister)
        layoutConfirm = view.findViewById(R.id.layoutConfirm)
        auth = Firebase.auth


        btnSingUp.setOnClickListener {
            singUpUser()
        }

    }

    private fun singUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfirm.text.toString()

        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            layoutEmail.helperText = "Enter email"
            layoutConfirm.helperText = "Confirm password"
            layoutPass.helperText = "Enter password"
            view?.showsnackBar("Email and Password can't be blank")
            return
        }
        if (pass != confirmPassword) {
            view?.showsnackBar("Password and Confirm Password do not match")
            return
        }
        auth.createUserWithEmailAndPassword(email!!, pass!!)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    view?.showsnackBar("User account registered")
                    parentFragmentManager
                        .beginTransaction()
                        .add(R.id.activityContainer, LoginFragment())
                        .commit()
//                        verifyEmail()

                } else {
                    view?.showsnackBar("Singed Up Failed!")
                }
            }

    }
//        private fun verifyEmail() {
//            val mUser = com.zhenya_flower.firstlesson_kotlin.auth.currentUser;
//            mUser!!.sendEmailVerification()
//                .addOnCompleteListener{ task ->
//                    if (task.isSuccessful) {
//                        view?.showsnackBar("Verification email sent to " + mUser.email)
//
//                    } else {
//                        Log.e(TAG, "sendEmailVerification", task.exception)
//                        view?.showsnackBar("Failed to send verification email.")
//                    }
//                }
//        }
}

fun View.showsnackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
}




