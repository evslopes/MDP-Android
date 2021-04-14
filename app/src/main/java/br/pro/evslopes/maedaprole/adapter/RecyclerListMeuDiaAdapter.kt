package br.pro.evslopes.maedaprole.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.pro.evslopes.maedaprole.R
import br.pro.evslopes.maedaprole.enums.Tipos
import br.pro.evslopes.maedaprole.model.MeuDia
import br.pro.evslopes.maedaprole.model.MinhaInspiracao


class RecyclerListMeuDiaAdapter(
    private val meuDia: MutableList<MeuDia>,
    val actionClick: (MeuDia) -> Unit
) : RecyclerView.Adapter<RecyclerListMeuDiaAdapter.MeuDiaViewHolder>() {

    class MeuDiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNomeMeuDia: TextView = itemView.findViewById(R.id.txtNomeMeuDia)
        val imgCategoria: ImageView = itemView.findViewById(R.id.imgCategoriaMeuDia)
        val txtDataMeuDia: TextView = itemView.findViewById(R.id.txtDataMeuDia)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeuDiaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_meudia, parent, false)
        return MeuDiaViewHolder(view)
    }

    override fun getItemCount(): Int = meuDia.size

    override fun onBindViewHolder(holder: MeuDiaViewHolder, position: Int) {
        val meuDia = meuDia[position]
        holder.txtNomeMeuDia.text = meuDia.titulo
        holder.txtDataMeuDia.text = meuDia.data.toString()

        var mBitmapIds = arrayListOf<Int>()
        var uris = arrayListOf<Uri>()
        mBitmapIds.add(Tipos.ALERTA.caminho)
        mBitmapIds.add(Tipos.CURIOSIDADE.caminho)
        mBitmapIds.add(Tipos.GESTACAO.caminho)
        mBitmapIds.add(Tipos.MEUBEBE.caminho)
        mBitmapIds.add(Tipos.RECORDACAO.caminho)


        if(meuDia.tipo == "alerta") {
            uris.add(Uri.parse("android.resource://br.pro.evslopes.maedaprole/drawable/" + mBitmapIds[0]))
        }
        else if(meuDia.tipo == "curiosidade") {
            uris.add(Uri.parse("android.resource://br.pro.evslopes.maedaprole/drawable/" + mBitmapIds[1]))
        }
        else if(meuDia.tipo == "gestacao") {
            uris.add(Uri.parse("android.resource://br.pro.evslopes.maedaprole/drawable/" + mBitmapIds[2]))
        }
        else if(meuDia.tipo == "meubebe") {
            uris.add(Uri.parse("android.resource://br.pro.evslopes.maedaprole/drawable/" + mBitmapIds[3]))
        }
        else {
            uris.add(Uri.parse("android.resource://br.pro.evslopes.maedaprole/drawable/" + mBitmapIds[4]))
        }

        holder.imgCategoria.setImageURI(uris[0])

        holder.itemView.setOnClickListener {
            actionClick(meuDia)
        }
    }
}