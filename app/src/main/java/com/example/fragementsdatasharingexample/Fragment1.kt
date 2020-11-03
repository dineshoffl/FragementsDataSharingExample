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


class Fragment1 : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.first_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_fragment1.setOnClickListener {
            EventBus.getDefault().post(Events.FragmentOneClick1ToFragmentTwo("Fragment click1 one clicked"));
        }
        btn2_fragment1.setOnClickListener {
            EventBus.getDefault().post(Events.FragmentOneClick2ToFragmentTwo(1));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTextReceived(result: Events) {
        when(result){
            is Events.FragmentTwoClick1ToFragmentOne ->{
                tv_first_fragment.text = result.message
            }
            is Events.FragmentTwoClick2ToFragmentOne ->{
                tv_first_fragment.text = result.message.toString() + "Clicked"
            }
        }
        EventBus.getDefault().post(Events.FragmentOneToMainActivity(tv_first_fragment.text.toString()));
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