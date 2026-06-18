package com.example.coyhaiquebc.Planner.Plannerstep

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.Planner.PlannerColors
import com.example.coyhaiquebc.Planner.components.PlannerPrimaryButton

@Composable
fun PlannerWelcomeStep(
    onStartClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.planner_welcome_title),
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 38.sp,
            color = PlannerColors.TextPrimary
        )

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = stringResource(R.string.planner_welcome_subtitle),
            fontSize = 15.sp,
            lineHeight = 22.sp,
            color = PlannerColors.TextSecondary
        )

        Spacer(modifier = Modifier.height(34.dp))

        PlannerPrimaryButton(
            text = stringResource(R.string.planner_welcome_button),
            onClick = onStartClick
        )
    }
}