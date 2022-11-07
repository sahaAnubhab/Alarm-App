package com.foodiedev.setrepetitiveexactalarm

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.foodiedev.setrepetitiveexactalarm.service.AlarmService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var alarmService: AlarmService
    lateinit var calendar: Calendar
    var t = 5
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alarmService = AlarmService(this)

        calendar = Calendar.getInstance()

        setExact.setOnClickListener {
            setAlarm { alarmService.setExactAlarm(it) }
            t += 5
        }

    }

    private fun setAlarm(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, Calendar.SECOND)
            this.set(Calendar.MILLISECOND, 0)
            this.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE))
            this.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY))
            callback(this.timeInMillis + t*1000*60)
        }

    }
}