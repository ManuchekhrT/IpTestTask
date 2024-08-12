package ru.iptesttask.ui.screens.items

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.iptesttask.data.local.entity.Item
import ru.iptesttask.R

@Composable
fun ItemsContent(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    items: List<Item>,
    onDeleteItem: (Item) -> Unit,
    onUpdateItem: (Item, Int) -> Unit
) {
    Column(modifier = Modifier.padding(12.dp)) {
        SearchBar(searchQuery = searchQuery, onSearchQueryChange = onSearchQueryChange)
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn {
            items(items) { item ->
                ItemCard(
                    item = item,
                    onDeleteClick = onDeleteItem,
                    onUpdateClick = { updatedAmount -> onUpdateItem(item, updatedAmount) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchQuery: String, onSearchQueryChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        label = {
            Text(
                text = stringResource(id = R.string.search_item),
                style = TextStyle(color = Color.Black.copy(alpha = 0.7f), fontSize = 16.sp)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.search_icon_description),
                tint = Color.Black.copy(alpha = 0.7f)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 48.dp),
        shape = RoundedCornerShape(4.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Black.copy(alpha = 0.7f),
            containerColor = Color.White
        ),
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = { onSearchQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_icon_description),
                        tint = Color.Black.copy(alpha = 0.7f)
                    )
                }
            }
        },
        singleLine = true
    )
}
