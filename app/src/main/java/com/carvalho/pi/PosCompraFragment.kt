package com.carvalho.pi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.carvalho.pi.databinding.FragmentPosCompraBinding

class PosCompraFragment : Fragment() {

    private lateinit var binding: FragmentPosCompraBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentPosCompraBinding.inflate(layoutInflater, container, false)




        return binding.root
    }

}