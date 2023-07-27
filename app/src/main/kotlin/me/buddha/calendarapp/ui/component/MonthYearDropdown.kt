package me.buddha.calendarapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.buddha.calendarapp.domain.Month

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MonthYearDropdown(
  visibleMonth: Int,
  visibleYear: Int,
  setVisibleMonth: (Int) -> Unit,
  setVisibleYear: (Int) -> Unit,
  getSelectedMonth: () -> Unit,
) {
  var isMonthMenuExpanded by remember { mutableStateOf(false) }
  var isYearMenuExpanded by remember { mutableStateOf(false) }
  Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceEvenly,
  ) {
    ExposedDropdownMenuBox(
      expanded = isMonthMenuExpanded,
      onExpandedChange = { isMonthMenuExpanded = !isMonthMenuExpanded },
      modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
    ) {
      TextField(
        value = Month.values()[visibleMonth].value,
        onValueChange = {},
        readOnly = true,
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isMonthMenuExpanded) },
        modifier = Modifier.menuAnchor(),
        colors = TextFieldDefaults.textFieldColors(
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent,
        )
      )

      ExposedDropdownMenu(
        expanded = isMonthMenuExpanded,
        onDismissRequest = { isMonthMenuExpanded = false }
      ) {
        Month.values().forEachIndexed { index, item ->
          DropdownMenuItem(
            text = { Text(text = item.value) },
            onClick = {
              setVisibleMonth(index)
              isMonthMenuExpanded = false
              getSelectedMonth()
            }
          )
        }
      }
    }

    ExposedDropdownMenuBox(
      expanded = isYearMenuExpanded,
      onExpandedChange = { isYearMenuExpanded = !isYearMenuExpanded },
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
    ) {
      TextField(
        value = visibleYear.toString(),
        onValueChange = {},
        readOnly = true,
        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isYearMenuExpanded) },
        modifier = Modifier.menuAnchor(),
        colors = TextFieldDefaults.textFieldColors(
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent,
        ),
      )

      ExposedDropdownMenu(
        expanded = isYearMenuExpanded,
        onDismissRequest = { isYearMenuExpanded = false }
      ) {
        (1900..2100).forEach { item ->
          DropdownMenuItem(
            text = { Text(text = item.toString()) },
            onClick = {
              setVisibleYear(item)
              isYearMenuExpanded = false
              getSelectedMonth()
            }
          )
        }
      }
    }
  }
}