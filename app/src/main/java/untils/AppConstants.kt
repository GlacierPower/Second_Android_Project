package untils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object AppConstants {
    fun View.showsnackBar(message: String) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
    }
}