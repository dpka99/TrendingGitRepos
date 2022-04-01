package com.trendinggitrepos.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.trendinggitrepos.R
import com.trendinggitrepos.databinding.ActivityMainBinding
import com.trendinggitrepos.presentation.repolist.GithubRepoViewModel
import com.trendinggitrepos.presentation.repolist.GithubRepoViewModelFactory
import com.trendinggitrepos.workmanager.SyncDataWorker
import javax.inject.Inject
import androidx.navigation.NavController

import androidx.navigation.fragment.NavHostFragment


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: GithubRepoViewModelFactory
    private lateinit var viewModel: GithubRepoViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var  appBarConfiguration : AppBarConfiguration
    private lateinit var  navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as App).appComponent.inject(this)

        viewModel = ViewModelProvider(this, factory).get(GithubRepoViewModel::class.java)

        setupToolbar()
    }

    private fun setupToolbar(){

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment?
        navController = navHostFragment!!.navController

        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp();
        return super.onSupportNavigateUp();
    }
}