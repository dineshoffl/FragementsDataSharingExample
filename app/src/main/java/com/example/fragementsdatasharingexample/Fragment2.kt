package com.example.fragementsdatasharingexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.first_fragment_layout.*
import kotlinx.android.synthetic.main.second_fragment_layout.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class Fragment2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.second_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_fragment2.setOnClickListener {
            EventBus.getDefault().post(Events.FragmentTwoToFragmentOne("Fragment two clicked"));
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTextReceived(result: Events.FragmentOneToFragmentTwo) {
        tv_second_fragment.text = result.message
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