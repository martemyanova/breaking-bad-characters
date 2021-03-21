package com.techtask.breakingbadcharacters

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.techtask.breakingbadcharacters.common.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private val navController get() = findNavController(R.id.navHostFragment)

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
