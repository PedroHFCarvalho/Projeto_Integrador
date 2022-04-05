package com.carvalho.pi.repository

import com.carvalho.pi.api.RetrofitInstance
import com.carvalho.pi.model.Categoria
import com.carvalho.pi.model.Produto
import retrofit2.Response

class Repository {

    suspend fun listaProduto(): Response<List<Produto>> {
        return RetrofitInstance.api.listProduto()
    }

    suspend fun adicionarProduto(produto: Produto): Response<Produto> {
        return RetrofitInstance.api.postProduto(produto)
    }

    suspend fun updateProduto(produto : Produto) : Response<Produto>{
        return RetrofitInstance.api.updateProduto(produto)
    }

    suspend fun deleteProduto(id: Long): Response<Produto>{
        return RetrofitInstance.api.deleteProduto(id)
    }

    suspend fun listaCategoria(): Response<List<Categoria>> {
        return RetrofitInstance.api.listCategorias()

    }

}