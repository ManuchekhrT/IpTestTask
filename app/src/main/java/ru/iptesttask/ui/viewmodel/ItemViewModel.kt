package ru.iptesttask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.iptesttask.data.local.db.ItemDao
import ru.iptesttask.data.local.entity.Item
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val itemDao: ItemDao) : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            itemDao.getAllItems().collect { itemList ->
                _items.value = itemList
            }
        }
    }

    fun searchItems(query: String) {
        viewModelScope.launch {
            itemDao.searchItemsByName(query).collect { itemList ->
                _items.value = itemList
            }
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.deleteItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.updateItem(item)
        }
    }
}
