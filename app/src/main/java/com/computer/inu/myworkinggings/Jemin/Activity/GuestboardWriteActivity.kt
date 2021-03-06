package com.computer.inu.myworkinggings.Jemin.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.computer.inu.myworkinggings.Jemin.POST.PostResponse
import com.computer.inu.myworkinggings.Network.ApplicationController
import com.computer.inu.myworkinggings.Network.NetworkService
import com.computer.inu.myworkinggings.R
import com.computer.inu.myworkinggings.Seunghee.db.SharedPreferenceController
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_guestboard_write.*
import org.jetbrains.anko.ctx
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuestboardWriteActivity : AppCompatActivity() {

    var userID : Int = 0
    var name : String = ""

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guestboard_write)

        userID = intent.getIntExtra("userID", 0)
        name = intent.getStringExtra("name")
        guest_board_write_title_tv.text = name +"님의 Guest Board"

        guest_board_write_confirm_btn.setOnClickListener {
            postGuestBoard()
        }
    }

    fun postGuestBoard()
    {
        var jsonObject = JSONObject()
        jsonObject.put("content", guest_board_write_content_edit.text.toString())

        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        var postOtherGuestBoardResponse = networkService.postOtherGuestBoard(SharedPreferenceController.getAuthorization(ctx), userID, gsonObject)
        postOtherGuestBoardResponse.enqueue(object : Callback<PostResponse> {

            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                if(response.isSuccessful){
                    Log.v("asdf", "게스트보드 등록 성공")
                    val intent : Intent = Intent()
                    setResult(10)
                    finish()
                }
            }
            override fun onFailure(call: Call<PostResponse>, t: Throwable?) {
            }
        })
    }
}
