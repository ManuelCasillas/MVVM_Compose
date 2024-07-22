package com.formation.mvvm_compose.login


import android.widget.Toast
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.formation.mvvm_compose.R
import com.formation.mvvm_compose.commons.BasicScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginRoot(vm: LoginViewModel = koinViewModel(), onLogin: () -> Unit) {
    BasicScreen(content = {
        Login(vm.state, onLogin, vm::onLoginClick, vm::userValueChanged, vm::passValueChanged)
    })
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun Login(
    state: LoginViewModel.UiState,
    onLogin: () -> Unit,
    onLoginClick: (user: String, pass: String) -> Unit,
    userValueChanged: () -> Unit,
    passValueChanged: () -> Unit
) {

    LaunchedEffect(state.loggedIn) {
        if (state.loggedIn) onLogin()
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        val context = LocalContext.current

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        ) {
            Text(text = "Elige el idioma")
        }

        Image(
            painter = painterResource(id = R.drawable.ic_marvel),
            contentDescription = null,
            modifier = Modifier
                .size(140.dp)
                .weight(1f),
        )

        Box(
            contentAlignment = Alignment.Center, modifier = Modifier
                .imePadding()
                .weight(3.5f)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(IntrinsicSize.Min)
            ) {
                val focusManager = LocalFocusManager.current


                var user by rememberSaveable { mutableStateOf(value = "") }
                var pass by rememberSaveable { mutableStateOf(value = "") }
                val buttonEnabled = pass.isNotEmpty() && user.isNotEmpty()

                StateUserTextField(
                    value = user,
                    onValueChange = {
                        user = it
                        userValueChanged()
                    },
                    isError = state.isUserError
                )

                Crossfade(targetState = state.isUserError) { isUserError ->
                    if (isUserError) {
                        Text(text = "User must contain @")
                    }
                }

                StatePasswordTextField(
                    value = pass,
                    onValueChange = {
                        pass = it
                        passValueChanged()
                    },
                    isError = state.isPassError
                )

                Crossfade(targetState = state.isPassError) { isUserError ->
                    if (isUserError) {
                        Text(text = "Password must contains 5 letters")
                    }
                }

                Button(
                    enabled = buttonEnabled,
                    onClick = {
                        focusManager.clearFocus()
                        onLoginClick(user, pass)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),

                    ) {
                    Text(text = "Login")
                }

                ClickablePartOfText(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 12.sp, color = Color.Gray)) {
                            append("Has olvidado tus datos de inicio de sesión?")
                        }

                        withAnnotation("tag", "annotation") {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Gray,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Obtén ayuda")
                            }
                        }
                    }) {
                    Toast.makeText(context, "Estamos trabajando en ello", Toast.LENGTH_SHORT).show()
                }
            }
        }

        Text("Login Social", modifier = Modifier.weight(1f))


        Box(
            modifier = Modifier.weight(1f)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp) // Specify the height of the thin line
                    .background(Color.LightGray)// Set the color of the line
            )

            Text(
                text = " ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            )

            ClickablePartOfText(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 16.sp, color = Color.Gray)) {
                        append("¿No tienes cuenta? ")
                    }

                    withAnnotation("tag", "annotation") {
                        withStyle(
                            style = SpanStyle(
                                color = Color.Gray,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
                            append("Regístrate")
                        }
                    }
                }, center = true) {
                Toast.makeText(context, "Estamos trabajando en ello", Toast.LENGTH_SHORT).show()
            }

        }


    }

}

@Composable
private fun StateUserTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
) {
    TextField(
        value = value,
        label = { Text(text = "Usuario") },
        placeholder = { Text(text = "Manuel@gmail.com") },
        onValueChange = onValueChange,
        isError = isError,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = R.color.lightGray),
            focusedContainerColor = colorResource(id = R.color.lightGray),
            errorContainerColor = colorResource(id = R.color.lightGray),
            focusedTextColor = Color.Black,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            focusedIndicatorColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            cursorColor = Color.Gray,
        ),
        textStyle = TextStyle(
            color = if (isError) Color.Red else Color.Black
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        )
    )
}

@Composable
private fun StatePasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(
        value = value,
        label = { Text(text = "Contraseña") },
        placeholder = { Text(text = "12345") },
        onValueChange = onValueChange,
        isError = isError,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = colorResource(id = R.color.lightGray),
            focusedContainerColor = colorResource(id = R.color.lightGray),
            errorContainerColor = colorResource(id = R.color.lightGray),
            focusedTextColor = Color.Black,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            focusedIndicatorColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            cursorColor = Color.Gray,
        ),
        textStyle = TextStyle(
            color = if (isError) Color.Red else Color.Black
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconToggleButton(
                checked = passwordVisible,
                onCheckedChange = { passwordVisible = it },
            ) {
                Crossfade(targetState = passwordVisible) { visible ->
                    if (visible) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            tint = if (isError) Color.Red else Color.Black,
                            contentDescription = null
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.VisibilityOff,
                            tint = if (isError) Color.Red else Color.Black,
                            contentDescription = null
                        )
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
    )
}

@Composable
fun ClickablePartOfText(annotatedString: AnnotatedString,center: Boolean = false,onTextClick: () -> Unit, ) {

    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }

    var textAlign = TextAlign.Start
    if (center) {
        textAlign = TextAlign.Center
    }

    Text(
        text = annotatedString,
        lineHeight = 18.sp,
        onTextLayout = {
            textLayoutResult = it
        },
        textAlign = textAlign,
        modifier = Modifier
            .conditional(center){
                Modifier.fillMaxWidth().padding(16.dp)

            }
            .pointerInput(Unit) {

                detectTapGestures { offset ->
                    textLayoutResult?.let { layoutResult ->
                        val position = layoutResult.getOffsetForPosition(offset)
                        annotatedString
                            .getStringAnnotations(
                                start = position,
                                end = position,
                            )
                            .firstOrNull { annotation ->
                                annotation.tag.startsWith("tag")
                            }
                            ?.let { annotation ->
                                val userId = annotation.item  // could be use when a annotated string have more than one tag. Could it be differentiate with a when
                                onTextClick()
                            }
                    }
                }
            }
    )
}

fun Modifier.conditional(condition : Boolean, modifier : Modifier.() -> Modifier) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}