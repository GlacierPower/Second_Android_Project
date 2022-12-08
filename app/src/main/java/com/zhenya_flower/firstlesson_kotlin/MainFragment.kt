package com.zhenya_flower.firstlesson_kotlin

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth


private lateinit var auth: FirebaseAuth


private lateinit var tvEmail: TextView
private lateinit var tvEmailVerifiied: TextView

class MainFragment : Fragment() {
    override fun onStart() {
        super.onStart()
        val mUser = auth.currentUser
        tvEmail.text = mUser?.email
        tvEmailVerifiied.text = mUser?.isEmailVerified.toString()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_fragment_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.log_out -> logOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logOut() {
        auth.signOut()
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.activityContainer, LoginFragment())
            .commit()
    }

}