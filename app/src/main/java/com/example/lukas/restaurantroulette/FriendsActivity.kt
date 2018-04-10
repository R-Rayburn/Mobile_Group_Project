package com.example.lukas.restaurantroulette

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.luksaul.restaurantroulette.CellData
import com.example.luksaul.restaurantroulette.CellViewAdapter
import kotlinx.android.synthetic.main.activity_friends.*

class FriendsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)


        var ab = getSupportActionBar()
        ab?.setTitle("Chat Project")
        ab?.setSubtitle("Friends List")

        ab?.setDisplayHomeAsUpEnabled(true)

        if (this.intent.hasExtra("userEmail")) {
            mUserEmail = this.intent.getStringExtra("userEmail")
            mUserImageUrl = this.intent.getStringExtra("userImageUrl")
        }
        else {
            Log.w("debug", "Activity requires a logged in user")
        }

        attachRecyclerView()

        addCelltoRecyclerView(CellData(mUserEmail,mUserImageUrl,"That's me"))
        addCelltoRecyclerView(CellData(mUserEmail,mUserImageUrl,"That's me 2"))
        addCelltoRecyclerView(CellData(mUserEmail,mUserImageUrl,"That's me 3"))
        addCelltoRecyclerView(CellData(mUserEmail,mUserImageUrl,"That's me 4"))
        addCelltoRecyclerView(CellData(mUserEmail,mUserImageUrl,"That's me 5"))
    }

    private var mUserEmail:String = ""
    private var mUserImageUrl:String = ""



    lateinit var adapter: CellViewAdapter

    private fun attachRecyclerView(){
        val manager = LinearLayoutManager(this)
        recyclerView_friends.setHasFixedSize(true)
        recyclerView_friends.layoutManager = manager
        recyclerView_friends.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))

        initializeRecyclerView()

    }


    private fun initializeRecyclerView() {
        adapter = CellViewAdapter{ view, position -> rowTapped(position)}
        recyclerView_friends.adapter = adapter
    }

    private fun rowTapped(position: Int){
        Log.d("debug", adapter.rows[position].headerTxt + " " + adapter.rows[position].messageText)
    }

    private fun addCelltoRecyclerView(cellData: CellData) {
        adapter.addCellData(cellData)
        recyclerView_friends.smoothScrollToPosition(adapter.getCellCount() - 1)
    }
}
