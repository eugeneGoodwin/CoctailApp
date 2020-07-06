package com.vortex.soft.coctailapp.di.module

import android.util.Log
import com.google.gson.GsonBuilder
import com.vortex.soft.coctailapp.data.repository.NetService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val SERVER_ENDPOINT = "https://xxx.xxx.xxx/"

val networkModule = module {
    factory { GsonBuilder().create() }

    factory { OkHttpClient.Builder()
        .addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()

                val t1 = System.nanoTime()
                Log.i("KotlinFlowTest!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",
                    java.lang.String.format(
                        "Sending request %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()
                    )
                )

                val response = chain.proceed(request)

                val t2 = System.nanoTime()
                Log.i("KotlinFlowTest!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!",
                    String.format(
                        "Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6, response.headers()
                    )
                )

                return response
            }
        })
        .build() }
    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(SERVER_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
    single { NetService(get()) }
}