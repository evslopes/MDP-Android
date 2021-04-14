package br.pro.evslopes.maedaprole.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.pro.evslopes.maedaprole.R
import br.pro.evslopes.maedaprole.model.MinhaInspiracao

class RecyclerListMinhaInspiracaoAdapter (
    private val minhaInspiracao: List<MinhaInspiracao>
) : RecyclerView.Adapter<RecyclerListMinhaInspiracaoAdapter.MinhaInspiracaoViewHolder>(){

    class MinhaInspiracaoViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    val textViewListMinhaInspiracaoAssunto: TextView = itemView.findViewById(R.id.textViewListMinhaInspiracaoAssunto)
    val textViewListMinhaInspiracaoConteudo: TextView = itemView.findViewById(R.id.textViewListMinhaInspiracaoConteudo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinhaInspiracaoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_minhainspiracao, parent, false)
        return MinhaInspiracaoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MinhaInspiracaoViewHolder, position: Int) {
        val news = minhaInspiracao[position]
        holder.textViewListMinhaInspiracaoAssunto.text = news.source
        holder.textViewListMinhaInspiracaoConteudo.text = news.headline?.main
    }

    override fun getItemCount(): Int = minhaInspiracao.size
}