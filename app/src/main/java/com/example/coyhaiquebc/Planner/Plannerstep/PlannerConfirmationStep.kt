package com.example.coyhaiquebc.Planner.Plannerstep

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.data.model.TransportRouteDto
import com.example.coyhaiquebc.Planner.PlannerColors
import com.example.coyhaiquebc.Planner.components.PlannerPrimaryButton

@Composable
fun PlannerConfirmationStep(
    destination: String,
    route: TransportRouteDto?,
    date: String,
    people: String,
    onFinishClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    val readyText = stringResource(R.string.planner_confirmation_ready)
    val qrInstructionText = stringResource(R.string.planner_confirmation_qr_instruction)
    val originText = stringResource(R.string.planner_confirmation_origin)
    val departureText = stringResource(R.string.planner_confirmation_departure)
    val arrivalText = stringResource(R.string.planner_confirmation_arrival)
    val priceText = stringResource(R.string.planner_confirmation_price)
    val dateText = stringResource(R.string.planner_confirmation_date)
    val peopleText = stringResource(R.string.planner_confirmation_people)
    val finishText = stringResource(R.string.planner_confirmation_finish)
    val defaultOrigin = stringResource(R.string.planner_confirmation_default_origin)

    val destinationLabel = stringResource(R.string.planner_confirmation_destination)

    val origen = route?.getOrigen(currentLanguage) ?: defaultOrigin
    val destino = route?.getDestino(currentLanguage) ?: destination

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F7F5))
            .padding(horizontal = 24.dp, vertical = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2F7D75).copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.ConfirmationNumber,
                    contentDescription = null,
                    tint = Color(0xFF2F7D75),
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = readyText,
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1A1A1A)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = qrInstructionText,
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = originText.uppercase(),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = origen,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF1A1A1A)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .height(2.dp)
                            .width(30.dp)
                            .background(Color(0xFF2F7D75).copy(alpha = 0.3f))
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = destinationLabel.uppercase(),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                            letterSpacing = 0.5.sp
                        )
                        Text(
                            text = destino,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF2F7D75),
                            textAlign = TextAlign.End
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))
                HorizontalDivider(color = Color(0xFFF0F0F0), thickness = 1.dp)
                Spacer(modifier = Modifier.height(18.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = departureText,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = route?.departureTime ?: "--:--",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = arrivalText,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = route?.arrivalTime ?: "--:--",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = dateText,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = date,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = peopleText,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "$people",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A1A1A)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))
                HorizontalDivider(color = Color(0xFFF0F0F0), thickness = 1.dp)
                Spacer(modifier = Modifier.height(18.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = priceText,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = route?.getPriceFormatted(currentLanguage) ?: "--",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFFE07A5F)
                        )
                    }


                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(PlannerColors.QRBackground.copy(alpha = 0.6f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.QrCode,
                            contentDescription = stringResource(R.string.planner_confirmation_qr_content_description),
                            modifier = Modifier.size(70.dp),
                            tint = Color.Black
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón finalizador
        PlannerPrimaryButton(
            text = finishText,
            onClick = onFinishClick
        )
    }
}