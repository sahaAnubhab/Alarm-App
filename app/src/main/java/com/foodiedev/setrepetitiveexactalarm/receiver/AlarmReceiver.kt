package com.foodiedev.setrepetitiveexactalarm.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.text.format.DateFormat
import android.widget.Toast
import com.foodiedev.setrepetitiveexactalarm.service.AlarmService
import com.foodiedev.setrepetitiveexactalarm.util.Constants
import io.karn.notify.Notify
import timber.log.Timber
import java.util.*
import java.util.concurrent.TimeUnit

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val timeInMillis = intent.getLongExtra(Constants.EXTRA_EXACT_ALARM_TIME, 0L)
        var alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        if(alarmUri == null){
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        }
        val ringtone = RingtoneManager.getRingtone(context, alarmUri)
        ringtone.play()
        when (intent.action) {
            Constants.ACTION_SET_EXACT -> {
                buildNotification(context, "Alarm", convertDate(timeInMillis))
            }
        }
    }

    private fun buildNotification(context: Context, title: String, message: String) {
        Notify
            .with(context)
            .content {
                this.title = title
                text = "Alarm at - $message"
            }
            .show()
    }

    private fun convertDate(timeInMillis: Long): String =
        DateFormat.format("dd/MM/yyyy hh:mm:ss", timeInMillis).toString()

}