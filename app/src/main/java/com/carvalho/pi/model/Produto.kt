package com.carvalho.pi.model

import android.content.res.Resources
import android.graphics.drawable.Drawable

data class Produto(
    val titulo: String?,
    var descricao: String,
    var valor: Double?,
    ) {

    var qtd: Int = 0
    var categoria: String = ""

    constructor(
        titulo: String,
        descricao: String,
        valor: Double,
        img: Drawable,
        qtd: Int,
        categoria: String
    ) : this(titulo, descricao, valor,) {
        this.qtd = qtd
        this.categoria = categoria

    }

}