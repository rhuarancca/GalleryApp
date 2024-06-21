package ui.auth

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.galleryapp.ui.theme.GalleryAppTheme


class RegisterActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryAppTheme {
                RegisterScreen()
            }
        }

    }
}

@Composable
fun RegisterScreen(navController: NavController = rememberNavController(), registerViewModel: RegisterViewModel = viewModel()){
    Log.d("RegisterScreen", "RegisterScreen Composable called")
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var password0 by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val registerState by registerViewModel.registerState.collectAsState()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
       Text(text = "Register",style = MaterialTheme.typography.headlineMedium)
       Spacer(modifier = Modifier.height(16.dp))
        BasicTextField(
            value = email,
            onValueChange = {email = it},

            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true
        ){
            Text(
                text = if(email.isEmpty()) "Email" else "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
            it()
        }
        TextField(
            value = password0,
            onValueChange = { password0 = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = confirmPassword,
            onValueChange = {confirmPassword = it},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Text(
                text = if(confirmPassword.isEmpty()) "Confirm Password" else "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
            )
            it()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                Log.d("RegisterScreen", "Register button clicked with email: $email and password: $password")
                registerViewModel.register(email,password,confirmPassword)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register")
            
        }

        when(registerState){
            is RegisterState.Loading -> {
                Log.d("RegisterScreenLoading", "Loading state")
                CircularProgressIndicator()
            }
            is RegisterState.Success -> {
                Log.d("RegisterScreenSuccess", "Registration successful")
                Toast.makeText(context,"Registration Successful",Toast.LENGTH_SHORT).show()
                navController.navigate("login")
            }

            is RegisterState.Error ->{
                Log.d("RegisterScreenError", "Registration error")
                val error = (registerState as RegisterState.Error).message
                Toast.makeText(context,error,Toast.LENGTH_SHORT).show()
            }
            else -> {}
        }

    }

}