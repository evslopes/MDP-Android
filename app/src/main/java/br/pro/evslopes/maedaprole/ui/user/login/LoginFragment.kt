package br.pro.evslopes.maedaprole.ui.user.login

import android.content.Intent
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
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel

    private lateinit var callbackManager: CallbackManager
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.login_fragment, container, false)

        val bottomNavigationView: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavigationApp)
        bottomNavigationView.visibility = View.GONE

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.status.observe(viewLifecycleOwner, Observer {
            if(it){
                findNavController().navigate(R.id.listMeuDiaFragment)
            }
        })

        viewModel.msg.observe(viewLifecycleOwner, Observer {
            if(!it.isNullOrBlank())
                makeToast(it)
        })

        auth = FirebaseAuth.getInstance()
        callbackManager = CallbackManager.Factory.create()


        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Pass the activity result back to the Facebook SDK
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleFacebookAccessToken(token: AccessToken) {

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnSuccessListener { task ->
                val user = auth.currentUser
            }
            .addOnFailureListener {
                // If sign in fails, display a message to the user.
                Toast.makeText(requireContext(), "Authentication failed:${it.message}",
                    Toast.LENGTH_SHORT).show()
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonFacebookLogin.setReadPermissions("email", "public_profile")
        buttonFacebookLogin.fragment = this
        buttonFacebookLogin.registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    handleFacebookAccessToken(loginResult.accessToken)
                    findNavController().navigate(R.id.listMeuDiaFragment)
                }

                override fun onCancel() {
                    Toast.makeText(requireContext(), "Authentication cancel",
                        Toast.LENGTH_SHORT).show()
                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(requireContext(), "Authentication Error",
                        Toast.LENGTH_SHORT).show()
                }
            })

        btnLogin.setOnClickListener {
            val email = editTextEmailLogin.text.toString()
            val senha = editTextSenhaLogin.text.toString()
            if(validandoEmailESenha(email, senha)) {
                viewModel.verificandoCredenciais(email, senha)
            }
            else {
                makeToast("Favor preencher Email Senha")
            }
        }
        textViewCadastro.setOnClickListener {
            findNavController().navigate(R.id.cadastroFragment)
        }
    }
    private fun makeToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
    }

    fun validandoEmailESenha(email: String, senha: String) : Boolean {
        return !email.isNullOrBlank() && !senha.isNullOrBlank()
    }


}