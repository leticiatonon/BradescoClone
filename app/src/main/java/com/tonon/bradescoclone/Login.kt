package com.tonon.bradescoclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.tonon.bradescoclone.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportActionBar!!.hide()
        verificarUsuarioLogado()

        val cadastrar = binding.cadastrar
        cadastrar.setOnClickListener {
            val intent = Intent(this, Cadastrar::class.java)
            startActivity(intent)
        }


        val entrar = binding.btnEntrar

        entrar.setOnClickListener {
            val email = binding.email.text.toString()
            val senha = binding.senha.text.toString()
            val erro = binding.erro

            if (email.isEmpty() || senha.isEmpty()){
                erro.setText("Preencha todos os campos")
            }else{
                autenticarUsuario()
            }
        }
    }

    private fun autenticarUsuario(){
        val email = binding.email.text.toString()
        val senha = binding.senha.text.toString()
        val erro = binding.erro

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this, "Login efetuado com sucesso", Toast.LENGTH_SHORT).show()
                telaPrincipal()
            }
        }.addOnFailureListener {

            var falhou = it

            when{
                erro is FirebaseAuthInvalidCredentialsException -> erro.setText("Email ou senha incorretos")
                erro is FirebaseNetworkException -> erro.setText("Sem conexão com a internet")
                else -> erro.setText("erro ao logar usuário")
            }

        }
    }

    private fun telaPrincipal(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun verificarUsuarioLogado(){
        val usuarioLogado = FirebaseAuth.getInstance().currentUser
        if(usuarioLogado != null){
            telaPrincipal()
        }
    }
}