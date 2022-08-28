package com.yadahs.uimessage

/**
 * @Author Dushane Coram
 * @Date 27,August,2022
 * @Project MessageDialog
 * @Company Yadahs LLC,
 * @Copyright (c) 2022 Yadahs LLC. All rights reserved.
 * @Description
 */
data class MessageAttributes(
    val title: String,
    val shortMessage: String,
    val longMessage: String,
    val icon: Int,
    val action: Message.Action
    )
