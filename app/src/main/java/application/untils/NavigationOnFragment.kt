package application.untils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.zhenya_flower.firstlesson_kotlin.R

object NavigationOnFragment {
    fun replaceFragment(parentFragmentManager: FragmentManager,
                        fragment: Fragment,
    addToBackStack :Boolean
    ){
      if(addToBackStack){
          parentFragmentManager
              .beginTransaction()
              .replace(R.id.activityContainer,fragment)
              .addToBackStack(null)
              .commit()
      }else{
          parentFragmentManager
              .beginTransaction()
              .replace(R.id.activityContainer,fragment)
              .commit()
      }
    }
}