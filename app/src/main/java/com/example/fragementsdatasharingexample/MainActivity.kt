package com.example.fragementsdatasharingexample


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().add(R.id.container1, Fragment1()).commit()
        supportFragmentManager.beginTransaction().add(R.id.container2, Fragment2()).commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTextReceived(result: Events.FragmentOneToMainActivity) {
        tv_main_activity.text = result.message
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}