package com.yadahs.uimessage

import java.lang.Exception

/**
 * @Author Dushane Coram
 * @Date 27,August,2022
 * @Project MessageDialog
 * @Company Yadahs LLC,
 * @Copyright (c) 2022 Yadahs LLC. All rights reserved.
 * @Description
 */
interface OnMessageListener {
    fun onShowDialogMessage(exception: Exception, action: Message.Action)
    fun onShowDialogMessage(attributes: MessageAttributes)

    fun onShowSnackMessage(exception: Exception)
    fun onShowSnackMessage(message: String)

    fun onShowToastMessage(exception: Exception)
    fun onShowToastMessage(message: String)
}