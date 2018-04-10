package com.example.lukas.restaurantroulette

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.luksaul.restaurantroulette.CellData
import com.example.luksaul.restaurantroulette.CellViewAdapter
import com.example.luksaul.restaurantroulette.ChatData
import com.example.luksaul.restaurantroulette.FireChatService
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        var ab = getSupportActionBar()
        ab?.setTitle("Chat Project")
        ab?.setSubtitle("Chat Room")

        ab?.setDisplayHomeAsUpEnabled(true)

        if(this.intent.hasExtra("userEmail")) {
            mUserEmail = this.intent.getStringExtra("userEmail")
            mUserImageUrl = this.intent.getStringExtra("userImageUrl")

        }
        else{
            Log.w("debug","Activity requires a logged in User")
        }

        attachRecyclerView()

        chatService.setupService(recyclerView_chat.context,{ message -> addMessageToRecyclerView(message)})

        sendButton.setOnClickListener({ view -> sendMessage()})
    }

    private var mUserEmail:String = ""
    private var mUserImageUrl:String = ""


    private val chatService = FireChatService.instance

    private fun sendMessage(){
        chatService.sendMessage(mUserEmail,mUserImageUrl,sendText.text.toString())
    }

    private fun addMessageToRecyclerView(message: ChatData?){
        if(message != null){
            val cellData = CellData(message.fromEmail,message.fromImageURL,message.message)
            addCelltoRecyclerView(cellData)
            sendText.setText("")
        }
    }

    lateinit var adapter: CellViewAdapter

    private fun attachRecyclerView(){
        val manager = LinearLayoutManager(this)
        recyclerView_chat.setHasFixedSize(true)
        recyclerView_chat.layoutManager = manager
        recyclerView_chat.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        initializeRecyclerView()
    }


    private fun initializeRecyclerView() {
        adapter = CellViewAdapter{ view, position -> rowTapped(position)}
        recyclerView_chat.adapter = adapter
    }

    private fun rowTapped(position: Int){
        Log.d("debug", adapter.rows[position].headerTxt + " " + adapter.rows[position].messageText)
    }

    private fun addCelltoRecyclerView(cellData: CellData) {
        adapter.addCellData(cellData)
        recyclerView_chat.smoothScrollToPosition(adapter.getCellCount() - 1)
    }
}
