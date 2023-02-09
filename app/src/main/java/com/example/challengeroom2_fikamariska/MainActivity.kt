package com.example.challengeroom2_fikamariska

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.challengeroom2_fikamariska.room.Constant
import com.example.challengeroom2_fikamariska.room.dbPerpustakaan
import com.example.challengeroom2_fikamariska.room.tbBuku
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var bukuAdapter: BukuAdapter
    val db by lazy { dbPerpustakaan (this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        halEdit()
        setuRecyclerView()
    }

    override fun onStart(){
        super.onStart()
        loadData()
    }

    fun loadData() {
        CoroutineScope(Dispatchers.IO).launch {
            val siswa = db.tbBookDAO().tampilsemua()
            Log.d("MainActivity", "dbResponse:$siswa")
            withContext(Dispatchers.Main) {
                bukuAdapter.setData(siswa)
            }
        }
    }

    private fun halEdit() {
        btnInput.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(tbbukuid: Int, intentType: Int) {
        startActivity(
            Intent(applicationContext, EditActivity::class.java)
                .putExtra("intent_id", tbbukuid)
                .putExtra("intent_type", intentType)
        )
    }

    fun setuRecyclerView(){
        bukuAdapter = BukuAdapter(arrayListOf(), object : BukuAdapter.OnAdapterListener {
            override fun onClick(tbbuk: tbBuku) {
                intentEdit(tbbuk.id_buku, Constant.TYPE_READ)
                // startActivity(Intent(applicationContext,EditActivity::class.java)
                //.putExtra("intent_nis",tbsis.nis))
            }

            override fun onUpdate(tbbuk: tbBuku) {
                intentEdit(tbbuk.id_buku, Constant.TYPE_UPDATE)
            }

            override fun onDelete(tbBuk: tbBuku) {
                deleteAlert(tbBuk)
            }

        })


        //idRecyclerView
        listdatasiswa.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = bukuAdapter
        }
    }

    private fun deleteAlert(tbBuk: tbBuku) {
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin hapus ${tbBuk.judul}?")
            setNegativeButton("Batal") { dialogInterface, i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus") { dialogInterface, i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbBookDAO().deltbbuku(tbBuk)
                    dialogInterface.dismiss()
                    loadData()
                }
            }
        }
        dialog.show()
    }


}