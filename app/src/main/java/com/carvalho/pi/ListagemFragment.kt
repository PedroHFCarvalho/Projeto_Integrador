package com.carvalho.pi

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carvalho.pi.adapter.AdapterProduto
import com.carvalho.pi.adapter.ProdItemClickListener
import com.carvalho.pi.databinding.ActivityMainBinding.inflate
import com.carvalho.pi.databinding.FragmentListagemBinding
import com.carvalho.pi.model.Produto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class ListagemFragment : Fragment(), ProdItemClickListener {

    private lateinit var binding: FragmentListagemBinding
    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var newlist: List<Produto>
    private lateinit var templist: MutableList<Produto>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListagemBinding.inflate(layoutInflater, container, false)

        val adapteProd = AdapterProduto(this, viewModel)

        viewModel.listarProduto()
        viewModel.listarCategoria()

        templist = mutableListOf()
        newlist = mutableListOf()


        viewModel.responseProduto.observe(viewLifecycleOwner) {
            if (it != null) {

                newlist = it.body()!!
                adapteProd.setLista(newlist)

            }
            Log.d("Req", it.body().toString())
        }



        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                templist.clear()
                newlist.forEach {
                    if (it.nomeMarca!!.contains(query!!, true)) {
                        templist.add(it)
                    }
                }
                adapteProd.setLista(templist.asReversed())

                return false
            }

        })

        binding.recyclerListProd.layoutManager = LinearLayoutManager(context)
        binding.recyclerListProd.adapter = adapteProd
        binding.recyclerListProd.setHasFixedSize(true)
        //adapteProd.setLista(listProduto)

        /*binding.floatingAddProd.setOnClickListener {
            findNavController().navigate(R.id.action_listagemFragment_to_postagemFragment)
        }*/

        return binding.root

    }


    override fun onProdClicked(produto: Produto) {
        viewModel.produtoSelecionado = produto
        findNavController().navigate(R.id.action_listagemFragment_to_produtoFragment)
    }


}