package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color.*
import android.hardware.camera2.params.RggbChannelVector.RED
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_library_books_24)
            .setContentTitle("New Book")
            .setContentText(" A new Book to read is out. A very interesting and captiving book.Titled, the Glover Mentis ")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Often, depending on where you’re submitting, the length of the synopsis will vary. When you’re happy with your final draft, save a default copy to work with each time you prepare a new query or submission.\n" +
                        "\n" +
                        "If you have a default copy on file, it’s easier to mould your synopsis when you find that your next target asks for ‘a brief synopsis of no more than 300 words’. Simply work through the same process, keeping only what is critical to the manuscript.\n" +
                        "\n" +
                        "Likewise, if the guidelines suggest you have a few hundred words more than your original draft, use the extra words to show off. You might even be able to weave in one of those secondary elements you had to cut from your original.\n" +
                        "\n" +
                        "Be sure to save each new copy. It’s easier to reduce a 600-word synopsis down to 500 words than it is to cut down from 1000 words.\n" +
                        "\n" +
                        "Also, don’t forget to share your work. Give it to friends and family. Read it aloud with the members of your writers’ group. Just like you did with your manuscript, listen to feedback and be sure that your synopsis is the best it can be before you format it, ready for submission."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()



        val notificationManager = NotificationManagerCompat.from(this)

        binding.Notibtn.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID,builder)
        }

    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance).apply {

            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}