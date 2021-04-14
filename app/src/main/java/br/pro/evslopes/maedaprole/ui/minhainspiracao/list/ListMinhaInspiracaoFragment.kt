package br.pro.evslopes.maedaprole.ui.minhainspiracao.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import br.pro.evslopes.maedaprole.R
import br.pro.evslopes.maedaprole.adapter.RecyclerListMinhaInspiracaoAdapter
import com.google.android.material.snackbar.Snackbar

class ListMinhaInspiracaoFragment : Fragment() {

    private lateinit var viewModelListNews: ListMinhaInspiracaoViewModel
    private lateinit var recycleViewListNews: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_minhainspiracao_fragment, container, false)
//        val listMinhaInspiracaoModelFactory = ListMinhaInspiracaoModelFactory()

        recycleViewListNews = view.findViewById(R.id.recyclerlistMinhaInspiracao)

        viewModelListNews = ViewModelProvider(this).get(ListMinhaInspiracaoViewModel::class.java)

        viewModelListNews.news.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty())
                recycleViewListNews.adapter = RecyclerListMinhaInspiracaoAdapter(it)
        })

        viewModelListNews.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
        })

        return view
    }
    }
