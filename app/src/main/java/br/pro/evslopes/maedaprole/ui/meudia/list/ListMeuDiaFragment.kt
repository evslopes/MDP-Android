package br.pro.evslopes.maedaprole.ui.meudia.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.pro.evslopes.maedaprole.R
import br.pro.evslopes.maedaprole.adapter.RecyclerListMeuDiaAdapter
import br.pro.evslopes.maedaprole.data.MeuDiaDaoFirestore
import br.pro.evslopes.maedaprole.data.ObjetoUtil
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.list_meudia_fragment.*

class ListMeuDiaFragment : Fragment() {
    private lateinit var viewModel: ListMeuDiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_meudia_fragment, container, false)
        val listMeuDiaViewModelFactory = ListMeuDiaViewModelFactory(MeuDiaDaoFirestore())

        viewModel = ViewModelProvider(this, listMeuDiaViewModelFactory).get(ListMeuDiaViewModel::class.java)
        viewModel.meuDia.observe(viewLifecycleOwner, Observer {
            recyclerlistMeuDia.adapter = RecyclerListMeuDiaAdapter(it) {
                ObjetoUtil.Selecionado = it
                findNavController().navigate(R.id.detailsMeuDiaFragment)
            }
            recyclerlistMeuDia.layoutManager = LinearLayoutManager(requireContext())
        })
        viewModel.attListMeudia()

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationApp)
        bottomNavigationView.visibility = View.VISIBLE

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabAddMeuDia.setOnClickListener {
            ObjetoUtil.Selecionado = null
            findNavController().navigate(R.id.formMeuDiaFragment)
        }
    }
}