package com.carvalho.pi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.carvalho.pi.databinding.FragmentPosCompraBinding
import com.carvalho.pi.model.Produto

class PosCompraFragment : Fragment() {

    private lateinit var binding: FragmentPosCompraBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var produtoSelecionado: Produto? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPosCompraBinding.inflate(layoutInflater, container, false)

        recuperarDados()

        binding.btnFinalizar.setOnClickListener {

            if (validaCampos(binding.eTxtEnderFinal.text.toString())) {
                Toast.makeText(context, "A Compra foi finalizada", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    R.id.action_posCompraFragment_to_listagemFragment, null,
                    NavOptions.Builder().setPopUpTo(R.id.posCompraFragment, true)
                        .build()
                )
            } else {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    fun validaCampos(endereco: String): Boolean {
        return !(endereco.isBlank() || endereco.length < 5 || endereco.length > 50)
    }


    private fun recuperarDados() {
        produtoSelecionado = viewModel.produtoSelecionado
        if (produtoSelecionado != null) {
            binding.txtTituloProdFinal.text = produtoSelecionado?.nomeMarca.toString()
            binding.txtValorFinal.text = produtoSelecionado?.valor.toString()
        } else {
            binding.txtTituloProdFinal.text = null
            binding.txtValorFinal.text = null
        }
    }
}