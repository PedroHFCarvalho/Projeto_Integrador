package com.carvalho.pi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.carvalho.pi.databinding.ActivityMainBinding
import com.carvalho.pi.databinding.FragmentPaginaPrincipalBinding

class PaginaPrincipal : Fragment() {

    private lateinit var binding: FragmentPaginaPrincipalBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPaginaPrincipalBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

}