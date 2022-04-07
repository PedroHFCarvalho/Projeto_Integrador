package com.carvalho.pi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.carvalho.pi.databinding.FragmentCadastroBinding

class CadastroFragment : Fragment() {

    private lateinit var binding: FragmentCadastroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    binding = FragmentCadastroBinding.inflate(layoutInflater, container, false)

        binding.bttnCadastro.setOnClickListener {

            findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)

        }

        return binding.root

    }

}