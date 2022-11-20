package com.zhenya_flower.firstlesson_kotlin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputEmail = findViewById<TextInputLayout>(R.id.inputEmail)
        val inputPass = findViewById<TextInputLayout>(R.id.inputPass)
        val email = findViewById<EditText>(R.id.email)
        val pass = findViewById<EditText>(R.id.password)
        pass.setSelection(0)
        val btn = findViewById<Button>(R.id.materialButton)


        val dialog = AlertDialog.Builder(this)
            .setTitle("Information")
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->

            }

        btn.setOnClickListener {
            if (email.text.toString().isEmailValid() && isValidPassword(pass.text.toString())) {
                dialog.setMessage("Email: ${email.text} \n " + "Password: ${pass.text}").show()

            } else {
                if (email.text.isEmpty() || email.text.contains(" ")) {
                    inputEmail.helperText = "Email can't be empty or contain spaces"
                    inputEmail.setEndIconDrawable(R.drawable.error)
                    inputEmail.boxStrokeErrorColor = getColorStateList(R.color.error)
                }
                if (pass.text.isEmpty() || pass.text.contains(" ")) {
                    inputPass.helperText = "Password can't be empty or contain spaces"
                    inputPass.setEndIconDrawable(R.drawable.error)
                    inputPass.boxStrokeErrorColor = getColorStateList(R.color.error)
                }
            }
        }
    }

    private fun String.isEmailValid(): Boolean {
        return this.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.length < 8) return false
        if (password.firstOrNull { it.isDigit() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null) return false
        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null) return false
        if (password.firstOrNull { !it.isLetterOrDigit() } == null) return false

        return true
    }

}