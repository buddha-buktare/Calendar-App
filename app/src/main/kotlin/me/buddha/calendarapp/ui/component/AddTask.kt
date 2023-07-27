package me.buddha.calendarapp.ui.component

import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.buddha.calendarapp.R.drawable
import me.buddha.calendarapp.domain.Month

@RequiresApi(VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
internal fun AddTask(
  selectedDate: Int,
  selectedMonth: Int,
  selectedYear: Int,
  storeTask: (String, String) -> Unit,
) {
  var isExpanded by remember { mutableStateOf(false) }
  var title by remember { mutableStateOf("") }
  var description by remember { mutableStateOf("") }
  val keyboardController = LocalSoftwareKeyboardController.current

  Box(
    modifier = Modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(16.dp))
      .background(Color.Gray)
      .padding(
        start = 8.dp,
        end = 8.dp,
        bottom = if (isExpanded) 8.dp else 0.dp,
      )
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
      ) {
        Column(
          modifier = Modifier.weight(1f)
        ) {
          Text(
            text = "$selectedDate ${Month.values()[selectedMonth].value}, $selectedYear",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
          )
        }
        Spacer(modifier = Modifier.width(8.dp))
        if (isExpanded) {
          Icon(
            painter = painterResource(id = drawable.ic_arrow_up),
            contentDescription = null,
            modifier = Modifier
              .size(40.dp)
              .clickable { isExpanded = false },
          )
        } else {
          Button(
            onClick = { isExpanded = true }
          ) {
            Text(text = "Add Task")
          }
        }
      }

      if (isExpanded) {
        TextField(
          value = title,
          onValueChange = { title = it },
          placeholder = { Text(text = "Title...") },
          modifier = Modifier.fillMaxWidth(),
          colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
          ),
          keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
          ),
          keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
          )
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
          value = description,
          onValueChange = { description = it },
          placeholder = { Text(text = "Description...") },
          modifier = Modifier.fillMaxWidth(),
          colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
          ),
          keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
          ),
          keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
          )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
          onClick = {
            isExpanded = false
            storeTask(title, description)
            title = ""
            description = ""
          },
        ) {
          Text(text = "Store Task")
        }
      }
    }
  }
}