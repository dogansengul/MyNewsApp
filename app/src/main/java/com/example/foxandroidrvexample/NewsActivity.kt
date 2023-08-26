package com.example.foxandroidrvexample

//noinspection SuspiciousImport
import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.foxandroidrvexample.DataPackage.News
import com.example.foxandroidrvexample.DataPackage.NewsList
import com.example.foxandroidrvexample.databinding.ActivityNewsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class NewsActivity : AppCompatActivity() {
    private var binding: ActivityNewsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val bundle: Bundle? = intent.extras
        val position = bundle!!.getInt("Position")

        setUpActivity(position)

    }
    private fun setUpActivity(position: Int) {
        val newsList = NewsList.getNewsList()
        val report = newsList[position]
        binding?.tvTitle?.text = report.title.toString()
        //binding?.tvArticle?.text = report.title.toString()
        val requestOptions = RequestOptions()
            .placeholder(R.mipmap.sym_def_app_icon)
            .error(R.mipmap.sym_def_app_icon)
        Glide.with(applicationContext)
            .applyDefaultRequestOptions(requestOptions)
            .load(report.imageURL)
            .into(binding!!.ivArticleImage)

        CoroutineScope(Dispatchers.Main).launch {
            val haberMetni = fetchArticalText(report)
            binding?.tvArticle?.text = haberMetni
        }
    }
    private suspend fun fetchArticalText(report: News): String = withContext(Dispatchers.IO) {
        val url = report.link

        try {
            val doc = Jsoup.connect(url).get()
            val haberMetni = doc.select("div[data-component=\"text-block\"] p").text()
            haberMetni
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}