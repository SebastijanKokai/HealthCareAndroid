package com.example.healthcare.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.healthcare.data.Gender

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DropdownMenu(
    items: Array<T>,
    hintText: String,
    isExpanded: Boolean,
    value: String,
    onValueChange: () -> Unit,
    onExpandedChange: () -> Unit,
    onDismissRequest: () -> Unit,
    onItemClick: (label: T) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = isExpanded,
        onExpandedChange = {
            onExpandedChange()
        }) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = value,
            onValueChange = {
                onValueChange()
            },
            label = {
                Text(hintText)
            },
            trailingIcon = {
                Icon(
                    Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown icon",
                    tint = MaterialTheme.colorScheme.primary
                )
            },
        )
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                onDismissRequest()
            },
        ) {
            items.forEach { label ->
                if (label is Gender) {
                    DropdownMenuItem(
                        text = {
                            Text(text = label.value)
                        },
                        onClick = {
                            onItemClick(label)
                        })
                } else if (label is String) {
                    DropdownMenuItem(
                        text = {
                            Text(text = label)
                        },
                        onClick = {
                            onItemClick(label)
                        })
                }
            }
        }
    }
}

@Composable
@Preview
fun DropdownMenuPreview() {
    DropdownMenu(
        arrayOf("First item", "Second item"),
        "Please specify",
        false,
        "1",
        {}, {}, {}, {})
}