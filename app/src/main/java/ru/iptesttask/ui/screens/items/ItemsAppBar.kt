package ru.iptesttask.ui.screens.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.iptesttask.R
import ru.iptesttask.ui.theme.BlueLighter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsAppBar() {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.list_items),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.W400,
                        fontSize = 22.sp
                    ),
                    color = Color.Black.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = BlueLighter)
    )
}
