package com.rudearts.weatherapp.api

import android.content.Context
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.rudearts.weatherapp.model.WeatherCodes
import com.rudearts.weatherapp.model.external.WeatherResponse
import com.rudearts.weatherapp.model.local.Weather
import com.rudearts.weatherapp.util.ExternalMapper
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class ExternalMapperTest {

    @Rule
    @JvmField
    val mockitoRule = MockitoJUnit.rule()

    @Mock lateinit var weatherCodes: WeatherCodes

    @InjectMocks @Spy lateinit var mapper: ExternalMapper

    @Test
    fun weather2local() {
        val external = mock<WeatherResponse> {
            on { cod } doReturn 2
            on { id } doReturn 1L
        }

        val weather = mapper.weatherResponse2local(external)

        assertEquals(2, weather.cod)
    }

    @Test
    fun weather2db() {
        val weather = mock<Weather> {
            on { id } doReturn 4L
            on { cod } doReturn 3
        }

        val db = mapper.weather2db(weather)

        assertEquals(4L, db.id)
    }
}