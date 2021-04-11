package br.pro.evslopes.maedaprole.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.pro.evslopes.maedaprole.R
import br.pro.evslopes.maedaprole.enums.CategoriaEnum
import br.pro.evslopes.maedaprole.model.MeuDia


class RecyclerListMeuDiaAdapter(
    private val meuDia: List<MeuDia>,
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
        holder.txtNomeMeuDia.text = meuDia.nome
        holder.txtDataMeuDia.text = meuDia.data.toString()

        var mBitmapIds = arrayListOf<Int>()
        var uris = arrayListOf<Uri>()
        mBitmapIds.add(CategoriaEnum.CINEMA.caminho)
        mBitmapIds.add(CategoriaEnum.TEATRO.caminho)
        mBitmapIds.add(CategoriaEnum.FESTA.caminho)
        mBitmapIds.add(CategoriaEnum.SHOW.caminho)
        mBitmapIds.add(CategoriaEnum.EVENTOESPORTIVO.caminho)

        if(meuDia.categoria == "Cinema") {
            uris.add(Uri.parse("android.resource://br.pro.evslopes.maedaprole/drawable/" + mBitmapIds[0]))
        }
        else if(meuDia.categoria == "Teatro") {
            uris.add(Uri.parse("android.resource://br.pro.evslopes.maedaprole/drawable/" + mBitmapIds[1]))
        }
        else if(meuDia.categoria == "Festa") {
            uris.add(Uri.parse("android.resource://br.pro.evslopes.maedaprole/drawable/" + mBitmapIds[2]))
        }
        else if(meuDia.categoria == "Show") {
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