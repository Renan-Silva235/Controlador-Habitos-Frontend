package com.renansilva235.controlador_habitos.presentation.screens

import android.media.Image
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.platform.LocalConfiguration
import android.content.res.Configuration
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.FlowRow
@Composable
fun RegisterScreen(){
    val viewModel: RegisterViewModel = viewModel()
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordConfirmVisible by remember { mutableStateOf(false) }
    val primaryColor = colorResource(R.color.primary)
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(modifier = Modifier
                      .fillMaxSize()
                      .background(colorResource(R.color.background))
                      .verticalScroll(rememberScrollState())
                      .padding(horizontal = 24.dp,
                                vertical = if (isLandscape) 16.dp else 50.dp
                      ),
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
            modifier = Modifier.align(Alignment.Start),
            color = colorResource(R.color.text_primary)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = if(isLandscape) 2 else 1,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
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
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = colorResource(R.color.text_primary),
                        unfocusedLabelColor = colorResource(R.color.text_primary)
                    ),
                    isError = viewModel.passwordError,
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
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
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
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
                    visualTransformation = if(passwordConfirmVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = colorResource(R.color.text_primary),
                        unfocusedLabelColor = colorResource(R.color.text_primary)
                    ),
                    isError = viewModel.passwordConfirmError,
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordConfirmVisible = !passwordConfirmVisible
                        }) {
                            Icon(
                                imageVector = if (passwordConfirmVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
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

                    Text(
                        text = buildAnnotatedString {
                            append(stringResource(R.string.terms_prefix))
                            withLink(
                                LinkAnnotation.Clickable(
                                    tag = "terms",
                                    linkInteractionListener = {
                                        //to do
                                    },
                                    styles = TextLinkStyles(
                                        style = SpanStyle(
                                            color = primaryColor,
                                            textDecoration = TextDecoration.Underline
                                        )
                                    )

                                )
                            ){
                                append(stringResource(R.string.terms_link))
                            }
                        }
                    )
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
            }
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