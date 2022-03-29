package com.carvalho.pi.model

data class Categoria(
    val id: Long,
    val descricao: String?
) {
    override fun toString(): String {
        return descricao!!
    }

}