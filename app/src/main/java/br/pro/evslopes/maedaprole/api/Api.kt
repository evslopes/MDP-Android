package br.pro.evslopes.maedaprole.api

import br.pro.evslopes.maedaprole.api.service.MinhaInspiracaoService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    private var instace: Retrofit? = null
    private fun getRetrofit(): Retrofit{
        if (instace == null)
            instace = Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return instace as Retrofit
    }

    fun getNewsService(): MinhaInspiracaoService =
        getRetrofit().create(MinhaInspiracaoService::class.java)
}