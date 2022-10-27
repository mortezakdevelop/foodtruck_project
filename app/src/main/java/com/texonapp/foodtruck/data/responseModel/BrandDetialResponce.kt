package com.texonapp.foodtruck.data.responseModel

import com.google.gson.annotations.SerializedName
import com.texonapp.foodtruck.data.model.Food
import com.texonapp.foodtruck.data.model.Testimations
import com.texonapp.foodtruck.data.model.WorkHours
import com.texonapp.foodtruck.model.Link

class BrandDetialResponce {

    @SerializedName("data")
    val brandDetail: BrandDetail? = null



    inner class BrandDetail {

        @SerializedName("foods")
        val foods: ArrayList<Food>? = null

        @SerializedName("links")
        val links: Link? = null


        @SerializedName("testimonials")
        val testimonial: ArrayList<Testimations>? = null

        @SerializedName("working_hours")
        val workHours: ArrayList<WorkHours>? = null

        @SerializedName("id")
        val id: Int = null!!

        @SerializedName("name")
        val name: String = null!!

        @SerializedName("logo")
        val logo: String = null!!

        @SerializedName("image")
        val image: String = null!!

        @SerializedName("address")
        val address: String = null!!

        @SerializedName("description")
        val description: String = null!!
    }
}