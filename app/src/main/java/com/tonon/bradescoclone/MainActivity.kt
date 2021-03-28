package com.tonon.bradescoclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.tonon.bradescoclone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        deslogar()

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        
        toggle = ActionBarDrawerToggle(this, drawerLayout,R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> Toast.makeText(applicationContext, "item 1 clicked", Toast.LENGTH_SHORT).show()
                R.id.item2 -> Toast.makeText(applicationContext, "item 2 clicked", Toast.LENGTH_SHORT).show()
                R.id.item3 -> Toast.makeText(applicationContext, "item 3 clicked", Toast.LENGTH_SHORT).show()
            }
            true
        }



        val transferencia = binding.main.tranferencia
        transferencia.setOnClickListener {
            val intent = Intent(this, Transferencia::class.java)
            startActivity(intent)
        }


        val cartoes = binding.main.cartoes
        cartoes.setOnClickListener {
            val intent = Intent(this, Cartoes::class.java)
            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deslogar(){
        val deslogar = binding.main.deslogar
        deslogar.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            voltarTelaLogin()
        }
    }

    private fun voltarTelaLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}