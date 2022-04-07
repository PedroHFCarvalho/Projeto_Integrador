package com.carvalho.pi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.carvalho.pi.MainViewModel
import com.carvalho.pi.R
import com.carvalho.pi.model.Produto

class AdapterHomepage(private val prodItemClickListener : ProdItemClickListener, private val viewModel : MainViewModel, var context: Context) : RecyclerView.Adapter<AdapterHomepage.HomeViewHolder>() {

    var listProduto = emptyList<Produto>()

    class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titulo = view.findViewById<TextView>(R.id.txtNomeCardHome)
        val valor = view.findViewById<TextView>(R.id.txtValorCardHome)
        val img = view.findViewById<ImageView>(R.id.imgProdCardHome)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val cardView =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_homepage, parent, false)
        return HomeViewHolder(cardView)
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        holder.titulo.text = listProduto[position].nomeMarca
        holder.valor.text = "R$ ${"%.2f".format(listProduto[position].valor)}"
        // holder.img.setImageResource(listProduto[position].img)

        Glide.with(context)
            .load(listProduto[position].imagem)
            .placeholder(R.drawable.placeholder)
            .into(holder.img)

        holder.itemView.setOnClickListener{
            prodItemClickListener.onProdClicked(listProduto[position])
        }


        holder.itemView.setOnClickListener{
            prodItemClickListener.onProdClicked(listProduto[position])
        }

    }

    override fun getItemCount(): Int {
        return listProduto.size

    }

    fun setLista(lista: List<Produto>) {
        listProduto = lista
        notifyDataSetChanged()
    }




}