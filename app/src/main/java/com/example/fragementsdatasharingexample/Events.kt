package com.example.fragementsdatasharingexample


open class Events {


    data class FragmentOneClick1ToFragmentTwo(val message: String) : Events()

    data class FragmentOneClick2ToFragmentTwo(val message: Int) : Events()

    data class FragmentTwoClick1ToFragmentOne(val message: String): Events()

    data class FragmentTwoClick2ToFragmentOne(val message: Int): Events()

    data  class FragmentOneToMainActivity(val message: String)


}