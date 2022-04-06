package com.carvalho.pi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carvalho.pi.adapter.AdapterHomepage
import com.carvalho.pi.adapter.AdapterProduto
import com.carvalho.pi.adapter.ProdItemClickListener
import com.carvalho.pi.databinding.ActivityMainBinding
import com.carvalho.pi.databinding.FragmentPaginaPrincipalBinding
import com.carvalho.pi.model.Produto

class PaginaPrincipal : Fragment(), ProdItemClickListener {

    private lateinit var binding: FragmentPaginaPrincipalBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaginaPrincipalBinding.inflate(layoutInflater, container, false)

        viewModel.listarProduto()
        //viewModel.listarCategoria()

        val adapteProd = AdapterHomepage(this, viewModel, requireContext())



        viewModel.responseProduto.observe(viewLifecycleOwner) {
            if (it != null) {

                adapteProd.setLista(it.body()!!)
            }
            Log.d("Req", it.body().toString())
        }

        binding.recyclerHomepage.layoutManager = LinearLayoutManager(context)
        binding.recyclerHomepage.adapter = adapteProd
        binding.recyclerHomepage.setHasFixedSize(true)

        return binding.root
    }

    override fun onProdClicked(produto: Produto) {
        viewModel.produtoSelecionado = produto
        findNavController().navigate(R.id.action_paginaPrincipal_to_produtoFragment)
    }

}