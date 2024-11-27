package com.mario8a.todoappstarter.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario8a.todoappstarter.ui.theme.TodoappstarterTheme

@Composable
fun SummaryInfo(
    modifier: Modifier = Modifier,
    date: String = "March 9 , 2024",
    taskSummary: String = "5, incomplete, 5 complered"
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = date,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = taskSummary,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewSummaryInfo() {
    TodoappstarterTheme {
        SummaryInfo()
    }
}