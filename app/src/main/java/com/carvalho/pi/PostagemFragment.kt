package com.carvalho.pi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class PostagemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_postagem, container, false)
        val btnAdicionar = view.findViewById<Button>(R.id.btnAdicionar)

        btnAdicionar.setOnClickListener {
            findNavController().navigate(R.id.action_postagemFragment_to_listagemFragment)
            Toast.makeText(context,"Adicionado", Toast.LENGTH_LONG).show()
        }



        return view
    }


}