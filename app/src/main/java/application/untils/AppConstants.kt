package application.untils

import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar

object AppConstants {
    fun View.showsnackBar(message: String) {
        Snackbar.make(rootView, message ?: "empty", Snackbar.LENGTH_SHORT).show()
    }

    fun Context.hideSoftInput(view: View) {
        val im = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(view.windowToken, InputMethodManager.RESULT_UNCHANGED_SHOWN)
    }

    fun String.isEmailValid() = this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    const val NAME = "name"
    const val DATE = "date"
    const val IMAGE_VIEW = "image_view"
    const val EXCEPTION = "Exception called"
}