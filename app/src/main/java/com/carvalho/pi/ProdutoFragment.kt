package com.carvalho.pi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.carvalho.pi.databinding.FragmentPostagemBinding
import com.carvalho.pi.databinding.FragmentProdutoBinding
import com.carvalho.pi.model.Produto

class ProdutoFragment : Fragment() {


    private lateinit var binding: FragmentProdutoBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var produtoSelecionado : Produto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProdutoBinding.inflate(layoutInflater, container, false)

        recuperarDados()

        binding.btnEditPreview.setOnClickListener {
            findNavController().navigate(R.id.action_produtoFragment_to_editProdutoFragment)
        }

        return binding.root
    }

    private fun recuperarDados(){
        produtoSelecionado = viewModel.produtoSelecionado
        if (produtoSelecionado != null){
            binding.textNamePreview.text = produtoSelecionado?.nomeMarca
            binding.eTextDescricaoPreview.text = produtoSelecionado?.descricao
            //binding.imgProd.setImageDrawable(produtoSelecionado?.imagem)
            binding.textCategoriaPreview.text = "Categoria: \n${produtoSelecionado?.categoria.toString()}"
            binding.textQtdPreview.text = "Quantidade: \n${produtoSelecionado?.quantidade.toString()}"
            binding.eTextValorPreview.text = "R$ ${produtoSelecionado?.valor.toString()}"
        }else{
            binding.textNamePreview.text = null
            binding.eTextDescricaoPreview.text = null
            //binding.imgProd.drawable = null
            binding.eTextValorPreview.text = null
        }
    }
}