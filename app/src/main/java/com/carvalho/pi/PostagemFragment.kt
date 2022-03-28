package com.carvalho.pi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.carvalho.pi.databinding.FragmentPostagemBinding
import com.carvalho.pi.model.Produto
import java.lang.Exception

class PostagemFragment : Fragment() {

    private lateinit var binding: FragmentPostagemBinding
    private lateinit var viewModel: PostagemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPostagemBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this).get(PostagemViewModel::class.java)

        binding.btnAdicionar.setOnClickListener {

            try {
                viewModel.validadorPost(addProd())
                findNavController().navigate(R.id.action_postagemFragment_to_listagemFragment)
            } catch (e: Exception) {
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }

    fun addProd(): Produto {
        val nome = binding.textName.text.toString()
        val descricao = binding.eTextDescricao.text.toString()
        val valor = binding.eTextValor.text.toString().toDoubleOrNull()

        return Produto(nome, descricao, valor)

    }

}