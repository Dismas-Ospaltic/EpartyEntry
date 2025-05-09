package com.st11.epartyentry.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.st11.epartyentry.model.EventEntity
import com.st11.epartyentry.repository.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EventsViewModel(private val eventRepository: EventRepository) : ViewModel() {

    // Holds the list of events
    private val _events = MutableStateFlow<List<EventEntity>>(emptyList())
    val events: StateFlow<List<EventEntity>> = _events

    // Holds a single event item
    private val _eventDetail = MutableStateFlow<EventEntity?>(null)
    val eventDetail: StateFlow<EventEntity?> = _eventDetail




    init {
        getAllEvents()
    }

    private fun getAllEvents() {
        viewModelScope.launch {
            eventRepository.getAllEvents().collectLatest { eventList ->
                _events.value = eventList
            }
        }
    }

    fun insertEvent(event: EventEntity) {
        viewModelScope.launch {
            eventRepository.insertEvent(event)
        }
    }

    fun updateEvent(event: EventEntity) {
        viewModelScope.launch {
            eventRepository.updateEvent(event)
        }
    }

    fun getEventById(eventId: String) {
        viewModelScope.launch {
            _eventDetail.value = eventRepository.getEventById(eventId)
        }
    }


    private val _totalUpcoming = MutableStateFlow<Int?>(0)
    val totalUpcoming: StateFlow<Int?> = _totalUpcoming

    fun getAllTotalUpcoming() {
        viewModelScope.launch {
            eventRepository.getAllTotalUpcoming().collectLatest { total ->
                _totalUpcoming.value = total
            }
        }
    }


    private val _totalEnded = MutableStateFlow<Int?>(0)
    val totalEnded: StateFlow<Int?> = _totalEnded

    fun getAllTotalEnded() {
        viewModelScope.launch {
            eventRepository.getAllTotalEnded().collectLatest { total ->
                _totalEnded.value = total
            }


        }


    }

}