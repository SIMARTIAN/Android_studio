package com.example.myapplication4654

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _text = MutableLiveData("HELLO!")
    val text: LiveData<String> get() = _text

    fun changeText(){
        _text.value = if (_text.value == "HELLO!") "HELLO SASHA!" else "HELLO!"
    }
}