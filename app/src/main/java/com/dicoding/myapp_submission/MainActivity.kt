package com.dicoding.myapp_submission

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var button: ImageButton
    private lateinit var rvUniversity: RecyclerView
    private val list = ArrayList<University>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        button = findViewById(R.id.about_page)
        button.setOnClickListener{
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }
        rvUniversity = findViewById(R.id.rv_university)
        rvUniversity.setHasFixedSize(true)

        list.addAll(getListUniversity())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListUniversity(): ArrayList<University> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataTanggal = resources.getStringArray(R.array.data_tanggal)
        val dataAlamat = resources.getStringArray(R.array.data_alamat)
        val dataEmail = resources.getStringArray(R.array.data_email)
        val dataJumlah = resources.getIntArray(R.array.data_jumlah_fakultas)

        val listUniversity = ArrayList<University>()
        for (i in dataName.indices) {
            val univ = University(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataTanggal[i], dataAlamat[i], dataEmail[i], dataJumlah[i])
            listUniversity.add(univ)
        }
        return listUniversity
    }

    private fun showRecyclerList() {
        rvUniversity.layoutManager = LinearLayoutManager(this)
        val listUnivAdapter = ListUnivAdapter(list){
            showSelectedUniv(it)
        }
        rvUniversity.adapter = listUnivAdapter
    }

    private fun showSelectedUniv(univ: University) {
        Toast.makeText(this, "You Choose " + univ.name, Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("UNIV_NAME", univ.name)
        intent.putExtra("UNIV_DESCRIPTION", univ.description)
        intent.putExtra("UNIV_PHOTO", univ.photo)
        intent.putExtra("UNIV_DATE", univ.tanggal)
        intent.putExtra("UNIV_ADDRESS", univ.alamat)
        intent.putExtra("UNIV_EMAIL", univ.email)
        intent.putExtra("UNIV_JUMLAH_FAKULTAS", univ.jumlah)
        startActivity(intent)
    }
}