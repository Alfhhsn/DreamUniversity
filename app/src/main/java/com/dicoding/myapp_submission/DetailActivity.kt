package com.dicoding.myapp_submission

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("UNIV_NAME")
        val description = intent.getStringExtra("UNIV_DESCRIPTION")
        val photo = intent.getIntExtra("UNIV_PHOTO", 0)
        val tanggal = intent.getStringExtra("UNIV_DATE")
        val alamat = intent.getStringExtra("UNIV_ADDRESS")
        val email = intent.getStringExtra("UNIV_EMAIL")
        val jumlah = intent.getIntExtra("UNIV_JUMLAH_FAKULTAS", 0)

        val imgUniversity: ImageView = findViewById(R.id.img_item_photo)
        val tvName: TextView = findViewById(R.id.tv_item_name)
        val tvDescription: TextView = findViewById(R.id.tv_item_description)
        val tvTanggal: TextView = findViewById(R.id.tv_item_tanggal)
        val tvAlamat: TextView = findViewById(R.id.tv_item_alamat)
        val tvEmail: TextView = findViewById(R.id.tv_item_email)
        val intJumlah: TextView = findViewById(R.id.tv_item_jumlah)
        val button: Button = findViewById(R.id.action_share)

        imgUniversity.setImageResource(photo)
        tvName.text = name
        tvDescription.text = description
        tvTanggal.text = tanggal
        tvAlamat.text = alamat
        tvEmail.text = email
        intJumlah.text = "Jumlah Fakultas: $jumlah"
        button.setOnClickListener {
            val intentActionShare = Intent()
            intentActionShare.action = Intent.ACTION_SEND
            intentActionShare.putExtra(
                Intent.EXTRA_TEXT,
                "Hei ada universitas menarik nih \n" +
                        "Nama:$name \n" +
                        "Alamat: $alamat \n" +
                        "Kalau tertarik hubungi: $email"
            )
            intentActionShare.type = "text/plain"
            startActivity(Intent.createChooser(intentActionShare, "Share To:"))
        }

    }
}

