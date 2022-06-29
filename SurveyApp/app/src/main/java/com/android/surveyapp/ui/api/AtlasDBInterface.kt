package com.android.surveyapp.ui.api

import android.content.Context
import android.util.Log
import com.android.surveyapp.ui.datamodel.SaveFormDataAll
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.io.IOException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


//@Singleton
interface AtlasDBInterface {

    companion object {
        var BASE_URL = "http://www.taggeo.co.in/"
//https://surveyappapi.000webhostapp.com/surveyapi.php
        fun create(): AtlasDBInterface {

            val okHttpClient7: OkHttpClient =
                OkHttpClient().newBuilder().addInterceptor { chain ->
                    val request = chain.request()
                    Log.v(
                        "Request",
                        "Request-->${request.url()}, params-->${bodyToString(request)},hreder-->${request.headers()}}"
                    )
                    val response = chain.proceed(request)
                    val body = response.body()
                    val bodyString = body?.string()
                    val contentType = body?.contentType()
                    Log.v("Response Body in Client -->" + response.code(), bodyString!!)
                    response.newBuilder().body(ResponseBody.create(contentType, bodyString!!))
                        .build()
                }.connectTimeout(100, TimeUnit.SECONDS).retryOnConnectionFailure(true)
                    .readTimeout(100, TimeUnit.SECONDS).writeTimeout(100, TimeUnit.SECONDS).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
//                .client(getUnsafeOkHttpClient())
                .client(okHttpClient7)
                .build()
            return retrofit.create(AtlasDBInterface::class.java)
        }


        private fun getUnsafeOkHttpClient(): OkHttpClient? {
            return try {
                // Create a trust manager that does not validate certificate chains
                val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                            chain: Array<X509Certificate?>?,
                            authType: String?
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate?>? {
                            return null
                        }
                    }
                )
                val sslContext = SSLContext.getInstance("SSL")
                sslContext.init(null, trustAllCerts, SecureRandom())
                // Create an ssl socket factory with our all-trusting manager
                val sslSocketFactory = sslContext.socketFactory
                val okHttpClient = OkHttpClient()
                okHttpClient.sslSocketFactory()
                okHttpClient.hostnameVerifier()
                okHttpClient
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }

        var gson = GsonBuilder()
            .setLenient()
            .create()

        private fun bodyToString(request: Request): String {
            return try {
                val copy = request.newBuilder().build()
                val buffer = Buffer()
                copy.body()?.writeTo(buffer)
                buffer.readUtf8()
            } catch (e: IOException) {
                "did not work"
            }
        }

    }
    @Headers("content_type:application/json ")

    @POST(value = "surveyapi.php")  //       http://www.taggeo.co.in/surveyapi.php
    fun saveForm(
//    @Header(value = "roleID") roleID: Int?,
        @Body body: SaveFormDataAll
    )
    : Call<ResponseBody>


}