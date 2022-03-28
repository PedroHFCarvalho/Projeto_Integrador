package com.carvalho.pi

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.carvalho.pi.model.Produto
import java.lang.Exception
import kotlin.math.log

class PostagemViewModel : ViewModel() {

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