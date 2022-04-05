package com.carvalho.pi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.carvalho.pi.databinding.FragmentInicioBinding

class Inicio : Fragment() {

    private lateinit var  binding: FragmentInicioBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater:LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInicioBinding.inflate(layoutInflater, container, false)

        return binding.root

        binding.bttnFLogin.setOnClickListener {

            findNavController().navigate(R.id.)
        }
        binding.bttnFCadastro
        binding.bttnSkip
    }
}