package com.computer.inu.myworkinggings.Jemin.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.computer.inu.myworkinggings.Jemin.Adapter.GuestActAdapter
import com.computer.inu.myworkinggings.Jemin.Get.Response.GetData.GetActiveData
import com.computer.inu.myworkinggings.Jemin.Get.Response.GetOtherActiveResponse
import com.computer.inu.myworkinggings.Network.ApplicationController
import com.computer.inu.myworkinggings.Network.NetworkService
import com.computer.inu.myworkinggings.R
import kotlinx.android.synthetic.main.fragment_mypage_act.*
import com.computer.inu.myworkinggings.Jemin.Adapter.GuestBoardAdapter
import com.computer.inu.myworkinggings.Jemin.Data.GuestActItem
import com.computer.inu.myworkinggings.Jemin.Data.GuestBoardItem
import com.computer.inu.myworkinggings.Moohyeon.Data.MyActData
import com.computer.inu.myworkinggings.Moohyeon.Data.ReplysData
import com.computer.inu.myworkinggings.Moohyeon.get.GetMypageActResponse
import com.computer.inu.myworkinggings.Moohyeon.get.GetMypageIntroduceResponse
import kotlinx.android.synthetic.main.fragment_mypage_act.view.*
import kotlinx.android.synthetic.main.fragmet_my_page_introduce.*
import kotlinx.android.synthetic.main.fragmet_my_page_introduce.view.*
import org.jetbrains.anko.support.v4.ctx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MypageActFragment : Fragment(){

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var getOtherActiveData = ArrayList<GetActiveData>()
    var guestActItem =  ArrayList<GuestActItem>()
    lateinit var guestActAdapter : GuestActAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v : View = inflater.inflate(R.layout.fragment_mypage_act,container,false)
        getOtherActive()
        return v
    }

    fun getOtherActive() {
        var getOtherActiveResponse = networkService.getOtherActive("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjksInJvbGUiOiJVU0VSIiwiaXNzIjoiR2luZ3MgVXNlciBBdXRoIE1hbmFnZXIiLCJleHAiOjE1NDkwODg1Mjd9.P7rYzg9pNtc31--pL8qGYkC7cx2G93HhaizWlvForfg", 1) // 네트워크 서비스의 getContent 함수를 받아옴
        getOtherActiveResponse.enqueue(object : Callback<GetOtherActiveResponse> {
            override fun onResponse(call: Call<GetOtherActiveResponse>?, response: Response<GetOtherActiveResponse>?) {
                Log.v("TAG", "타인 활동 목록 서버 통신 연결")
                if (response!!.isSuccessful) {
                    Log.v("TAG", "타인 활동 목록 조회 성공")
                    getOtherActiveData = response.body()!!.data

                    guestActAdapter = GuestActAdapter(context!!, getOtherActiveData)
                    mypage_act_recyclerview.layoutManager = LinearLayoutManager(activity)
                    mypage_act_recyclerview.adapter = guestActAdapter
                }
            }

            override fun onFailure(call: Call<GetOtherActiveResponse>?, t: Throwable?) {
                Log.v("TAG", "타인 활동 목록 서버 연결 실패 = " + t.toString())

            }
        })
    }
    fun getMyAct() {
        var getMypageActResponse = networkService.getMypageActResponse("application/json","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOjksInJvbGUiOiJVU0VSIiwiaXNzIjoiR2luZ3MgVXNlciBBdXRoIE1hbmFnZXIiLCJleHAiOjE1NDkwODg1Mjd9.P7rYzg9pNtc31--pL8qGYkC7cx2G93HhaizWlvForfg") // 네트워크 서비스의 getContent 함수를 받아옴
        getMypageActResponse.enqueue(object : Callback<GetMypageActResponse> {
            override fun onResponse(call: Call<GetMypageActResponse>?, response: Response<GetMypageActResponse>?) {
                Log.v("TAG", "나의 활동 페이지 서버 통신 연결")
                if (response!!.isSuccessful) {
                    Log.v("MyTAG", "나의 활동 페이지 서버 통신 연결 성공")
                    val temp : ArrayList<MyActData> = response.body()!!.data

                }
            }

            override fun onFailure(call: Call<GetMypageActResponse>?, t: Throwable?) {
                Log.v("MyTAG", "통신 실패 = " + t.toString())
            }
        })
    }
}