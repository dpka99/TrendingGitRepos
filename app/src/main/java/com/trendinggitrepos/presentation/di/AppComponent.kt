package com.trendinggitrepos.presentation.di

import com.trendinggitrepos.presentation.App
import com.trendinggitrepos.presentation.MainActivity
import com.trendinggitrepos.presentation.repodetail.GithubRepoDetailFragment
import com.trendinggitrepos.presentation.repolist.GithubRepoListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        FactoryModule::class,
        NetModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
    ]
)
interface AppComponent {

    fun inject(application: App)
    fun inject(mainActivity: MainActivity)
    fun inject(githubRepoListFragment: GithubRepoListFragment)

}