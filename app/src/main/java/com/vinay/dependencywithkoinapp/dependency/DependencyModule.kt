package com.vinay.dependencywithkoinapp.dependency

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vinay.dependencywithkoinapp.model.GitHubApi
import com.vinay.dependencywithkoinapp.model.UserRepository
import com.vinay.dependencywithkoinapp.viewmodel.UserViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel {
        UserViewModel(get())
    }
}

val repositoryModule = module {
    single {
        UserRepository(get())
    }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

    single { provideUseApi(get()) }
}

val retrofitModule = module {

    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()

        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(factory))
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}