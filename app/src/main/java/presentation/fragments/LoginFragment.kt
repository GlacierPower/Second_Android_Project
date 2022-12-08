package presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.zhenya_flower.firstlesson_kotlin.R
import com.zhenya_flower.firstlesson_kotlin.databinding.FragmentLoginFragmentBinding



class LoginFragment : Fragment() {

    private var _viewBinding: FragmentLoginFragmentBinding? = null
    private val viewBinding get() = _viewBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentLoginFragmentBinding.inflate(inflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewBinding.signUp.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer,SingUpFragment())
                .addToBackStack(null)
                .commit()
        }
        viewBinding.forgotPass.setOnClickListener {
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.activityContainer,ForgotPasswordFragment())
                .addToBackStack(null)
                .commit()
        }

    }

}