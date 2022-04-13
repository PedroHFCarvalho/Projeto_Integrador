package com.carvalho.pi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.carvalho.pi.databinding.FragmentPosCompraBinding
import com.carvalho.pi.helpers.Validator
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

        setSpineerPagamento()
        validaCampos()
        recuperarDados()

        binding.btnFinalizar.setOnClickListener {

            if (binding.eTxtEnderFinalLayout.helperText.isNullOrBlank() && binding.spinPagmantoFInalLayout.helperText.isNullOrBlank()) {
                Toast.makeText(context, "A Compra foi finalizada", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    R.id.action_posCompraFragment_to_paginaPrincipal, null,
                    NavOptions.Builder().setPopUpTo(R.id.posCompraFragment, true)
                        .build()
                )
            } else {
                Toast.makeText(context, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }


    fun validaCampos() {
        binding.eTxtEnderFinal.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.eTxtEnderFinalLayout.helperText = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //ignore
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.eTxtEnderFinalLayout.helperText = binding.eTxtEnderFinal.text?.let {
                    Validator().validationAddress(binding.eTxtEnderFinal.text.toString(), it)
                }.toString()
            }

        })

        binding.spinPagmantoFInal.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.spinPagmantoFInalLayout.helperText = binding.spinPagmantoFInal.text?.let {
                    Validator().validationPay(binding.spinPagmantoFInal.text.toString(), it)
                }.toString()
            }
        }

    }

    fun setSpineerPagamento() {

        var listaQuantidade = listOf<String>("Boleto", "PIX", "Cartão de Crédito")

        binding.spinPagmantoFInal.setAdapter(
            ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listaQuantidade
            )
        )

    }

    private fun recuperarDados() {
        produtoSelecionado = viewModel.produtoSelecionado
        if (produtoSelecionado != null) {
            binding.txtTituloProdFinal.text = produtoSelecionado?.nomeMarca.toString()
            Glide.with(this).load(produtoSelecionado?.imagem).placeholder(R.drawable.placeholder)
                .into(binding.imgProdutoFinal)
            binding.txtValorFinal.text = "R$ ${"%.2f".format(produtoSelecionado?.valor)}"
        } else {
            binding.txtTituloProdFinal.text = null
            binding.txtValorFinal.text = null
        }
    }
}