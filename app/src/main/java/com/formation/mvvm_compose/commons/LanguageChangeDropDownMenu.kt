package com.formation.mvvm_compose.commons

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat

@Composable
fun LanguageChangeDropDownMenu() {
    val itemList = listOf("Español (España)", "English")

    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    val buttonModifier = Modifier.width(200.dp)
    val context = LocalContext.current

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            DropdownList(
                itemList = itemList,
                selectedIndex = selectedIndex,
                modifier = buttonModifier,
                onItemClick = {
                    selectedIndex = it

                    val languageTag = when (selectedIndex) {
                        1 -> "en"
                        else -> "es"
                    }

                    ChangeLanguage(context, languageTag)
                })
        }
    }
}


private fun ChangeLanguage(context: Context, locale: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.getSystemService(LocaleManager::class.java)
            .applicationLocales = LocaleList.forLanguageTags(locale)
    } else {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(locale))
    }
}