package ua.dp.hammer.video.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.runBlocking
import ua.dp.hammer.video.databinding.FragmentVideoBinding
import ua.dp.hammer.video.models.VideoViewModel

class VideoFragment : Fragment() {
    private val viewModel: VideoViewModel by viewModels {
        object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return runBlocking {
                    VideoViewModel() as T
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View {
        val binding = FragmentVideoBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.loginButton.setOnLongClickListener() {
            viewModel.login()
            true
        }

        return binding.root
    }
}