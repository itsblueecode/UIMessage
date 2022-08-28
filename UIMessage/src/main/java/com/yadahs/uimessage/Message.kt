package com.yadahs.uimessage

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

/**
 * @Author Dushane Coram
 * @Date 25,August,2022
 * @Project MessageDialog
 * @Company Yadahs LLC
 * @Copyright (c) 2022 Yadahs LLC. All rights reserved.
 * @Description
 */
object Message {

    enum class Action {
        IGNORE, POP_FRAGMENT, CLEAR_BACK_STACK, CLOSE_ACTIVITY, RESTART_APP
    }

    private lateinit var builder: AlertDialog.Builder
    private var alertDialog: AlertDialog? = null

    fun showMessageDialog(
        context: Context?,
        attributes: MessageAttributes,
        options: DialogAttributes
    ) : AlertDialog? {
        return try {
            context?.let {
                hideDialog(alertDialog)
                builder = AlertDialog.Builder(context, options.dialogTheme)
                builder.setTitle(attributes.title)
                builder.setIcon(attributes.icon)
                builder.setMessage(attributes.longMessage)
                builder.setCancelable(false)
                builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }

                // Create a new AlertDialog object.
                alertDialog = builder.create()
                // Show Alert Dialog
                alertDialog?.show()
                // Set button color to colorPrimary.
                alertDialog?.getButton(DialogInterface.BUTTON_POSITIVE)?.setTextColor(
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            it.applicationContext,
                            options.positiveButtonTextColor
                        )
                    )
                )
                alertDialog
            }
        } catch (e: Exception) {
            Log.e(
                "Message",
                e.message ?: "An error occurred while trying to display the message"
            )
            null
        }
    }

    private fun hideDialog(alertDialog: AlertDialog?) {
        alertDialog?.let {
            if (it.isShowing) {
                it.hide()
            }
        }
    }

    fun showSnackBarMessage(
        context: Context?,
        message: String?
    ) {
        Snackbar.make((context as AppCompatActivity).findViewById(android.R.id.content), message.toString(), Snackbar.LENGTH_SHORT).show()
    }

    fun showToastMessage(
        context: Context?,
        message: String?
    ) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}