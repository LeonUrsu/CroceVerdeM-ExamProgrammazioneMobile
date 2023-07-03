package com.example.croceverdeplus

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class WeekDatesTest {

    private lateinit var calendar: Calendar

    @Before
    fun setUp() {
        calendar = Calendar.getInstance()
        // Data di riferimento per il test
        calendar.time = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2023-06-29")
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetWeekDates() {
        val weekDates = getWeekDates(calendar.time)

        //Lunedì
        val expectedWeekStart =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2023-06-26")
        //Domenica
        val expectedWeekEnd =
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2023-07-02")

        assertEquals(expectedWeekStart, weekDates.first)
        assertEquals(expectedWeekEnd, weekDates.second)
    }

}