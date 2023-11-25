package com.imm.techexactly.adapter

import android.view.*
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imm.techexactly.MainActivity
import com.imm.techexactly.R
import com.imm.techexactly.model.ApplicationsListModel
import java.util.*


class ApplicationListAdapter(
     private val getApplicationList: List<ApplicationsListModel.Data.App>?, var mainActivity: MainActivity
) :
    RecyclerView.Adapter<ApplicationListAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.applicatiolistitem,
            parent, false
        )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        //  val event = getEventsList?.get(position)
        holder.applicationName.text = getApplicationList!!.get(position).appName

        Glide.with(mainActivity).load(getApplicationList!!.get(position).appIcon)
            .placeholder(R.drawable.dummy_user_img)
            .error(R.drawable.dummy_user_img)
            .into(holder.applicationImg)





    }



    override fun getItemCount(): Int {
        return getApplicationList!!.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var applicationName : TextView = itemView.findViewById(R.id.applicationListName)

        var applicationImg :  ImageView= itemView.findViewById(R.id.applicationListImg)

        var toggleBtn : ToggleButton = itemView.findViewById(R.id.toggleButton1)


    }


}