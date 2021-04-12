package br.pro.evslopes.maedaprole.ui.meudia.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.evslopes.maedaprole.R
import br.pro.evslopes.maedaprole.data.MeuDiaDaoFirestore
import br.pro.evslopes.maedaprole.data.ObjetoUtil
import br.pro.evslopes.maedaprole.model.MeuDia
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.details_meudia_fragment.*

class DetailsMeuDiaFragment : Fragment() {
    private lateinit var viewModel: DetailsMeuDiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_meudia_fragment, container, false)

        val detailsTicketViewModelFactory = DetailsMeuDiaViewModelFactory(requireActivity().application, MeuDiaDaoFirestore())

        viewModel = ViewModelProvider(this, detailsTicketViewModelFactory).get(DetailsMeuDiaViewModel::class.java)

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationApp)
        bottomNavigationView.visibility = View.GONE

        viewModel.downloadFoto()
        viewModel.imagemPerfil.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                imageViewFotoMeuDiaDetails.setImageURI(it)
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (verificarMeuDiaSelecionado())
            detalhesMeuDia(ObjetoUtil.meuDiaSelecionado!!)

        imageViewBackDetailsMeuDia.setOnClickListener {
            findNavController().popBackStack()
        }

        fabEditMeuDia.setOnClickListener {
            findNavController().navigate(R.id.formTicketFragment)
        }

        fabDeleteTicket.setOnClickListener {
            MeuDiaDaoFirestore().delete(ObjetoUtil.meuDiaSelecionado!!)
            findNavController().popBackStack()
        }
    }

    fun verificarMeuDiaSelecionado() = ObjetoUtil.meuDiaSelecionado != null

    @RequiresApi(Build.VERSION_CODES.M)
    private fun detalhesMeuDia (meuDia: MeuDia){
        textViewLocalDetail.setText(meuDia.descricao!!.getClearText())
        textViewDataDetail.setText(meuDia.data)
        textViewHorarioDetail.setText(meuDia.hora)
        textViewNomeDetail.setText(meuDia.titulo)
        textViewCategoriaDetail.setText(meuDia.tipo)
    }
}