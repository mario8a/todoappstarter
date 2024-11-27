package com.mario8a.todoappstarter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario8a.todoappstarter.ui.theme.TodoappstarterTheme


@Composable
fun HelloWorld(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .background(
                color = Color.Red
            )
            .padding(16.dp),
        text = "Hello world",
        fontWeight = FontWeight.Bold,
        color = Color.Blue
    )
}


@Preview(
    showBackground = true
)
@Composable
fun HellowWorldPreview() {
    TodoappstarterTheme {
        HelloWorld()
    }
}

@Composable
fun ClickabletextExample(
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf("ClickMe") }
    Text(
        modifier = modifier.clickable {
            text = "Other text"
        },
        text = text,
        fontWeight = FontWeight.Bold,
        color = Color.Blue
    )
}

@Preview(
    showBackground = true
)
@Composable
fun ClickableTextPrev() {
    TodoappstarterTheme {
        ClickabletextExample()
    }
}

@Composable
fun IconExample(
    modifier: Modifier = Modifier
) {
    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "Favorite icon",
        modifier = modifier
            .size(48.dp)
            .border(width = 2.dp, Color.Gray, shape = CircleShape)
            .padding(8.dp)
    )
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewIconExample() {
    TodoappstarterTheme {
        IconExample()
    }
}

@Composable
fun RowView() {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        HelloWorld()
        IconExample()
    }
}

@Preview(
    showBackground = true
)
@Composable
fun RowViewExample(){
    TodoappstarterTheme {
        RowView()
    }
}

@Composable
fun ColumnView(){
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HelloWorld()
        IconExample()
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ColViewExample(){
    TodoappstarterTheme {
        ColumnView()
    }
}