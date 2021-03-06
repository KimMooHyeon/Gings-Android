package com.computer.inu.myworkinggings.Jemin.Adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.computer.inu.myworkinggings.R

class ChatViewHolder(itemView : View?) : RecyclerView.ViewHolder(itemView!!) {

    var chatProfileLayout : LinearLayout = itemView!!.findViewById(R.id.chat_profile_layout)
    var chatUserImg : ImageView = itemView!!.findViewById(R.id.chat_profile_img)
    var chatUserName : TextView = itemView!!.findViewById(R.id.chat_username_tv)
    var chatContent : TextView = itemView!!.findViewById(R.id.chat_chat_content_tv)
    var chatMainLayout : LinearLayout = itemView!!.findViewById(R.id.chat_content_layout)
    var chatMineViewLayout : LinearLayout = itemView!!.findViewById(R.id.chat_mine_view_layout)
    var chatChatLayout : LinearLayout = itemView!!.findViewById(R.id.chat_chatting_layout)
    var chatLeftTime : TextView = itemView!!.findViewById(R.id.chat_left_time_tv)
    var chatRightTime : TextView = itemView!!.findViewById(R.id.chat_right_time_tv)
    var chatGapLayout : LinearLayout = itemView!!.findViewById(R.id.chat_gap_layout)
    var chatGap2Layout : LinearLayout = itemView!!.findViewById(R.id.chat_gap2_layout)
}