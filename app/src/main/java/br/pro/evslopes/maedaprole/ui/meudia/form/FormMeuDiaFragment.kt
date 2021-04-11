package br.pro.evslopes.maedaprole.ui.meudia.form

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.pro.evslopes.maedaprole.R
import br.pro.evslopes.maedaprole.data.CriptoString
import br.pro.evslopes.maedaprole.data.MeuDiaDaoFirestore
import br.pro.evslopes.maedaprole.data.ObjetoUtil
import br.pro.evslopes.maedaprole.model.MeuDia
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.form_meudia_fragment.*

class FormMeuDiaFragment : Fragment() {
    private lateinit var viewModel: FormMeuDiaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_meudia_fragment, container, false)

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationApp)
        bottomNavigationView.visibility = View.GONE

        val formTicketViewModelFactory = FormMeuDiaViewModelFactory(requireActivity().application, MeuDiaDaoFirestore())

        viewModel = ViewModelProvider(this, formTicketViewModelFactory).get(FormMeuDiaViewModel::class.java)

        viewModel.let {
            it.message.observe(viewLifecycleOwner, Observer { message ->
                if(!message.isNullOrBlank()) {
                    makeToast(message)
                }
            })
            it.status.observe(viewLifecycleOwner, Observer { status ->
                    if(status) {
                        findNavController().popBackStack(R.id.listMeuDiaFragment, false)
                    }
            })

        }

        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ArrayAdapter.createFromResource(requireActivity(), R.array.categorias_array, android.R.layout.simple_spinner_dropdown_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCategoriaAddMeuDia.adapter = adapter
        }
        spinnerCategoriaAddMeuDia.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.categoriaSelecionada(parent!!.getItemAtPosition(position).toString())
                makeToast("${viewModel.categoriaSelecionadaString}")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        if(ObjetoUtil.meuDiaSelecionado != null) {
            preencherFormulario(ObjetoUtil.meuDiaSelecionado!!)
        }

        imageViewBackFormTicket.setOnClickListener {
            findNavController().popBackStack()
        }

        fabSaveTicket.setOnClickListener {
            val local = CriptoString()
            local.setClearText(editTextLocalAddMeuDia.text.toString())
            val nome = editTextNomeAddMeuDia.text.toString()
            val data = editTextDataAddMeuDia.text.toString()
            val hora = editTextHoraAddMeuDia.text.toString()
            val categoria = viewModel.categoriaSelecionadaString
            if(verificarCategoriaPadrao(categoria)) {
                makeToast("Categoria deve ser selecionada!!")
            }
            else if (verificarCamposVazios(local, nome, data, hora)){
                viewModel.salvarTicket(nome, local, data, hora, categoria!!)
            }
            else {
                makeToast("Todos os campos devem ser preenchidos!")
            }
        }

        imageViewAddMeuDiaImage.setOnClickListener {
            tirarFoto()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun verificarCamposVazios(local: CriptoString, nome: String, data: String, hora: String) =
        !local.getClearText().isNullOrBlank() && !nome.isNullOrBlank() && !data.isNullOrBlank() && !hora.isNullOrBlank()

    fun verificarCategoriaPadrao(categoria: String?) =
        categoria == "Selecionar Categoria"

    private fun tirarFoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            var imageBitmap = data!!.extras!!.get("data") as Bitmap
            imageViewAddMeuDiaImage.setImageBitmap(imageBitmap)
            viewModel.alterarImagemPerfil(imageBitmap)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun preencherFormulario(meuDia: MeuDia) {
        editTextLocalAddMeuDia.setText(meuDia.local!!.getClearText())
        editTextNomeAddMeuDia.setText(meuDia.nome)
        editTextDataAddMeuDia.setText(meuDia.data)
        editTextHoraAddMeuDia.setText(meuDia.hora)
        spinnerCategoriaAddMeuDia.setSelection((spinnerCategoriaAddMeuDia.getAdapter() as? ArrayAdapter<String>)!!.getPosition(meuDia.categoria))

        viewModel.receberFoto()
        viewModel.imagemTicket.observe(viewLifecycleOwner, Observer { foto ->
            if(foto != null) {
                imageViewAddMeuDiaImage.setImageURI(foto)
            }
        })
    }

    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }
}