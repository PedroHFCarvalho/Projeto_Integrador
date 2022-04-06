package com.carvalho.pi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.carvalho.pi.databinding.FragmentPostagemBinding
import com.carvalho.pi.databinding.FragmentProdutoBinding
import com.carvalho.pi.model.Produto

class ProdutoFragment : Fragment() {


    private lateinit var binding: FragmentProdutoBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var produtoSelecionado: Produto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProdutoBinding.inflate(layoutInflater, container, false)

        recuperarDados()
        viewModel.listarCategoria()

        binding.btnEditPreview.setOnClickListener {
            findNavController().navigate(
                R.id.action_produtoFragment_to_editProdutoFragment, null,
                NavOptions.Builder().setPopUpTo(R.id.editProdutoFragment, true)
                    .build()
            )

        }

        binding.btnComprarPreview.setOnClickListener {
            findNavController().navigate(
                R.id.action_produtoFragment_to_posCompraFragment, null,
                NavOptions.Builder().setPopUpTo(R.id.posCompraFragment, true)
                    .build()
            )
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun recuperarDados() {
        produtoSelecionado = viewModel.produtoSelecionado
        if (produtoSelecionado != null) {
            binding.textNamePreview.text = produtoSelecionado?.nomeMarca
            binding.eTextDescricaoPreview.text = produtoSelecionado?.descricao
            //binding.imgProd.setImageDrawable(produtoSelecionado?.imagem)
            binding.textCategoriaPreview.text =
                "Categoria ${produtoSelecionado?.categoria?.descricao.toString()}"
            binding.textQtdPreview.text = "Quantidade ${produtoSelecionado?.quantidade.toString()}"
            binding.eTextValorPreview.text = produtoSelecionado?.valor.toString()
        } else {
            binding.textNamePreview.text = null
            binding.eTextDescricaoPreview.text = null
            //binding.imgProd.drawable = null
            binding.eTextValorPreview.text = null
        }
    }
}