package com.techtask.breakingbadcharacters.common

import androidx.fragment.app.Fragment
import com.techtask.breakingbadcharacters.common.BaseActivity

open class BaseFragment : Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).presentationComponent
    }

    protected val injector get() = presentationComponent
}
