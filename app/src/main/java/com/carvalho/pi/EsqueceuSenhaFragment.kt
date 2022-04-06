package com.carvalho.pi

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.carvalho.pi.databinding.FragmentEsqueceuSenhaBinding


class EsqueceuSenhaFragment: Fragment() {

    private lateinit var binding: FragmentEsqueceuSenhaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEsqueceuSenhaBinding.inflate(layoutInflater, container, false)


        binding.bttnEnviar.setOnClickListener {

            emailValidator(binding.editTxtEmail)

        }

        return binding.root
    }

    private fun emailValidator(etMail: EditText) {

        val emailToText = etMail.text.toString()

        if (emailToText.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            findNavController().navigate(R.id.action_esqueceuSenhaFragment_to_loginFragment)
            Toast.makeText(context, "Email Enviado", Toast.LENGTH_LONG).show()

        } else {
            Toast.makeText(context, "Email Inválido", Toast.LENGTH_SHORT).show()
        }
    }

}