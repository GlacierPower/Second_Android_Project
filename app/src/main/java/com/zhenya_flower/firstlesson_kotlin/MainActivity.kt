package com.zhenya_flower.firstlesson_kotlin

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layoutEmail = findViewById<TextInputLayout>(R.id.inputEmail)
        val layoutPass = findViewById<TextInputLayout>(R.id.inputPass)
        val email = findViewById<EditText>(R.id.email)
        layoutEmail.setStartIconDrawable(R.drawable.person)
        layoutPass.setStartIconDrawable(R.drawable.lock)
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
                    layoutEmail.helperText = "Email can't be empty or contain spaces"
                    layoutEmail.setHelperTextColor(getColorStateList(R.color.error))
                    layoutEmail.endIconDrawable = AppCompatResources
                        .getDrawable(this, R.color.error)
                    layoutEmail.boxStrokeErrorColor = getColorStateList(R.color.error)
                }
                if (pass.text.isEmpty() || pass.text.contains(" ")) {
                    layoutPass.helperText = "Password can't be empty or contain spaces"
                    layoutPass.setHelperTextColor(getColorStateList(R.color.error))
                    layoutPass.setEndIconDrawable(R.drawable.error)
                    layoutPass.boxStrokeErrorColor = getColorStateList(R.color.error)
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