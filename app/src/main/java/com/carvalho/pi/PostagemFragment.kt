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
import com.carvalho.pi.databinding.FragmentPostagemBinding
import com.carvalho.pi.model.Categoria
import com.carvalho.pi.model.Produto
import java.lang.Exception

class PostagemFragment : Fragment() {

    private lateinit var binding: FragmentPostagemBinding
    private val viewModel: MainViewModel by activityViewModels()
    private var idCategSelect = 0L
    private var qtdSelect = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPostagemBinding.inflate(layoutInflater, container, false)

        setSpineerQuantidade()

        viewModel.responseCategoria.observe(viewLifecycleOwner) {
            Log.d("Req", it.body().toString())
            setSpineerCategoria(it.body())
        }

        binding.btnAdicionar.setOnClickListener {
            try {
                addProd()
                findNavController().navigate(R.id.action_postagemFragment_to_listagemFragment)
            } catch (e: Exception) {
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    fun setSpineerQuantidade(){

        var listaQuantidade = listOf (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)

        binding.spnrQtd.adapter = ArrayAdapter(
            requireContext(),
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            listaQuantidade)

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
                    idCategSelect = categSelect.id
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        }
    }


    fun addProd(){
        val nomeMarca = binding.textName.text.toString()
        val descricao = binding.eTextDescricao.text.toString()
        val imagem = binding.imgProd.drawable.toString()
        val quantidade = qtdSelect
        val valor = binding.eTextValor.text.toString().toDouble()
        val categoria = Categoria(idCategSelect, null)

        viewModel.adicionarProduto(Produto(0L,nomeMarca, descricao, imagem, quantidade, valor, categoria))

    }


}