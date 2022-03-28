package com.carvalho.pi

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carvalho.pi.adapter.AdapterProduto
import com.carvalho.pi.databinding.ActivityMainBinding.inflate
import com.carvalho.pi.databinding.FragmentListagemBinding
import com.carvalho.pi.model.Produto
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListagemFragment : Fragment() {

    private lateinit var binding: FragmentListagemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListagemBinding.inflate(layoutInflater,container,false)

        val adapteProd = AdapterProduto()

        var listProduto =
            mutableListOf(
                Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                ),
                Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                ),
                Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                ),
                Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                ), Produto(
                    titulo = "Titulo",
                    descricao = "Descricao",
                    valor = 5.00,
                )

            )


        binding.recyclerListProd.layoutManager = LinearLayoutManager(context)
        binding.recyclerListProd.adapter = adapteProd
        binding.recyclerListProd.setHasFixedSize(true)
        adapteProd.setLista(listProduto)

        binding.floatingAddProd.setOnClickListener {
            findNavController().navigate(R.id.action_listagemFragment_to_postagemFragment)
        }


        return binding.root

    }

}