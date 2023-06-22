package com.example.modul10_firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private lateinit var userRecyclerView: RecyclerView //berisi recycle view
    private lateinit var adapter: MahasiswaAdapter //var adapter mengarahkan ke class MahasiswaAdapter.kt
    private lateinit var userList: ArrayList<Mahasiswa> //var userList diarahkan ke class Mahasiswa.kt
    private lateinit var database: DatabaseReference //databasereference berasal dari property database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//tampilan awal ke activitymain

        // Inisialisasi RecyclerView
        userRecyclerView = findViewById(R.id.recyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)

        // Inisialisasi ArrayList dan Adapter
        userList = ArrayList()
        adapter = MahasiswaAdapter(userList)
        userRecyclerView.adapter = adapter

        // Mendapatkan referensi database Firebase
        database = FirebaseDatabase.getInstance().getReference("mahasiswa")

        // Mendapatkan data dari Firebase
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Hapus data sebelumnya dari userList
                userList.clear()

                // Loop melalui setiap item data dan tambahkan ke userList
                for (snapshot in dataSnapshot.children) {
                    val mahasiswa = snapshot.getValue(Mahasiswa::class.java)
                    mahasiswa?.let {
                        userList.add(it)
                    }
                }

                // Memperbarui RecyclerView setelah mendapatkan data baru
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Penanganan kesalahan saat mengakses Firebase
            }
        })
    }
}