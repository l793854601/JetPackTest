package com.tkm.workmanager

import android.content.Context
import android.os.SystemClock
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

class MyWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    companion object {
        private const val TAG = "MyWorker"
    }
    override fun doWork(): Result {
        val name = inputData.getString(INPUT_DATA_KEY)
        Log.d(TAG, "doWork: $name started")
        SystemClock.sleep(3000)
        Log.d(TAG, "doWork: $name finished")
//        if (name == WORK_B_NAME) {
//            return Result.failure()
//        }
        return Result.success(workDataOf(OUTPUT_DATA_KEY to "output data"))
    }
}