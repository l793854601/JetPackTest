package com.tkm.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.work.*

const val INPUT_DATA_KEY = "INPUT_DATA_KEY"
const val OUTPUT_DATA_KEY = "OUTPUT_DATA_KEY"
const val WORK_A_NAME = "WORK_A"
const val WORK_B_NAME = "WORK_B"

class MainActivity : AppCompatActivity() {
    private val workManager by lazy { WorkManager.getInstance(this) }

    private lateinit var tvResult: TextView
    private lateinit var btnAddQueue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvResult = findViewById(R.id.tv_result)
        btnAddQueue = findViewById(R.id.btn_add_queue)
        btnAddQueue.setOnClickListener {
            val workRequestA = createWorkRequest(WORK_A_NAME)
            val workRequestB = createWorkRequest(WORK_B_NAME)

            workManager.beginWith(workRequestA)
                .then(workRequestB)
                .enqueue()

            workManager.getWorkInfoByIdLiveData(workRequestA.id).observe(this) { workInfo ->
                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    //  成功
                    val result = workInfo.outputData.getString(OUTPUT_DATA_KEY)
                    tvResult.text = result
                }
            }
        }
    }

    private fun createWorkRequest(name: String): OneTimeWorkRequest {
        //  创建约束条件：电量、网络、存储等
        val constraints = Constraints.Builder()
    //                .setRequiredNetworkType(NetworkType.CONNECTED)
    //                .setRequiresBatteryNotLow(true)
    //                .setRequiresCharging(true)
    //                .setRequiresStorageNotLow(true)
            .build()
        //  创建WorkRequest
        val workRequest = OneTimeWorkRequestBuilder<MyWorker>()
            .setInputData(workDataOf(INPUT_DATA_KEY to name))
            .setConstraints(constraints)
            .build()
        return workRequest
    }
}