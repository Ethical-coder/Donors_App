package com.example.donors

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.donors.activity.SecondActivity
import com.example.donors.adapter.ActivityMainRVAdapter
import com.example.donors.constant.DATA_RV_MAIN
import com.example.donors.databinding.ActivityMainBinding
import com.example.donors.library.RecyclerItemClickListenr
import kotlinx.android.synthetic.main.activity_main.*

lateinit var adapter: ActivityMainRVAdapter

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate( layoutInflater )

        setContentView( binding.root )

        binding.mainRvMain.layoutManager = LinearLayoutManager( this )
        adapter = ActivityMainRVAdapter()
        binding.mainRvMain.adapter = adapter
        adapter.set( DATA_RV_MAIN )

        binding.mainRvMain.addOnItemTouchListener(
            RecyclerItemClickListenr(
                this ,
                main_rv_main ,
                object :
                    RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        itemOnClickRVMainListner( this@MainActivity , view , adapter.data[position] )
                        Toast.makeText(
                            this@MainActivity,
                            "${adapter.data[position]} clicked",
                            Toast.LENGTH_LONG
                        ).show()
                    } // to Activity 2

                    override fun onItemLongClick(view: View?, position: Int) {}
                }
            )
        )

    }

    private fun itemOnClickRVMainListner(context:Context, view: View, itemType: String ){
        val intent = Intent( context , SecondActivity::class.java )
        intent.putExtra("TYPE" , itemType)
        intent.also {
            startActivity(it)
        }
    }

}