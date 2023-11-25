package com.imm.techexactly.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName



class ApplicationsListModel {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    class Data {
        @SerializedName("app_list")
        @Expose
        var appList: List<App>? = null

        @SerializedName("usage_access")
        @Expose
        var usageAccess: Int? = null


        class App {
            @SerializedName("app_id")
            @Expose
            var appId: Int? = null

            @SerializedName("fk_kid_id")
            @Expose
            var fkKidId: Int? = null

            @SerializedName("kid_profile_image")
            @Expose
            var kidProfileImage: String? = null

            @SerializedName("app_name")
            @Expose
            var appName: String? = null

            @SerializedName("app_icon")
            @Expose
            var appIcon: String? = null

            @SerializedName("app_package_name")
            @Expose
            var appPackageName: String? = null

            @SerializedName("status")
            @Expose
            var status: String? = null
        }
    }
}