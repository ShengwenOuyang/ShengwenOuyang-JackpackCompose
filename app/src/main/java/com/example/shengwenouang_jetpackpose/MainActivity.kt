package com.example.shengwenouang_jetpackpose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.shengwenouang_jetpackpose.ui.theme.ShengwenOuangJetpackPoseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowWidthClass = currentWindowAdaptiveInfo().windowSizeClass.windowWidthSizeClass
            MyApp(windowWidthClass)
        }
    }
}

@Composable
fun MyApp(windowWidthClass: WindowWidthSizeClass) {
    ShengwenOuangJetpackPoseTheme {
        when (windowWidthClass) {
            WindowWidthSizeClass.COMPACT, WindowWidthSizeClass.MEDIUM -> PortraitScreen()
            WindowWidthSizeClass.EXPANDED -> LandscapeScreen()
            else -> LandscapeScreen()
        }
    }
}
@Composable
fun PortraitScreen() {
    val selectedItem = remember { mutableStateOf<String?>(null) }

    if (selectedItem.value == null) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text("Select an Item")
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(listOf("Item 1", "Item 2", "Item 3")) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedItem.value = item }
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    } else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Details for ${selectedItem.value}")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { selectedItem.value = null }) {
                    Text("Back to List")
                }
            }
        }
    }
}

@Composable
fun LandscapeScreen() {
    val selectedItem = remember { mutableStateOf<String?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            item {
                Text("Select an Item")
                Spacer(modifier = Modifier.height(8.dp))
            }
            items(listOf("Item 1", "Item 2", "Item 3")) { item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedItem.value = item }
                        .padding(8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Box(
            modifier = Modifier
                .weight(2f)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            if (selectedItem.value != null) {
                Text("Details for ${selectedItem.value}")
            } else {
                Text("Select an item to see details")
            }
        }
    }
}









