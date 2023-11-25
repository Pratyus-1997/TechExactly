package com.imm.techexactly.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmfooddeliveryapp.utils.common.Constant
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.imm.techexactly.MainActivity
import com.imm.techexactly.R
import com.imm.techexactly.adapter.ApplicationListAdapter
import com.imm.techexactly.model.ApplicationsListModel
import com.sueep.retrofit.ApiResponse
import kotlinx.android.synthetic.main.fragment_applications.*
import retrofit2.Call


class ApplicationsFragment(var mainActivity: MainActivity) : Fragment(), ApiResponse {

    private lateinit var programsRecyclerview: RecyclerView


    private lateinit var adapter: ApplicationListAdapter

    var getEventsApi: Call<JsonElement>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_applications, container, false)
    }


    override  fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        programsRecyclerview = view.findViewById(R.id.applicationRV)


        getEventsDataApi()



    }

    private fun getEventsDataApi() {


        getEventsApi = mainActivity.apiService!!.getAllApplication()
        mainActivity.parseApiData!!.hitApi(getEventsApi!!, true, mainActivity, this)



    }

    private fun eventsData(events: List<ApplicationsListModel.Data.App>?) {
        // programsRecyclerview.adapter = HomeProgramsRecylerviewAdapter(context,events,dashBoardScreen)

        programsRecyclerview.adapter =
            context?.let { ApplicationListAdapter(events,mainActivity) }

    }

    override fun onSuccess(call: Call<JsonElement>, responseCode: Int, response: String) {

        if(call == getEventsApi )
        {
            var jsonResponse= Gson().fromJson(response,ApplicationsListModel::class.java)

            eventsData(jsonResponse!!.data!!.appList)


            searchText(jsonResponse)


        }
        else
        {


        }


    }


    private fun searchText(jsonResponseP: ApplicationsListModel){

        searchProgramEdittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
            }
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {

            }

            override fun onTextChanged(
                query1: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                var query = query1
                query = query.toString().toUpperCase()
                val filteredList: ArrayList<ApplicationsListModel.Data.App> =
                    ArrayList()
                for (i in 0 until jsonResponseP.data!!.appList!!.size) {
                    val text: String =
                        jsonResponseP.data!!.appList!!.get(i).appName!!.toUpperCase()
                    if (text.contains(query)) {
                        filteredList.add(jsonResponseP.data!!.appList!!.get(i))
                    }
                }
                programsRecyclerview.adapter = ApplicationListAdapter(
                    filteredList,
                    mainActivity
                )
                programsRecyclerview.adapter!!.notifyDataSetChanged()
            }
        })

    }

    override fun onError(call: Call<JsonElement>, errorCode: Int, errorMsg: String) {

    }



}