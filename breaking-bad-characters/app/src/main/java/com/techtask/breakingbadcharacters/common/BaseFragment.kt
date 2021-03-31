package com.techtask.breakingbadcharacters.common

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.techtask.breakingbadcharacters.R
import com.techtask.breakingbadcharacters.common.BaseActivity

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).presentationComponent
    }

    protected val injector get() = presentationComponent

    protected val navController get() = activity?.findNavController(R.id.navHostFragment)
}
