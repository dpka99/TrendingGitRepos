package com.trendinggitrepos.presentation.di

import android.content.Context
import com.trendinggitrepos.common.Constants
import com.trendinggitrepos.common.NetworkValidator
import com.trendinggitrepos.data.api.GithubApiEndPoint
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesGitHubApiEndpoint(retrofit: Retrofit): GithubApiEndPoint {
        return retrofit.create(GithubApiEndPoint::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkValidator(context: Context): NetworkValidator {
        return NetworkValidator(context)
    }


}