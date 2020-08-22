package ua.dp.hammer.video.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ua.dp.hammer.video.camera.SecuritySession

class VideoViewModel : ViewModel() {
    fun login() {
        viewModelScope.launch {
            SecuritySession().sendLoginCommand()
        }
    }
}