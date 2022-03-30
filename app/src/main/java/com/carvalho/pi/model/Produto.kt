package com.carvalho.pi.model

import android.content.res.Resources
import android.graphics.drawable.Drawable

data class Produto(
    val nomeMarca: String?,
    var descricao: String,
    var imagem: String,
    var quantidade: Int,
    var valor: Double,
    var categoria: Categoria
    )


