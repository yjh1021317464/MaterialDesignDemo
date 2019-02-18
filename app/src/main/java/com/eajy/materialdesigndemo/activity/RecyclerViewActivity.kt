package com.eajy.materialdesigndemo.activity

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.support.v7.widget.helper.ItemTouchHelper
import com.eajy.materialdesigndemo.R
import com.eajy.materialdesigndemo.adapter.RecyclerViewAdapter
import com.eajy.materialdesigndemo.view.ItemTouchHelperCallback

class RecyclerViewActivity : AppCompatActivity() {

    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var mRecyclerView: RecyclerView? = null
    private var fab: FloatingActionButton? = null
    private var adapter: RecyclerViewAdapter? = null

    private var color = 0
    private var data: List<String>? = null
    private var insertData: String? = null
    private var loading: Boolean = false
    private var loadTimes: Int = 0

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val linearLayoutManager =
                    recyclerView.layoutManager as LinearLayoutManager
            if (!loading &&
                    linearLayoutManager.itemCount ==
                    linearLayoutManager.findLastVisibleItemPosition() + 1) {

                Handler().postDelayed({
                    if (loadTimes <= 5) {
                        adapter!!.removeFooter()
                        loading = false
                        adapter!!.addItems(data!!)
                        adapter!!.addFooter()
                        loadTimes++
                    } else {
                        adapter!!.removeFooter()
                        Snackbar.make(mRecyclerView!!, getString(R.string.no_more_data), Snackbar.LENGTH_SHORT)
                                .setCallback(object : Snackbar.Callback() {
                                    override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                        super.onDismissed(transientBottomBar, event)
                                        loading = false
                                        adapter!!.addFooter()
                                    }
                                }).show()
                    }
                }, 1500)

                loading = true
            }
        }

    }

    private val screenWidthDp: Int
        get() {
            val displayMetrics = resources.displayMetrics
            return (displayMetrics.widthPixels / displayMetrics.density).toInt()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val toolbar = findViewById<Toolbar>(R.id.toolbar_recycler_view)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        initData()
        findView()
        initView()
    }

    private fun initData() {
        data = List(20) {
            (it + 1).toString() + ""
        }

        insertData = "0"
        loadTimes = 0
    }

    private fun findView() {
        fab = findViewById(R.id.fab_recycler_view)
        mRecyclerView = findViewById(R.id.recycler_view_recycler_view)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout_recycler_view)
    }

    private fun initView() {

        mRecyclerView!!.layoutManager = when {
            screenWidthDp >= 1200 -> {
                GridLayoutManager(this, 3)
            }
            screenWidthDp >= 800 -> {
                GridLayoutManager(this, 2)
            }
            else -> {
                LinearLayoutManager(this)
            }
        }

        adapter = RecyclerViewAdapter(this)
        mRecyclerView!!.adapter = adapter
        adapter!!.addHeader()
        adapter!!.setItems(data!!)
        adapter!!.addFooter()

        fab!!.setOnClickListener {
            val linearLayoutManager = mRecyclerView!!.layoutManager as LinearLayoutManager?
            adapter!!.addItem(linearLayoutManager!!.findFirstVisibleItemPosition() + 1, insertData!!)
        }

        val callback = ItemTouchHelperCallback(adapter)
        val mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper.attachToRecyclerView(mRecyclerView)


        swipeRefreshLayout!!.setColorSchemeResources(
                R.color.google_blue,
                R.color.google_green,
                R.color.google_red,
                R.color.google_yellow
        )
        swipeRefreshLayout!!.setOnRefreshListener {
            Handler().postDelayed({
                if (color > 4) {
                    color = 0
                }
                adapter!!.setColor(++color)
                swipeRefreshLayout!!.isRefreshing = false
            }, 2000)
        }

        mRecyclerView!!.addOnScrollListener(scrollListener)
    }

}
