package com.example.modul10_firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MahasiswaAdapter(private val userList: ArrayList<Mahasiswa>) : RecyclerView.Adapter<MahasiswaAdapter.MyViewHolder>() {
//class mahasiswa adapter memupunyai variable userlist yg akan menampung data mahasiswa
//yg berasal dari Mahasiswa.kt yg dalam bentuk array


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }
    //untuk menampilkan data item layout (yg cardview)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.nama.text = currentItem.nama
        holder.nim.text = currentItem.nim
        holder.telp.text = currentItem.telp
    }
    //inisialisasi nama berdasaran parameter yg ada di userList

    override fun getItemCount(): Int {
        return userList.size
    }
    //untuk menghitung item listnya sesuai jumlah data yg ada difirebase

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.namaTextView)
        val nim: TextView = itemView.findViewById(R.id.nimTextView)
        val telp: TextView = itemView.findViewById(R.id.telpTextView)
    }
    //untuk mengarahkan misal variable nama diambil berdasarkan id nama textview yg ada di itemlayout(cardview)
}
