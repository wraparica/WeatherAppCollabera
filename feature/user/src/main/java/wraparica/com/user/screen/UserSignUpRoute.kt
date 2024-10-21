package wraparica.com.user.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import wraparica.com.user.LoginUiState
import wraparica.com.user.UserViewModel

@Composable
internal fun UserSignupRoute(
    navigateToLoginRoute: () -> Unit,
    viewModel: UserViewModel = hiltViewModel()
) {
    val uiState by viewModel.signupUiState.collectAsStateWithLifecycle()

    UserSignupScreen(
        navigateToLoginRoute = navigateToLoginRoute,
        uiState = { uiState },
        onNameChange = { viewModel.updateName(it) },
        onPasswordChange = { viewModel.updatePassword(it) },
        onUsernameChange = { viewModel.updateUsername(it) },
        onSignupClick = { viewModel.signup() }
    )

}

@Composable
internal fun UserSignupScreen(
    modifier: Modifier = Modifier,
    uiState: () -> LoginUiState,
    onNameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignupClick: () -> Unit,
    navigateToLoginRoute: () -> Unit,
) {
    var signupClicked by remember { mutableStateOf(false) }
    if (signupClicked) {
        if (uiState().proceed) {
            navigateToLoginRoute()
        } else {
            signupClicked = false
        }
    }
    Scaffold(modifier = modifier) {
        Surface {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color(0xFF60D5D3))
            ) {

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 6.dp),
                        value = uiState().name,
                        onValueChange = onNameChange,
                        shape = MaterialTheme.shapes.extraSmall,
                        label = {
                            Text(
                                modifier = Modifier.wrapContentHeight(),
                                text = "Name",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    lineHeight = 12.sp,
                                    fontWeight = FontWeight.W600
                                ),
                                color = Color(0xFF073B3A),
                                //fontFamily = FontFamily(Font(R.font.poppins))
                            )

                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(0xFF073B3A),
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                        ),
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 6.dp),
                        value = uiState().username,
                        onValueChange = onUsernameChange,
                        shape = MaterialTheme.shapes.extraSmall,
                        label = {
                            Text(
                                modifier = Modifier.wrapContentHeight(),
                                text = "Username",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    lineHeight = 12.sp,
                                    fontWeight = FontWeight.W600
                                ),
                                color = Color(0xFF073B3A),
                                //fontFamily = FontFamily(Font(R.font.poppins))
                            )

                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(0xFF073B3A),
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                        ),
                    )

                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 6.dp),
                        value = uiState().password,
                        onValueChange = onPasswordChange,
                        shape = MaterialTheme.shapes.extraSmall,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation =  PasswordVisualTransformation(),
                        label = {
                            Text(
                                modifier = Modifier.wrapContentHeight(),
                                text = "Password",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    lineHeight = 12.sp,
                                    fontWeight = FontWeight.W600
                                ),
                                color = Color(0xFF073B3A),
                                //fontFamily = FontFamily(Font(R.font.poppins))
                            )

                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(0xFF073B3A),
                            unfocusedContainerColor = Color.White,
                            focusedContainerColor = Color.White,
                        ),
                    )

                    TextButton(
                        modifier = modifier.fillMaxWidth().padding(12.dp),
                        onClick = {
                            onSignupClick()
                            navigateToLoginRoute()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue,
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Sign up",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White,
                            fontStyle = FontStyle.Italic,
                        )
                    }


            }
        }
    }
}