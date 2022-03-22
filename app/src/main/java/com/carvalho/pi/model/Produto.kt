package com.carvalho.pi.model

data class Produto(val titulo: String, var descricao: String, var valor: Double, var img: String) {
    var qtd: Int = 0
    var categoria: String = ""

    constructor(
        titulo: String,
        descricao: String,
        valor: Double,
        img: String,
        qtd: Int,
        categoria: String
    ) : this(titulo, descricao, valor, img) {
        this.qtd = qtd
        this.categoria = categoria

    }

}