package ir.parsyiani.sample_hilt_viewmodel.ui.theme.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import ir.parsyiani.sample_hilt_viewmodel.viewmodel.testVIewmodel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun sample(){
    val model = hiltViewModel<testVIewmodel>()
    val data = model.data.observeAsState()
    val et = model.st.observeAsState()
    var show  by remember {
        mutableStateOf(false)
    }
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            show = true
        }) {
            /* FAB content */
        }
    }) {
        showList(mess = data.value?.toList() ?: listOf())
    }
    if(show == true)
    CustomDialog(setShowDialog = {show = it}, setValue = {model.add(it)})

}
@Composable
fun showList(mess:List<String>){
    LazyColumn(){
        items(mess){ mes->
            Text(text = mes)
        }
    }
}

@Composable
fun CustomDialog(setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {
    var txt by remember { mutableStateOf("") }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(text = "Text")
                    TextField(value = txt, onValueChange ={txt = it} )
                    Row( horizontalArrangement = Arrangement.Center) {
                        androidx.compose.material.Button(onClick = {setValue(txt)
                        setShowDialog(false)}) {
                            Text(text = "ADD")
                        }
                        androidx.compose.material.Button(onClick = {setShowDialog(false)}) {
                            Text(text = "Close")
                        }
                    }
                }
            }
        }
    }
}