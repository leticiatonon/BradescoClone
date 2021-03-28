package com.tonon.bradescoclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tonon.bradescoclone.databinding.ActivityCartoesBinding

class Cartoes : AppCompatActivity() {

    private lateinit var binding: ActivityCartoesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val toolbar = binding.toolbar
        toolbar.title = "Cartões"
        toolbar.setTitleTextColor(getColor(R.color.white))
        toolbar.setTitleMargin(200, 0, 0, 0)
        toolbar.setBackgroundColor(getColor(R.color.red))
        toolbar.setNavigationIcon(getDrawable(R.drawable.ic_back))
        toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}