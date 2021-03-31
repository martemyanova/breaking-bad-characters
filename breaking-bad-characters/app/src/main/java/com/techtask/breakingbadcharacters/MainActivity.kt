package com.techtask.breakingbadcharacters

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.techtask.breakingbadcharacters.common.BaseActivity
import com.techtask.breakingbadcharacters.common.viewmodel.ViewModelFactory
import com.techtask.breakingbadcharacters.presentation.characterslist.viewmodel.CharacterListViewModel
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: CharacterListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CharacterListViewModel::class.java)

        //NavigationUI.setupActionBarWithNavController(this, navController)

        handleIntent(intent)
    }

    private val navController get() = findNavController(R.id.navHostFragment)

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        Log.d("BreakingBad", "handleIntent")
        if (Intent.ACTION_SEARCH == intent?.action) {
            Log.d("BreakingBad", "handleIntent, ACTION_SEARCH")
            val query = intent.getStringExtra(SearchManager.QUERY)
            query?.let {
                viewModel.onSearchRequest(it)
            }
        }
    }
}
