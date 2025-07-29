package com.amrit.nutriguidefoodsbyconditionnutrients.appcomponents



import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun ExitConfirmationDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("No")
            }
        },
        title = { Text("Exit App") },
        text = { Text("Are you sure you really want to exit?") }
    )
}
