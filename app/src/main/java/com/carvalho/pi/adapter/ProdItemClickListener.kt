package com.carvalho.pi.adapter

import com.carvalho.pi.model.Produto

interface ProdItemClickListener {
    fun onProdClicked(produto : Produto)
}