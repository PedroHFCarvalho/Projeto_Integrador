package com.carvalho.pi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carvalho.pi.adapter.AdapterProduto
import com.carvalho.pi.model.Produto
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListagemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_listagem, container, false)

        val recyclerProd = view.findViewById<RecyclerView>(R.id.recyclerListProd)
        val btnAdd = view.findViewById<FloatingActionButton>(R.id.floatingAddProd)
        val adapteProd = AdapterProduto()


        var listProduto =
            mutableListOf(
                Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                    img = "url"
                ),
                Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                    img = "url"
                ),
                Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                    img = "url"
                ),
                Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                    img = "url"
                ), Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                    img = "url"
                )

            )


        recyclerProd.layoutManager = LinearLayoutManager(context)
        recyclerProd.adapter = adapteProd
        recyclerProd.setHasFixedSize(true)
        adapteProd.setLista(listProduto)

        btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listagemFragment_to_postagemFragment)
        }


        return view

    }

}