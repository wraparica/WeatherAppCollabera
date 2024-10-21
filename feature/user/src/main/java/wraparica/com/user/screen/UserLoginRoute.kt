package wraparica.com.user.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import wraparica.com.designsystem.bottombar.BottomAppBarComponent
import wraparica.com.designsystem.weatherDetails.WeatherDetailComponent
import wraparica.com.user.LoginUiState
import wraparica.com.user.UserViewModel


@Composable
internal fun UserLoginRoute(
    navigateToDashboardRoute: () -> Unit,
    navigateToSignupRoute: () -> Unit,
    viewModel: UserViewModel = hiltViewModel()
) {
    val uiState by viewModel.loginState.collectAsStateWithLifecycle()
    if(uiState.proceedToDashboard){
        navigateToDashboardRoute()
    }
    UserLoginScreen(
        navigateToDashboardRoute = navigateToDashboardRoute,
        navigateToSignupRoute = navigateToSignupRoute,
        uiState = { uiState },
        onPasswordChange = { viewModel.updatePassword(it) },
        onUsernameChange = { viewModel.updateUsername(it) },
        onLoginClick = { viewModel.login() }
    )

}

@Composable
internal fun UserLoginScreen(
    modifier: Modifier = Modifier,
    uiState: () -> LoginUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    navigateToDashboardRoute: () -> Unit,
    navigateToSignupRoute: () -> Unit
) {
    var loginClicked by remember { mutableStateOf(false) }
    if (uiState().proceed) {
        navigateToDashboardRoute()
    } else if (!uiState().proceed && loginClicked) {
        loginClicked = false
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
                    modifier = modifier.fillMaxWidth().padding(horizontal = 12.dp),
                    onClick = {
                        onLoginClick()
                        loginClicked = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                    ),
                    shape = RoundedCornerShape(6.dp)
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Log in",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                    )
                }

                TextButton(
                    modifier = modifier.fillMaxWidth().padding(horizontal = 12.dp),
                    onClick = navigateToSignupRoute,
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