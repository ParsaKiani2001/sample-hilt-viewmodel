package ir.parsyiani.sample_hilt_viewmodel

sealed class State{
    object error:State()
    data class sucsses(val data:String):State()
}
