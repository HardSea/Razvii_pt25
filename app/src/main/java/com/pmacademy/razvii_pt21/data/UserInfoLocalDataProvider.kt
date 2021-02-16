package com.pmacademy.razvii_pt21.data

import com.pmacademy.razvii_pt21.data.model.UserInfoModel
import com.pmacademy.razvii_pt21.data.model.UserStatusType

class UserInfoLocalDataProvider {

    private val statusSet = mutableSetOf<UserInfoModel>()

    fun getLocalSetStatusUser(): Set<UserInfoModel> = statusSet

    private fun initLocalList() {
        addLocalStatusUser(3, UserStatusType.WARNING)
        addLocalStatusUser(4, UserStatusType.WARNING)
        addLocalStatusUser(7, UserStatusType.BANNED)
    }

    private fun addLocalStatusUser(userId: Int, userStatus: UserStatusType){
        statusSet.add(UserInfoModel(userId, userStatus))
    }

    init {
        initLocalList()
    }

}