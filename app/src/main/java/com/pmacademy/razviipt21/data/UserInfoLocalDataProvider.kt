package com.pmacademy.razviipt21.data

import com.pmacademy.razviipt21.data.model.UserInfoModel
import com.pmacademy.razviipt21.data.model.UserStatusType

class UserInfoLocalDataProvider {

    private companion object {
        const val warningUserId1 = 3
        const val warningUserId2 = 4
        const val bannedUserId1 = 7
    }

    private val statusSet = mutableSetOf<UserInfoModel>()

    fun getLocalSetStatusUser(): Set<UserInfoModel> = statusSet

    private fun initLocalList() {
        addLocalStatusUser(warningUserId1, UserStatusType.WARNING)
        addLocalStatusUser(warningUserId2, UserStatusType.WARNING)
        addLocalStatusUser(bannedUserId1, UserStatusType.BANNED)
    }

    private fun addLocalStatusUser(userId: Int, userStatus: UserStatusType) {
        statusSet.add(UserInfoModel(userId, userStatus))
    }

    init {
        initLocalList()
    }
}
