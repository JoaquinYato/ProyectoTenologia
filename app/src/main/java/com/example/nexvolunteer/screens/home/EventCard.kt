package com.example.nexvolunteer.screens.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.nexvolunteer.model.Event
import com.example.nexvolunteer.notifications.NotificationHelper
import com.example.nexvolunteer.notifications.ReminderHelper
import com.example.nexvolunteer.viewmodel.SearchViewModel
import com.example.nexvolunteer.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn

@Composable
fun EventCard(

    event: Event
) {

    val context = LocalContext.current

    val userViewModel = remember {

        UserViewModel()
    }

    val searchViewModel = remember {

        SearchViewModel()
    }

    val uid =
        FirebaseAuth.getInstance()
            .currentUser?.uid ?: ""

    LaunchedEffect(Unit) {

        userViewModel.loadUser(uid)
    }
    AnimatedVisibility(

        visible = true,

        enter = fadeIn()

    ) {

        Card(

            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),

            shape = RoundedCornerShape(20.dp),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            )

        ) {

            Column(

                modifier = Modifier.padding(16.dp)
            ) {

                /*
                    IMAGEN EVENTO
                 */

                if (event.imageUrl.isNotEmpty()) {

                    AsyncImage(

                        model = event.imageUrl,

                        contentDescription = null,

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(
                                RoundedCornerShape(16.dp)
                            )
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                }

                if (event.destacado) {

                    Row {

                        Icon(

                            imageVector = Icons.Default.Star,

                            contentDescription = null
                        )

                        Spacer(
                            modifier = Modifier.width(4.dp)
                        )

                        Text(
                            text = "Evento Destacado"
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                }

                /*
                    TITULO
                 */

                Text(

                    text = event.titulo,

                    style = MaterialTheme
                        .typography
                        .titleLarge
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                /*
                    DESCRIPCION
                 */

                Text(event.descripcion)

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                /*
                    INFO
                 */

                Text("📍 ${event.ubicacion}")

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text("📅 ${event.fecha}")

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text("🏷️ ${event.categoria}")

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                val user = userViewModel.user.value

                val yaParticipa =
                    user.eventosAsistidos.contains(event.id)

                Text("👥 ${event.participantes} participantes")

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                /*
                    FAVORITOS
                 */

                IconButton(

                    onClick = {

                        val user =
                            userViewModel.user.value

                        searchViewModel.addFavorite(

                            uid,

                            event.id,

                            user.favoritos,

                            onSuccess = {

                                Toast.makeText(

                                    context,

                                    "Agregado a favoritos",

                                    Toast.LENGTH_LONG

                                ).show()

                                NotificationHelper.showNotification(

                                    context,

                                    "Favorito agregado",

                                    "${event.titulo} fue agregado a favoritos"
                                )
                            },

                            onError = {

                                Toast.makeText(

                                    context,

                                    it,

                                    Toast.LENGTH_LONG

                                ).show()
                            }
                        )
                    }

                ) {

                    Icon(

                        Icons.Default.Favorite,

                        contentDescription = null
                    )
                }

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                /*
                    PARTICIPAR
                 */

                Button(
                    enabled = !yaParticipa,
                    onClick = {

                        userViewModel.attendEvent(

                            uid,

                            event.id,

                            onSuccess = {

                                Toast.makeText(

                                    context,

                                    "Participación registrada",

                                    Toast.LENGTH_LONG

                                ).show()

                                /*
                                    NOTIFICACION
                                 */

                                NotificationHelper.showNotification(

                                    context,

                                    "Participación Registrada",

                                    "Te uniste a ${event.titulo}"
                                )

                                /*
                                    RECORDATORIO
                                 */

                                ReminderHelper.scheduleReminder(

                                    context,

                                    event.titulo,

                                    System.currentTimeMillis() + 60000
                                )
                            },

                            onError = {

                                Toast.makeText(

                                    context,

                                    it,

                                    Toast.LENGTH_LONG

                                ).show()
                            }
                        )
                    },

                    modifier = Modifier.fillMaxWidth()

                ) {

                    Text(
                        if (yaParticipa)
                            "Ya inscrito"
                        else
                            "Participar"
                    )
                }
            }
        }
    }
}