package com.ecotup.ecotupapplication.ui.component

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.ecotup.ecotupapplication.data.cammon.Result
import com.ecotup.ecotupapplication.data.response.DataItemArticle
import com.ecotup.ecotupapplication.ui.theme.GreenLight
import com.ecotup.ecotupapplication.ui.user.home.HomeUserViewModel
import com.ecotup.ecotupapplication.util.ArticleStatic
import com.ecotup.ecotupapplication.util.SpacerCustom
import com.ecotup.ecotupapplication.util.convertTimestampToDate
import com.ecotup.ecotupapplication.util.dummyArticle

@Composable
fun SectionArticleDashboardUser(
    viewModel: HomeUserViewModel,
    modifier: Modifier = Modifier,
    lifecycleOwner: LifecycleOwner
) {
    var articleList by remember { mutableStateOf<List<DataItemArticle>>(emptyList()) }

    Text(
        text = "Recomendation Article",
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = GreenLight, fontSize = 15.sp, fontWeight = FontWeight.Bold
        )
    )

    SpacerCustom(space = 8)

    LaunchedEffect(viewModel) {
        viewModel.getArticle().observe(lifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    articleList = result.data.data
                }
                else -> {
                    // Handle error or other cases if needed
                }
            }
        }
    }

    ArticleList(listArticle = articleList)
}

@Composable
fun ArticleList(listArticle: List<DataItemArticle>, modifier: Modifier = Modifier) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(start = 3.dp, end = 8.dp),
        modifier = modifier
    ) {
        items(listArticle, key = { it.articleId }) { article ->
            ArticleItem(itemMenu = article)
        }
    }
}


@Composable
fun ArticleItem(itemMenu: DataItemArticle, modifier: Modifier = Modifier) {
    val browserLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = { })

    Card(
        modifier = modifier
            .width(200.dp)
            .shadow(2.dp, RoundedCornerShape(10.dp))
            .background(Color.White, shape = RoundedCornerShape(10.dp))
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = itemMenu.articleImage),
                contentDescription = "article_image",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        SpacerCustom(space = 5)

        Column(modifier = modifier.padding(8.dp)) {
            Text(
                text = itemMenu.articleName.toString(), style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold, color = GreenLight, textAlign = TextAlign.Center
                )
            )

            Log.d("NAME ARTICLE", itemMenu.articleName.toString())

            SpacerCustom(space = 8)

            Row(
                modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = itemMenu.articleAuthor.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 12.sp

                    )
                )

                val articleDate = convertTimestampToDate(itemMenu.articleDate.toString())
                Text(
                    text = articleDate, style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 12.sp
                    )
                )
            }

            SpacerCustom(space = 8)

            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(itemMenu.articleLink))
                browserLauncher.launch(intent)
            }, modifier = modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Read More", style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold, color = Color.White
                    )
                )
            }
        }
    }
}