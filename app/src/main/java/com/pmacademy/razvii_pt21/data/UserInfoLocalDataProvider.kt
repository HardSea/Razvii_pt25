package com.pmacademy.razvii_pt21.data

import com.pmacademy.razvii_pt21.domain.UserInfo
import com.pmacademy.razvii_pt21.domain.UserStatusType

class UserInfoLocalDataProvider {

    private val statusSet = mutableSetOf<UserInfo>()

    fun getLocalSetStatusUser(): Set<UserInfo> = statusSet

    private fun initLocalList() {
        addLocalStatusUser(3, UserStatusType.WARNING)
        addLocalStatusUser(4, UserStatusType.WARNING)
        addLocalStatusUser(7, UserStatusType.BANNED)
    }

    private fun addLocalStatusUser(userId: Int, userStatus: UserStatusType){
        statusSet.add(UserInfo(userId, userStatus))
    }

    init {
        initLocalList()
    }

//    fun remove(userId: Int){
//        statusSet.removeIf { it.userId == userId }
//    }
}