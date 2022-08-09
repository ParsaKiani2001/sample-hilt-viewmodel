package ir.parsyiani.sample_hilt_viewmodel.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.parsyiani.sample_hilt_viewmodel.State
import javax.inject.Inject

@HiltViewModel
class testVIewmodel @Inject constructor():ViewModel(){
    val data = MutableLiveData<MutableList<String>>(mutableListOf())
    private val state = MutableLiveData<State>(null)
    val st : LiveData<State> get()=state
    fun add(str:String) {
        data.value!!.add(str)
    }
    fun delete(){
        data.postValue(mutableListOf())
    }
}