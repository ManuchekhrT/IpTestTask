package ru.iptesttask.ui.screens.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.AddCircleOutline
import androidx.compose.material.icons.outlined.RemoveCircleOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.iptesttask.R
import ru.iptesttask.data.local.entity.Item
import ru.iptesttask.ui.dialog.ShowAlertDialog
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ItemCard(
    item: Item,
    onDeleteClick: (Item) -> Unit,
    onUpdateClick: (Int) -> Unit
) {
    var updatedAmount by remember { mutableStateOf(item.amount) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showUpdateDialog by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.W600, fontSize = 18.sp
                    )
                )

                ItemCardActions(
                    onUpdateClick = { showUpdateDialog = true },
                    onDeleteClick = { showDeleteDialog = true }
                )
            }

            if (showDeleteDialog) {
                ShowAlertDialog(
                    onDismissRequest = { showDeleteDialog = false },
                    onConfirmation = {
                        onDeleteClick(item)
                        showDeleteDialog = false
                    },
                    dialogTitle = stringResource(id = R.string.delete_item_dialog_title),
                    dialogText = stringResource(id = R.string.delete_item_dialog_text),
                    icon = Icons.Default.Warning
                )
            }

            if (showUpdateDialog) {
                ShowUpdateAmountDialog(
                    updatedAmount = updatedAmount,
                    onAmountChange = { updatedAmount = it },
                    onDismissRequest = {
                        showUpdateDialog = false
                        updatedAmount = item.amount
                    },
                    onConfirm = {
                        onUpdateClick(updatedAmount)
                        showUpdateDialog = false
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy((-10).dp)
            ) {
                item.tags.forEach {
                    AssistChip(onClick = { /*TODO*/ }, label = {
                        Text(
                            text = it
                        )
                    })
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            ItemDetails(
                amount = item.amount,
                dateAdded = SimpleDateFormat(
                    "dd.MM.yyyy",
                    Locale.getDefault()
                ).format(Date(item.time))
            )
        }
    }
}

@Composable
fun ItemCardActions(
    onUpdateClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        IconButton(
            onClick = onUpdateClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(id = R.string.edit_icon_description),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(id = R.string.delete_icon_description),
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun ShowUpdateAmountDialog(
    updatedAmount: Int,
    onAmountChange: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                Icons.Default.Settings,
                contentDescription = stringResource(id = R.string.settings_icon_description)
            )
        },
        title = { Text(text = stringResource(id = R.string.amount_label)) },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (updatedAmount > 0) onAmountChange(updatedAmount - 1)
                    },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.RemoveCircleOutline,
                        contentDescription = stringResource(id = R.string.minus_icon_description),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Text(
                    text = updatedAmount.toString(),
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.W500)
                )

                IconButton(
                    onClick = { onAmountChange(updatedAmount + 1) }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AddCircleOutline,
                        contentDescription = stringResource(id = R.string.plus_icon_description),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(text = stringResource(id = R.string.accept))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    )
}

@Composable
fun ItemDetails(amount: Int, dateAdded: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = stringResource(id = R.string.stock_label),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
            )
            Text(
                text = amount.toString(),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W400)
            )
        }

        Column {
            Text(
                text = stringResource(id = R.string.date_added_label),
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
            )
            Text(
                text = dateAdded,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W400)
            )
        }
    }
}
