package com.systemtechnology.clientregister

import com.systemtechnology.clientregister.utils.DoubleClick
import org.junit.Assert
import org.junit.Test

class DoubleClickTest {

    @Test
    fun doubleClickIsWorkingRight(){
        val dc = DoubleClick()
        Assert.assertFalse( dc.isDoubleClick() )
        Assert.assertTrue(  dc.isDoubleClick() )
        Assert.assertFalse( dc.isSingleClick() )
    }

}
