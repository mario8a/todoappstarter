package com.mario8a.todoappstarter

import android.app.Application
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class CustomTestRunner : AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader,
        className: String,
        context: android.content.Context
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}