package com.example.museumapp.util

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import kotlin.math.sqrt

class ShakeDetector : SensorEventListener {
    private var mLastTime: Long = 0
    private var mLastX: Float = 0.0f
    private var mLastY: Float = 0.0f
    private var mLastZ: Float = 0.0f
    private var mShakeThreshold = 1500

    private var onShake: (() -> Unit)? = null

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    fun setOnShakeListener(callback: () -> Unit) {
        onShake = callback
    }

    override fun onSensorChanged(event: SensorEvent?) {
        val currentTime = System.currentTimeMillis()

        if ((currentTime - mLastTime) > 100) {
            val diffTime = currentTime - mLastTime
            mLastTime = currentTime

            val x = event!!.values[0]
            val y = event.values[1]
            val z = event.values[2]

            val speed = sqrt((x - mLastX) * (x - mLastX) + (y - mLastY) * (y - mLastY) + (z - mLastZ) * (z - mLastZ)) / diffTime * 10000

            if (speed > mShakeThreshold) {
                onShake?.invoke()
            }

            mLastX = x
            mLastY = y
            mLastZ = z
        }
    }
}
