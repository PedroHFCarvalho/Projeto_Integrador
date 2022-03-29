package com.carvalho.pi

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carvalho.pi.model.Categoria
import com.carvalho.pi.model.Produto
import com.carvalho.pi.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import kotlin.math.log

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _responseProduto = MutableLiveData<Response<List<Produto>>>()
    private val _responseCategoria = MutableLiveData<Response<List<Categoria>>>()

    val responseProduto: LiveData<Response<List<Produto>>> = _responseProduto
    val responseCategoria: LiveData<Response<List<Categoria>>> = _responseCategoria

    init {
        listarProduto()
    }

    fun adicionarProduto(produto: Produto) {
        viewModelScope.launch {
            try {
                repository.adicionarProduto(produto)
                listarProduto()
            } catch (e: Exception) {
                Log.d("Err", e.message.toString())
            }

        }
    }


    fun listarProduto() {
        viewModelScope.launch {
            try {
                val response = repository.listaProduto()
                _responseProduto.value = response

            } catch (e: Exception) {
                Log.d("Err", e.message.toString())
            }

        }

    }

    fun listarCategoria() {
        viewModelScope.launch {
            try {
                val response = repository.listaCategoria()
                _responseCategoria.value = response

            } catch (e: Exception) {
                Log.d("Err", e.message.toString())
            }

        }

    }


    fun validadorPost(produto: Produto) {

        if (produto.titulo.isNullOrBlank() || produto.titulo.length <= 5 || produto.titulo.length >= 61) {
            throw Exception("Titulo Incorreto")
        }
        if (produto.descricao.isNullOrBlank() || produto.descricao.length <= 5 || produto.descricao.length >= 500) {
            throw Exception("Descrição Incorreto")
        }
        if (produto.valor == null) {
            throw Exception("Valor Incorreto")
        }

    }


}