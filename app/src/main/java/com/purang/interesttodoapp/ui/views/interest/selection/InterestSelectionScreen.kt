package com.purang.interesttodoapp.ui.views.interest.selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.purang.interesttodoapp.ui.theme.BackgroundLight
import com.purang.interesttodoapp.ui.theme.BlueP1
import com.purang.interesttodoapp.ui.theme.BlueP5

@Composable
fun InterestSelectionScreen(
    navController: NavController
) {
    val selection = arrayListOf<String>()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = "어떤 주제에 관심 있으신가요?",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 10.dp)
        )

        InterestSelectionRow(
            onClickChip = { v, selected ->
                if (selected) {
                    selection.add(v)
                } else {
                    selection.remove(v)
                }
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InterestSelectionRow(
    onClickChip: (String, Boolean) -> Unit
) {
    val interest = listOf("커피", "영화", "공연", "전시", "헬스", "농구", "미식", "반려동물","문화생활","여행", "+ 추가하기")

    FlowRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        maxItemsInEachRow = 3
    ) {
        interest.forEach {
            InterestChip(
                v = it,
                onClickChip = { v, selected ->
                    onClickChip(v, selected)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InterestChip(
    v : String,
    onClickChip : (String, Boolean) -> Unit
) {
    var selected by remember { mutableStateOf(false) }

    FilterChip(
        modifier = Modifier.padding(5.dp),
        selected = selected,
        onClick = {
            onClickChip(v, selected)
            selected = !selected
        },
        label = {
            Text(
                text = v,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = BackgroundLight, // 기본 배경색 (연한 회색)
            selectedContainerColor = BlueP5, // 선택 시 배경색 (메인 블루)
            labelColor = Color.Black, // 기본 텍스트 색상
            selectedLabelColor = Color.White // 선택 시 텍스트 색상
        )
    )
}