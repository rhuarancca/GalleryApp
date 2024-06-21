package ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.auth.LoginScreen
import ui.auth.LoginViewModel
import ui.auth.RegisterScreen
import ui.auth.RegisterViewModel
import com.example.galleryapp.ui.theme.GalleryAppTheme
import data.model.factory.GalleryViewModelFactory
import data.model.factory.LoginViewModelFactory
import data.model.factory.RegisterViewModelFactory
import data.model.factory.ImageScreen
import data.model.factory.Lounge1ViewModelFactory
import ui.gallery.GalleryScreen
import ui.gallery.GalleryViewModel
import ui.lounge.Lounge1ViewModel
import ui.painting.Lounge1Screen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Log.d("MainActivity", "onCreate called")
        setContent {
            GalleryAppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    Log.d("MainScreen", "MainScreen Composable called")
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home" ){
        composable("login"){
            Log.d("Navigation", "Navigating to LoginScreen")
            val context = LocalContext.current
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(context.applicationContext)
            )

            LoginScreen(navController = navController, loginViewModel = loginViewModel)
        }
        composable("register"){
            Log.d("Navigation", "Navigating to RegisterScreen")
            val context = LocalContext.current
            val registerViewModel: RegisterViewModel = viewModel(
                factory = RegisterViewModelFactory(context.applicationContext)
            )
            RegisterScreen(navController = navController, registerViewModel = registerViewModel)
        }
        composable("home"){
            Log.d("Navigation", "Navigating to Home")
            val context = LocalContext.current
            val galleryViewModel: GalleryViewModel = viewModel(
                factory = GalleryViewModelFactory(context.applicationContext)
            )
            GalleryScreen(navController = navController, galleryViewModel = galleryViewModel)
        }
        composable("gallery"){
            Log.d("Navigation", "Navigating to gallery")
            val context = LocalContext.current
            val galleryViewModel: GalleryViewModel = viewModel(
                factory = GalleryViewModelFactory(context.applicationContext)
            )
            GalleryScreen(navController = navController, galleryViewModel = galleryViewModel)
        }
        composable("lounge1"){
            Log.d("Navigation", "Navigating to Lounge1")
            val context = LocalContext.current
            val lounge1ViewModel: Lounge1ViewModel = viewModel(
                factory = Lounge1ViewModelFactory(context.applicationContext)
            )
            Lounge1Screen(navController = navController, lounge1ViewModel = lounge1ViewModel)
        }
        composable("image") {
            Log.d("Navigation", "Navigating to ImageScreen")
            ImageScreen()
        }
    }
    
}

@Composable
fun HomeScreen() {
    Log.d("HomeScreen", "HomeScreen Composable called")
    Text(text = "Welcome to the Home Screen", style = MaterialTheme.typography.headlineMedium)
}