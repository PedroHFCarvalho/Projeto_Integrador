package com.carvalho.pi

import android.R
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import androidx.navigation.ui.setupWithNavController
import com.carvalho.pi.databinding.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        setSupportActionBar(binding.toolbar) // Adiciona a ToolBar no lugar da ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Adiciona a Arrow na ToolBar




        val navController =
            findNavController(com.carvalho.pi.R.id.fragmentContainerView) // Identifica o navController com base no fragmenteView
        binding.nvView.setupWithNavController(navController)// Set o NavController


        //Alterne a configuração para exibir o ícone de hambúrguer com uma boa animação
        val drawerToggle = setupDrawerToggle()
        drawerToggle?.isDrawerIndicatorEnabled = true
        drawerToggle?.syncState()
        if (drawerToggle != null) {
            // Vincular eventos DrawerLayout ao ActionBarToggle
            binding.drawerLayout.addDrawerListener(drawerToggle)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //infla o menu da toolbar
        menuInflater.inflate(com.carvalho.pi.R.menu.toolbar_nav, menu)
        return super.onCreateOptionsMenu(menu)
    }


    private fun setupDrawerToggle(): ActionBarDrawerToggle? {
        // Renderiza mudanças da animaçao e do menu Hamburger
        return ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            com.carvalho.pi.R.string.drawer_open,
            com.carvalho.pi.R.string.drawer_close
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.home -> {
                // Abre o menu com uma animação
                binding.drawerLayout.openDrawer(GravityCompat.START)
                return true
            }

            com.carvalho.pi.R.id.listagemFragment -> {
                // Navegaçao do botao Busca
                findNavController(com.carvalho.pi.R.id.fragmentContainerView).navigate(com.carvalho.pi.R.id.listagemFragment)

                
                return true
            }
            com.carvalho.pi.R.id.postagemFragment -> {
                // Navegaçao do botao Adicionar
                //viewModel.produtoSelecionado = null
                findNavController(com.carvalho.pi.R.id.fragmentContainerView).navigate(com.carvalho.pi.R.id.postagemFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}