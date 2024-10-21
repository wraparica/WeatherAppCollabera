package wraparica.com.designsystem.weatherDetails

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import wraparica.com.designsystem.R
import wraparica.com.designsystem.convertToTime
import wraparica.com.model.weatherModel.OpenWeatherResponse
import wraparica.com.model.weatherModel.PreviousWeatherModel
import wraparica.com.model.weatherModel.WeatherUiState

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherDetailComponent(
    modifier: Modifier = Modifier,
    uiState: PreviousWeatherModel,
) {
    val elevation = animateDpAsState(0.dp, label = "")
    Box(
        modifier = modifier
            .shadow(elevation.value)
            .background(color = Color.White.copy(alpha = 0.35f))
            .graphicsLayer { alpha = 0.5f }

    ) {
        Column(
            modifier = modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    modifier = modifier
                        .wrapContentHeight(),
                    text = uiState.cts,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 14.sp,
                        lineHeight = 14.sp
                    ),
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )


                Row() {
                    Row(
                        modifier = modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    ) {
                        Column {
                            Text(
                                modifier = modifier
                                    .wrapContentHeight(),
                                text = uiState.name,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontSize = 18.sp,
                                    lineHeight = 18.sp
                                ),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )

                            Text(
                                modifier = modifier
                                    .wrapContentHeight(),
                                text = uiState.country,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp
                                ),
                                fontWeight = FontWeight.Normal,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Row(
                        modifier = modifier.align(Alignment.CenterVertically)
                    ) {
                        Text(
                            modifier = modifier
                                .wrapContentHeight()
                                .align(Alignment.CenterVertically),
                            text = uiState.weatherMain,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontSize = 18.sp,
                                lineHeight = 18.sp
                            ),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        GlideImage(
                            model = "https://openweathermap.org/img/wn/${uiState.icon}@2x.png",
                            contentDescription = null,
                            modifier = modifier
                                .height(60.dp)
                                .width(60.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    modifier = modifier
                        .wrapContentHeight(),
                    text = uiState.temp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 36.sp,
                        lineHeight = 36.sp
                    ),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row() {
                    Image(
                        painter = painterResource(R.drawable.sunrise),
                        contentDescription = "",
                        modifier = modifier
                            .height(30.dp)
                            .width(30.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        modifier = modifier
                            .wrapContentHeight().align(Alignment.CenterVertically),
                        text = "Sunrise",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 14.sp,
                            lineHeight = 14.sp
                        ),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        modifier = modifier
                            .wrapContentHeight().align(Alignment.CenterVertically),
                        text = uiState.sunrise.convertToTime(),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 14.sp,
                            lineHeight = 14.sp
                        ),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )

                }
                Row() {
                    Image(
                        painter = painterResource(R.drawable.sunset),
                        contentDescription = "",
                        modifier = modifier
                            .height(30.dp)
                            .width(30.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        modifier = modifier
                            .wrapContentHeight()
                            .align(Alignment.CenterVertically),
                        text = "Sunset",
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 14.sp,
                            lineHeight = 14.sp
                        ),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        modifier = modifier
                            .wrapContentHeight().align(Alignment.CenterVertically),
                        text = uiState.sunset.convertToTime(),
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontSize = 14.sp,
                            lineHeight = 14.sp
                        ),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }
}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}