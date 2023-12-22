package com.qwict.studyplanetandroid.presentation.components

import android.os.Build
import android.view.HapticFeedbackConstants

fun invokeHapticFeedback(view: android.view.View?) {
    view?.let {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            view.performHapticFeedback(HapticFeedbackConstants.REJECT)
        } else {
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS)
        }
    }
}
