package com.example.android_6ringo

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.navigation.NavHostController
import org.kodein.di.DI

var LocalComposeContext = staticCompositionLocalOf<ComposeContext> {
    error("No LocalComposeContext")
}

data class ComposeContext @OptIn(ExperimentalComposeUiApi::class) constructor(
    var container: Container,
    var navController: NavHostController,
    var keyboard: SoftwareKeyboardController?,
    var snackbarInfoHostState: SnackbarHostState,
    var snackbarErrorHostState: SnackbarHostState
)