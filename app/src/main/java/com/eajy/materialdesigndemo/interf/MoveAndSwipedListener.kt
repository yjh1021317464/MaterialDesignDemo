package com.eajy.materialdesigndemo.interf

/**
 * Created by zhang on 2016.08.21.
 */
interface MoveAndSwipedListener {

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean

    fun onItemDismiss(position: Int)

}
