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

class AdapterProduto (private val prodItemClickListener : ProdItemClickListener, private val viewModel : MainViewModel, val context: Context) : RecyclerView.Adapter<AdapterProduto.ProdutoViewHolder>() {

    var listProduto = emptyList<Produto>()

    class ProdutoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val titulo = view.findViewById<TextView>(R.id.textTituloCard)
        val descricao = view.findViewById<TextView>(R.id.textDescricaoCard)
        val valor = view.findViewById<TextView>(R.id.textValorCard)
        val img = view.findViewById<ImageView>(R.id.imgProdCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val cardView =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_produto, parent, false)
        return ProdutoViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {

        holder.titulo.text = listProduto[position].nomeMarca
        holder.descricao.text = listProduto[position].descricao
        holder.valor.text =    "R$ ${"%.2f".format(listProduto[position].valor)}"
       // holder.img.setImageResource(listProduto[position].img.toInt())

        Glide.with(context)
            .load(listProduto[position].imagem)
            .placeholder(R.drawable.placeholder)
            .into(holder.img)

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