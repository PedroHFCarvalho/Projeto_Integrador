package com.carvalho.pi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.carvalho.pi.databinding.FragmentCadastroBinding

class CadastroFragment : Fragment() {

    private lateinit var binding: FragmentCadastroBinding
    private var isValid: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
    binding = FragmentCadastroBinding.inflate(layoutInflater, container, false)

        binding.bttnCadastro.setOnClickListener {

            validadorCAD()

        }

        return binding.root

    }
    private fun validadorCAD(){

        val nome = binding.editTxtCDName.text.toString()
        val email = binding.editTxtCDEmail.text.toString()
        val senha = binding.editTxtCDPassword.text.toString()

        if (nome.isBlank() || nome.length < 3){
            Toast.makeText(context, "Nome Inválido", Toast.LENGTH_SHORT).show()

        }else if(email.isBlank()){
                Toast.makeText(context, "Email Inválido", Toast.LENGTH_SHORT).show()

            }else if (senha.isBlank() || senha.length < 8){
            Toast.makeText(context, "Senha Inválida", Toast.LENGTH_SHORT).show()

            }else {

            Toast.makeText(context, "Cadastro Realizado", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_cadastroFragment_to_loginFragment)

        }
    }

}