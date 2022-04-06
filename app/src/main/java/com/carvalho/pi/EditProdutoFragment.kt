package com.carvalho.pi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.carvalho.pi.databinding.FragmentEditProdutoBinding
import com.carvalho.pi.model.Categoria
import com.carvalho.pi.model.Produto


class EditProdutoFragment : Fragment() {

    private lateinit var binding: FragmentEditProdutoBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var produtoSelecionado: Produto? = null
    private lateinit var categoriaSelect: Categoria
    private var qtdSelect = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProdutoBinding.inflate(layoutInflater, container, false)

        setSpineerQuantidade()

        recuperarDados()

        viewModel.responseCategoria.observe(viewLifecycleOwner) {
            Log.d("Req", it.body().toString())
            setSpineerCategoria(it.body())
        }

        binding.btnDeletarEdit.setOnClickListener {
            viewModel.deleteProduto(viewModel.produtoSelecionado!!.id)
            findNavController().navigate(R.id.action_editProdutoFragment_to_listagemFragment)
            Toast.makeText(context, "Produto Foi Deletado com Sucesso", Toast.LENGTH_SHORT).show()
        }

        binding.btnEditar.setOnClickListener {

            viewModel.produtoSelecionado = editProd()
            findNavController().navigate(R.id.action_editProdutoFragment_to_produtoFragment)
            Toast.makeText(context, "Produto Foi Editado com Sucesso", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun recuperarDados() {
        produtoSelecionado = viewModel.produtoSelecionado
        if (produtoSelecionado != null) {
            binding.textName.setText(produtoSelecionado?.nomeMarca)
            binding.eTextDescricao.setText(produtoSelecionado?.descricao)
            //binding.imgProd.setImageDrawable(produtoSelecionado?.imagem)
            binding.eTextValor.setText(produtoSelecionado?.valor.toString())
        } else {
            binding.textName.text = null
            binding.eTextDescricao.text = null
            //binding.imgProd.drawable = null
            binding.eTextValor.text = null
        }
    }

    fun setSpineerQuantidade() {

        var listaQuantidade = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)

        binding.spnrQtd.adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            listaQuantidade
        )

        binding.spnrQtd.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                qtdSelect = binding.spnrQtd.selectedItem.toString().toInt()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    }

    fun setSpineerCategoria(listaCategoria: List<Categoria>?) {
        if (listaCategoria != null) {
            binding.spnrCateg.adapter = ArrayAdapter(
                requireContext(),
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                listaCategoria
            )

            binding.spnrCateg.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val categSelect = binding.spnrCateg.selectedItem as Categoria
                    categoriaSelect = categSelect

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }


    fun editProd(): Produto {
        val nomeMarca = binding.textName.text.toString()
        val descricao = binding.eTextDescricao.text.toString()
        val imagem = binding.imgProd.drawable.toString()
        val quantidade = qtdSelect.toString().toInt()
        val valor = binding.eTextValor.text.toString().toDouble()
        val categoria = categoriaSelect

        val produto = Produto(
            produtoSelecionado?.id!!,
            nomeMarca,
            descricao,
            imagem,
            quantidade,
            valor,
            categoria
        )

        viewModel.updateProduto(produto)
        return produto

    }

}