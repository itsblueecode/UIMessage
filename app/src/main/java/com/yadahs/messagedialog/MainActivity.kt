package com.yadahs.messagedialog

import android.app.AlertDialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yadahs.uimessage.Message
import com.yadahs.uimessage.MessageAttributes
import com.yadahs.uimessage.DialogAttributes
import com.yadahs.uimessage.OnMessageListener

class MainActivity : AppCompatActivity(), OnMessageListener {

    private var alertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set message listener
        AppApplication.getInstance().messageListener = this

        findViewById<TextView>(R.id.simple_textview).setOnClickListener {
            AppApplication.getInstance().messageListener.onShowDialogMessage(
                MessageAttributes(
                    "Simple Title",
                    "Simple Short Message",
                    "Simple Long Message",
                    R.drawable.ic_info_outline,
                    Message.Action.IGNORE
                )
            )
        }

        findViewById<TextView>(R.id.dialog_error_textview).setOnClickListener {
            AppApplication.getInstance().messageListener.onShowDialogMessage(
                Exception("This is a dialog error"),
                Message.Action.IGNORE
            )
        }

        findViewById<TextView>(R.id.snack_bar_error_textview).setOnClickListener {
            AppApplication.getInstance().messageListener.onShowSnackMessage(
                Exception("This is a snack bar error")
            )
        }

        findViewById<TextView>(R.id.toast_error_textview).setOnClickListener {
            AppApplication.getInstance().messageListener.onShowToastMessage(
                Exception("This is a toast error")
            )
        }
    }

    override fun onShowDialogMessage(attributes: MessageAttributes) {
        showDialogMessage(attributes)
    }

    override fun onShowDialogMessage(
        exception: Exception,
        action: Message.Action
    ) {
        // get error details from ErrorManager
        // Build a MessageAttributes From results
        val messageAttributes = MessageAttributes(
            "Error Title",
            "Error Short Message",
            "Error Long Message",
            R.drawable.ic_info_outline,
            action
        )
        // show message
        showDialogMessage(messageAttributes)
    }

    override fun onShowSnackMessage(exception: Exception) {
        // get error details from ErrorManager
        Message.showSnackBarMessage(this, exception.message)
    }

    override fun onShowSnackMessage(message: String) {
        Message.showSnackBarMessage(this, message)
    }

    override fun onShowToastMessage(exception: Exception) {
        // get error details from ErrorManager
        Message.showToastMessage(this, exception.message)
    }

    override fun onShowToastMessage(message: String) {
        Message.showToastMessage(this, message)
    }

    private fun showDialogMessage(attributes: MessageAttributes) {
        val dialogAttributes = DialogAttributes(
            dialogTheme = R.style.alert_dialog_style,
            positiveButtonTextColor = R.color.dialog_positive_button_text_color
        )
        alertDialog = Message.showMessageDialog(this, attributes, dialogAttributes)
        alertDialog?.setOnDismissListener {
            when (attributes.action) {
                Message.Action.IGNORE -> {
                    // DO NOTHING
                    println("ignore")
                }
                Message.Action.POP_FRAGMENT -> {
                    println("pop fragment")
                }
                Message.Action.RESTART_APP -> {
                    println("restart app")
                }
                Message.Action.CLOSE_ACTIVITY -> {
                    println("close activity")
                    finishAffinity()
                }
                Message.Action.CLEAR_BACK_STACK -> {
                    println("clear back stack")
                    // FragmentUtil.clearBackStack(this)
                }
            }
        }
    }




}