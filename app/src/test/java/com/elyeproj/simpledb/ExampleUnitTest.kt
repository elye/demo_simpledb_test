package com.elyeproj.simpledb

import android.os.Build.VERSION_CODES.LOLLIPOP
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class, sdk = intArrayOf(LOLLIPOP), packageName = "com.elyeproj.simpledb")
class ExampleUnitTest {

    lateinit var dbHelper: DbHelper

    @Before
    fun setup() {
        dbHelper = DbHelper(RuntimeEnvironment.application)
        dbHelper.clearDbAndRecreate()
    }

    @Test
    @Throws(Exception::class)
    fun testDbInsertion() {

        // Given
        val testStr1 = "testing"
        val testStr2 = "testing"

        // When
        dbHelper.insertText(testStr1)
        dbHelper.insertText(testStr2)

        // Then
        assertEquals(dbHelper.getAllText(), "$testStr1-$testStr2-")
    }

    @After
    fun tearDown() {
        dbHelper.clearDb()
    }
}