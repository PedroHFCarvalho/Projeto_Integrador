package com.carvalho.pi.api

import com.carvalho.pi.model.Categoria
import com.carvalho.pi.model.Produto
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("produtos")
    suspend fun listProduto(): Response<List<Produto>>

    @POST("produtos")
    suspend fun postProduto(@Body produto: Produto) : Response<Produto>

    @GET("categoria")
    suspend fun listCategorias(): Response<List<Categoria>>
}