package io.github.bffcorreia.randomgifs

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ShareActivity : AppCompatActivity() {

  private lateinit var gifView: ImageView
  private lateinit var toView: TextView
  private lateinit var subjectView: TextView
  private lateinit var bodyView: TextView
  private lateinit var shareView: Button
  private lateinit var gif: Gif

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_share)
    getIntentExtras()
    findViews()
    initViews()
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

  private fun getIntentExtras() {
    gif = intent.getParcelableExtra("gif")
  }

  private fun findViews() {
    gifView = findViewById(R.id.gif)
    toView = findViewById(R.id.to)
    subjectView = findViewById(R.id.subject)
    bodyView = findViewById(R.id.body)
    shareView = findViewById(R.id.share)
  }

  private fun initViews() {
    initActionBar()
    initGif()
    initSubject()
    initBody()
    initShare()
  }

  private fun initActionBar() {
    setTitle(R.string.share)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  private fun initGif() {
    Glide.with(this).asGif().load(gif.url).apply(RequestOptions.centerCropTransform()).into(gifView)
  }

  private fun initSubject() {
    val subject = "${getString(R.string.app_name)} - ${gif.title}"
    subjectView.text = subject
  }

  private fun initBody() {
    bodyView.text = gif.url
  }

  private fun initShare() {
    shareView.setOnClickListener({ shareByEmail() })
  }

  private fun shareByEmail() {
    val intent = Intent(Intent.ACTION_SENDTO)
    val emails = toView.text.replace("\\s".toRegex(), "").split(",").toTypedArray()
    intent.data = Uri.parse("mailto:")
    intent.putExtra(Intent.EXTRA_EMAIL, emails)
    intent.putExtra(Intent.EXTRA_SUBJECT, subjectView.text.toString())
    intent.putExtra(Intent.EXTRA_TEXT, bodyView.text)
    startActivity(intent)
  }
}