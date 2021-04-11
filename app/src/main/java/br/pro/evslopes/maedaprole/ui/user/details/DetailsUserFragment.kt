package br.pro.evslopes.maedaprole.ui.user.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.evslopes.maedaprole.R
import br.pro.evslopes.maedaprole.data.MeuDiaDaoFirestore
import br.pro.evslopes.maedaprole.data.UserFirebaseDao
import br.pro.evslopes.maedaprole.model.User
import kotlinx.android.synthetic.main.details_user_fragment.*

class DetailsUserFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsUserFragment()
    }

    private lateinit var viewModel: DetailsUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.details_user_fragment, container, false)
        val detailUserViewModelFactory = DetailsUserViewModelFactory(MeuDiaDaoFirestore())
        viewModel = ViewModelProvider(this, detailUserViewModelFactory).get(DetailsUserViewModel::class.java)

        viewModel.usuario.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                preencherInformacoesPerfil(it)
            }
            else if(UserFirebaseDao.firebaseAuth.currentUser == null) {
                findNavController().navigate(R.id.loginFragment)
                limparInformacoesPerfil()
            }
        })
        viewModel.attPerfil()
        return view
    }

    private fun limparInformacoesPerfil() {
        textViewNomeDetailUser.text = null
        textViewSobrenomeDetailUser.text = null
        textViewEmailDetailUser.text = null
        textViewIsDoulaDetailUser.text = null
    }

    private fun preencherInformacoesPerfil(usuario: User) {
        textViewNomeDetailUser.text = usuario.nome
        textViewSobrenomeDetailUser.text = usuario.sobrenome
        textViewEmailDetailUser.text = usuario.email
        textViewIsDoulaDetailUser.text = usuario.doula.toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fabEditUser.setOnClickListener {
            findNavController().navigate(R.id.formUserFragment)
        }

        btnLogout.setOnClickListener{
            viewModel.encerrarSessao()
        }
    }
}