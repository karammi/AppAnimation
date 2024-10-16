package com.asad.appanimation.core.data.dataSource

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CustomHttpInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(
                "JUST_A_SAMPLE_KEY_HEADER",
                "aJa!@6adsaR8#a@a!aMaD8aOaIa!aJ!aN@asa2RaNIa@!a12aj1a2k6klanada"
            ).build();
        return chain.proceed(request);
    }

}