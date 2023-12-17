package com.ecotup.ecotupapplication.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.util.SpacerCustom

@Composable
fun SectionStatusDriver(modifier: Modifier = Modifier) {
    var checked by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row()
        {
            SpacerCustom(space = 5)
            Text(
                text = "Status",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = GreenLight,
                    fontWeight = FontWeight.Bold,
                )
            )

        }


        Text(
            text = if (checked) "Online" else "Offline",
            style = MaterialTheme.typography.bodyMedium.copy(
                color = if (checked) GreenLight else Color.Gray,
                fontWeight = FontWeight.Bold,
            )
        )

        Row()
        {
            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it
                },
                thumbContent = if (checked) {
                    {
                        Icon(
                            imageVector = if (checked) Icons.Default.Check else Icons.Default.Close,
                            contentDescription = null,
                            modifier = Modifier.size(SwitchDefaults.IconSize),
                        )
                    }
                } else {
                    null
                },
            )
            SpacerCustom(space = 5)
        }

    }
}