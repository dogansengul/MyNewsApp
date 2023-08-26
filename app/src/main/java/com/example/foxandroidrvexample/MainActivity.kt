package com.example.foxandroidrvexample

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foxandroidrvexample.DataPackage.News
import com.example.foxandroidrvexample.DataPackage.NewsList
import com.example.foxandroidrvexample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.util.Collections
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var newsList: ArrayList<News>
    private lateinit var tempList: ArrayList<News>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        newsList = NewsList.getNewsList()
        tempList = arrayListOf<News>()
        tempList.addAll(newsList)

        setSupportActionBar(binding?.actionBar)
        newsAdapter = NewsAdapter(newsList)
        setUpRV()

        newsAdapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener{
            override fun onItemCLick(position: Int) {
                val intent = Intent(this@MainActivity, NewsActivity::class.java)
                intent.putExtra("Position", newsList[position].id-1)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        })
    }

    private fun searchNews(text: String) {
        // Clear the current list of news items.
        newsAdapter.updateList(emptyList())

        // Get the list of news items that match the search text.
        val filteredNewsList = newsList.filter {
            it.title.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))
        }

        // Update the adapter with the filtered list of news items.
        newsAdapter.updateList(filteredNewsList)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Call the searchNews() method to search for news items that match the new text.
                if(newText != null) {
                    searchNews(newText)
                } else {
                    newsAdapter.updateList(newsList)
                }
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    private fun setUpRV() {
        binding?.rvNewsRecyclerView?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

        //swipe delete gesture part
        val itemTouchHelper: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP
                    or ItemTouchHelper.DOWN
                    or ItemTouchHelper.END
                    or ItemTouchHelper.START,
            ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from_pos = viewHolder.bindingAdapterPosition
                val to_pos = target.bindingAdapterPosition
                Collections.swap(newsList, from_pos, to_pos)
                newsAdapter.notifyItemMoved(from_pos, to_pos)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                when(direction) {
                    ItemTouchHelper.LEFT -> {
                        val removed = newsList[position]
                        newsList.removeAt(position)
                        newsAdapter.notifyItemRemoved(position)
                        Snackbar.make(binding!!.rvNewsRecyclerView, "The news is removed.", Snackbar.LENGTH_SHORT)
                            .setAction("Undo", View.OnClickListener {
                                newsList.add(position, removed)
                                newsAdapter.notifyItemInserted(position)
                            }).show()
                    }
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                     dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.delete))
                    .addSwipeLeftActionIcon(R.drawable.ic_delete_foreground)
                    .create()
                    .decorate()
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive
                )
            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding?.rvNewsRecyclerView)

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}