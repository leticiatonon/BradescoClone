package com.tonon.bradescoclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.tonon.bradescoclone.databinding.ActivityCadastrarBinding

class Cadastrar : AppCompatActivity() {

    private lateinit var binding: ActivityCadastrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar!!.hide()


        val cadastrar = binding.btnCadastrar
        cadastrar.setOnClickListener {
            val email = binding.cadastrarEmail.text.toString()
            val senha = binding.cadastrarSenha.text.toString()
            val erro = binding.erroCadastro

            if (email.isEmpty() || senha.isEmpty()){
                erro.setText("Preencha todos os campos")
            }else{
                cadastrarUsuario()
            }
        }

    }

    private fun cadastrarUsuario(){
        val email = binding.cadastrarEmail.text.toString()
        val senha = binding.cadastrarSenha.text.toString()
        val erro = binding.erroCadastro

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"Usuário cadastrado com sucesso",Toast.LENGTH_SHORT).show()
                binding.cadastrarEmail.setText("")
                binding.cadastrarSenha.setText("")
                binding.erroCadastro.setText("")
            }
        }.addOnFailureListener {

            val falhou = it

            when {
                falhou is FirebaseAuthWeakPasswordException -> erro.setText("Digite uma senha com no mínimo 6 caracteres")
                falhou is FirebaseAuthUserCollisionException -> erro.setText("Essa conta já foi cadastrada")
                falhou is FirebaseNetworkException -> erro.setText("Sem conexão com a internet")
                else -> erro.setText("Erro ao cadastrar usuário")
            }
        }
    }

    private fun toolbar(){
        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_back))

    }
}