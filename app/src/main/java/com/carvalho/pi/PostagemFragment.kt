package com.carvalho.pi

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.carvalho.pi.databinding.FragmentPostagemBinding
import com.carvalho.pi.helpers.Validator
import com.carvalho.pi.model.Categoria
import com.carvalho.pi.model.Produto


class PostagemFragment : Fragment() {

    private lateinit var binding: FragmentPostagemBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var idCategSelect = 0L
    private var qtdSelect = 0
    private var url = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPostagemBinding.inflate(layoutInflater, container, false)

        viewModel.listarCategoria()
        validadeForm()

        viewModel.responseCategoria.observe(viewLifecycleOwner) {
            Log.d("Req", it.body().toString())
            setSpineerCategoria(it.body())
            setSpineerQuantidade()
        }

        binding.btnAdicionar.setOnClickListener {

            try {
                addProd()
                findNavController().navigate(R.id.action_postagemFragment_to_listagemFragment)
            } catch (e: Exception) {
                Toast.makeText(context, "Preencha todos os campos corretamente", Toast.LENGTH_SHORT).show()
            }

        }

        binding.imgProd.setOnClickListener {

            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle("URL de imagem")

            val input = EditText(context)
            input.hint = "Coloque a Url da imagem do produto"
            input.inputType = InputType.TYPE_CLASS_TEXT
            builder.setView(input)

            builder.setPositiveButton("OK") { _, _ ->
                url = input.text.toString()
                Glide.with(this)
                    .load(url)
                    .placeholder(R.drawable.placeholder)
                    .into(binding.imgProd)

            }
            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.cancel()
            }

            builder.show()
        }


        return binding.root
    }


    private fun validadeForm() {

        binding.textName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.textNameLayout.helperText = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //ignore
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.textNameLayout.helperText = binding.textName.text?.let {
                    Validator().validationName(binding.textName.text.toString(), it)
                }.toString()
            }

        })

        binding.eTextDescricao.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.textDescLayout.helperText = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //ignore
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.textDescLayout.helperText = binding.eTextDescricao.text?.let {
                    Validator().validationDescription(binding.eTextDescricao.text.toString(), it)
                }.toString()
            }

        })

        binding.eTextValor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.textValorLayout.helperText = ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //ignore
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.textValorLayout.helperText = binding.eTextValor.text?.let {
                    Validator().validationValue(binding.eTextValor.text.toString(), it)
                }.toString()
            }
        })

        binding.spnrQtd.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.textQtdLayout.helperText = binding.spnrQtd.text?.let {
                    Validator().validationQuantity(binding.spnrQtd.text.toString(), it)
                }.toString()
            }
        }

        binding.spnrCateg.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                binding.textValorLayout.helperText = binding.spnrQtd.text?.let {
                    Validator().validationQuantity(binding.spnrQtd.text.toString(), it)
                }.toString()

            }
        }
    }

        fun setSpineerQuantidade() {

            var listaQuantidade = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)

            binding.spnrQtd.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                    listaQuantidade
                )
            )

            binding.spnrQtd.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, p2, _ ->
                    qtdSelect = binding.spnrQtd.adapter.getItem(p2).toString().toInt()
                }

        }

        fun setSpineerCategoria(listaCategoria: List<Categoria>?) {
            if (listaCategoria != null) {
                binding.spnrCateg.setAdapter(
                    ArrayAdapter(
                        requireContext(),
                        androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        listaCategoria
                    )
                )

                binding.spnrCateg.onItemClickListener =
                    AdapterView.OnItemClickListener { _, _, p2, _ ->
                        val categSelect = binding.spnrCateg.adapter.getItem(p2) as Categoria
                        idCategSelect = categSelect.id
                    }
            }
        }


        fun addProd() {
            val nomeMarca = binding.textName.text.toString()
            val descricao = binding.eTextDescricao.text.toString()
            val imagem = url
            val quantidade = qtdSelect
            val valor = binding.eTextValor.text.toString().toDouble()
            val categoria = Categoria(idCategSelect, null)

            viewModel.adicionarProduto(
                Produto(
                    0L,
                    nomeMarca,
                    descricao,
                    imagem,
                    quantidade,
                    valor,
                    categoria
                )
            )

        }


    }