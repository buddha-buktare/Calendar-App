package me.buddha.calendarapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.buddha.calendarapp.R
import me.buddha.calendarapp.domain.Month

@Composable
internal fun MonthTile(
  visibleMonth: Int,
  visibleYear: Int,
  incrementMonth: () -> Unit,
  decrementMonth: () -> Unit,
) {

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(horizontal = 20.dp, vertical = 10.dp)
  ) {
    Icon(
      painter = painterResource(id = R.drawable.ic_arrow_back),
      contentDescription = null,
      modifier = Modifier
        .size(24.dp)
        .align(Alignment.CenterStart)
        .clickable { decrementMonth() },
    )
    Text(
      text = "${Month.values()[visibleMonth].value}, $visibleYear",
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold,
      fontSize = 20.sp,
      modifier = Modifier.align(Alignment.Center),
    )
    Icon(
      painter = painterResource(id = R.drawable.ic_arrow_forward),
      contentDescription = null,
      modifier = Modifier
        .size(24.dp)
        .align(Alignment.CenterEnd)
        .clickable { incrementMonth() },
    )
  }
}