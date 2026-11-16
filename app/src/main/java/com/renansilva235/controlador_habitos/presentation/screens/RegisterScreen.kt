package com.renansilva235.controlador_habitos.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import com.renansilva235.controlador_habitos.R
import androidx.lifecycle.viewmodel.compose.viewModel
import com.renansilva235.controlador_habitos.viewModel.auth.RegisterViewModel

@Composable
fun RegisterScreen(){
    val viewModel: RegisterViewModel = viewModel()

    Column(modifier = Modifier
                      .fillMaxSize()
                      .background(colorResource(R.color.background))
                      .padding(horizontal = 24.dp, vertical = 50.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)

         ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable{

            }
        ) {
            Text(
                text = "‹",
                style = MaterialTheme.typography.headlineMedium,
                color = colorResource(R.color.primary)

            )

            Text(
                text = stringResource(R.string.back),
                modifier = Modifier.padding(start = 8.dp),
                color = colorResource(R.color.primary)
            )
        }

        Text(
            text = stringResource(R.string.register_title),
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = colorResource(R.color.text_primary)
        )


        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text(stringResource(R.string.name)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.name_placeholder),
                    modifier = Modifier.alpha(0.5f)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = colorResource(R.color.text_primary),
                unfocusedLabelColor = colorResource(R.color.text_primary)
            ),
            isError = viewModel.nameError
        )

        if(viewModel.nameError){
            Text(
                text = stringResource(
                    if(viewModel.nameRequiredError){
                        R.string.name_required
                    }else{
                        R.string.name_error
                    }
                ),
                color = colorResource(R.color.fieldError),
                fontSize = 12.sp
            )
        }

        OutlinedTextField(
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text(stringResource(R.string.email)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.email_placeholder),
                    modifier = Modifier.alpha(0.5f)

                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = colorResource(R.color.text_primary),
                unfocusedLabelColor = colorResource(R.color.text_primary)
            ),
            isError = viewModel.emailError
        )

        if(viewModel.emailError){
            Text(
                text = stringResource(
                if(viewModel.emailRequiredError){
                        R.string.email_required
                    }else{
                        R.string.email_error
                    }
                ),
                color = colorResource(R.color.fieldError),
                fontSize = 12.sp
            )
        }

        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
            label = { Text(stringResource(R.string.password)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.password_placeholder),
                    modifier = Modifier.alpha(0.5f)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = colorResource(R.color.text_primary),
                unfocusedLabelColor = colorResource(R.color.text_primary)
            ),
            isError = viewModel.passwordError
        )

        if(viewModel.passwordError){
            Text(
                text = stringResource(
                    if(viewModel.passwordRequiredError){
                        R.string.password_required
                    }else{
                        R.string.password_error
                    }
                ),
                color = colorResource(R.color.fieldError),
                fontSize = 12.sp
            )
        }else{
            Text(
                text = stringResource(R.string.tip),
                Modifier.alpha(0.5f),
                fontSize = 14.sp
            )
        }

        OutlinedTextField(
            value = viewModel.passwordConfirm,
            onValueChange = { viewModel.passwordConfirm = it },
            label = { Text(stringResource(R.string.confirmPassword)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.confirmPassword_placeholder),
                    modifier = Modifier.alpha(0.5f)
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedLabelColor = colorResource(R.color.text_primary),
                unfocusedLabelColor = colorResource(R.color.text_primary)
            ),
            isError = viewModel.passwordConfirmError
        )

        if(viewModel.passwordConfirmError){
            Text(
                text = stringResource(
                    if(viewModel.passwordConfirmRequiredError && viewModel.password != ""){
                        R.string.password_confirm_required
                    }else{
                        R.string.password_confirm_error
                    }
                ),
                color = colorResource(R.color.fieldError),
                fontSize = 12.sp
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = viewModel.termsAccepted,
                onCheckedChange = { viewModel.termsAccepted = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(R.color.primary),
                    uncheckedColor = colorResource(R.color.text_primary),
                    checkmarkColor = colorResource(R.color.background)
                )
            )

            Text(stringResource(R.string.terms))
        }

        if (viewModel.termsAcceptedError) {
            Text(
                text = stringResource(R.string.terms_required),
                color = Color.Red,
                fontSize = 12.sp
            )
        }

        Button(
            onClick = {
                if(viewModel.validateForm()){

                }
            },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.primary),
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(R.string.register_button),
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = stringResource(R.string.have_account),
            modifier = Modifier.clickable{

            }.align(Alignment.CenterHorizontally).alpha(0.5f),
            style = MaterialTheme.typography.labelLarge,

        )

        Text(
            text = stringResource(R.string.open),
            color = colorResource(R.color.primary),
            modifier = Modifier.clickable{

            }.align(Alignment.CenterHorizontally),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )


    }
}