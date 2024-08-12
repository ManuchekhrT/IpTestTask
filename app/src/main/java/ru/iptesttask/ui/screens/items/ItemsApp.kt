package ru.iptesttask.ui.screens.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import ru.iptesttask.ui.viewmodel.ItemViewModel

@Composable
fun ItemsApp(itemViewModel: ItemViewModel = hiltViewModel()) {
    var searchQuery by remember { mutableStateOf("") }
    val items by itemViewModel.items.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFf5f4f5))
    ) {
        Column {
            ItemsAppBar()
            ItemsContent(
                searchQuery = searchQuery,
                onSearchQueryChange = {
                    searchQuery = it
                    itemViewModel.searchItems(searchQuery)
                },
                items = items.filter { it.name.contains(searchQuery, ignoreCase = true) },
                onDeleteItem = { itemViewModel.deleteItem(it) },
                onUpdateItem = { item, updatedAmount ->
                    itemViewModel.updateItem(item.copy(amount = updatedAmount))
                }
            )
        }
    }
}
