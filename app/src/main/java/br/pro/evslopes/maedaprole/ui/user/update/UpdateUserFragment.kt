package br.pro.evslopes.maedaprole.ui.user.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.evslopes.maedaprole.R
import kotlinx.android.synthetic.main.form_user_fragment.*

class UpdateUserFragment : Fragment() {

    private lateinit var viewModel: UpdateUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_user_fragment, container, false)

        viewModel = ViewModelProvider(this).get(UpdateUserViewModel::class.java)

        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().popBackStack()
            }
        })

        return  view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewBackUpdateUsuario.setOnClickListener {
            findNavController().popBackStack()
        }

        fabSaveAtualizacaoUsuario.setOnClickListener {
            var nome = editTextNomeAtualizacaoUsuario.text.toString()
            var sobrenome = editTextSobrenomeAtualizacaoUsuario.text.toString()

            if(nomeSobrenomeVazios(nome, sobrenome)) {
                viewModel.updateUsuario(nome, sobrenome)
            }
            else {
                makeToast("Favor preencher todos os campos!")
            }
        }
    }

    fun nomeSobrenomeVazios(nome: String, sobrenome: String) = !nome.isNullOrBlank() && !sobrenome.isNullOrBlank()

    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}