package com.ekoapp.chatkitmessagelistwithcustomui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ekoapp.ekosdk.uikit.chat.messages.adapter.EkoMessageListAdapter
import com.ekoapp.ekosdk.uikit.chat.messages.fragment.EkoMessageListFragment
import com.ekoapp.ekosdk.uikit.chat.messages.viewHolder.EkoChatMessageBaseViewHolder
import com.ekoapp.ekosdk.uikit.chat.util.MessageType

class MessageListActivity : AppCompatActivity(), EkoMessageListAdapter.ICustomViewHolder {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_list)

        val channelId = intent.getStringExtra("CHANNEL_ID") ?: ""
        val messageListFragment = EkoMessageListFragment.Builder(channelId)
            .build()
        messageListFragment.addCustomView(this)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, messageListFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun getViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): EkoChatMessageBaseViewHolder? {
        return when(viewType) {
            MessageType.MESSAGE_ID_TEXT_RECEIVER -> TextReceiverViewHolder(
                inflater.inflate(R.layout.item_text_receiver, parent, false), MyTextMsgViewModel())
            MessageType.MESSAGE_ID_TEXT_SENDER -> TextSenderViewHolder(
                inflater.inflate(R.layout.item_text_sender, parent, false), MyTextMsgViewModel()
            )
            else -> null
        }
    }
}