package com.systemtechnology.clientregister

import com.systemtechnology.clientregister.model.VivaCepApiRetrofit
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ConsistencyAddressTest {

    @Test
    fun testConsistencyJson() {

        val cep = "07183070"
        val path = "https://viacep.com.br/ws/$cep/"

        val observable = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(path)
            .build()
            .create( VivaCepApiRetrofit::class.java )
            .getAddressByVivaCepApi()

        observable.subscribe {

            Assert.assertFalse( it.erro )

            Assert.assertNotNull( it.bairro )
            Assert.assertNotNull( it.cep )
            Assert.assertNotNull( it.localidade )
            Assert.assertNotNull( it.logradouro )
            Assert.assertNotNull( it.uf )
        }


    }
}
