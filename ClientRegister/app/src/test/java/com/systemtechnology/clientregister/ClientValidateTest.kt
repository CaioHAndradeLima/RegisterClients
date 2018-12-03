package com.systemtechnology.clientregister

import com.systemtechnology.clientregister.entity.Client
import com.systemtechnology.clientregister.validate_helper.ClientValidate
import org.junit.Test

class ClientValidateTest {

    @Test
    fun testIfValidateIsWorking1() {
        val client = Client()

        assert( ClientValidate.isValid( client ) != 0 )
    }

    @Test
    fun testIfValidateIsWorking2() {
        val client = Client()
        client.name = "224245235"
        assert( ClientValidate.isValid( client ) != 0 )
    }

    @Test
    fun testIfValidateIsWorking3() {
        val client = Client()
        client.name = "cadifo0 kdosaf"
        client.CPF = "224245235"
        assert( ClientValidate.isValid( client ) != 0 )
    }

    @Test
    fun testIfValidateIsWorking4() {
        val client = Client()
        client.name = "cadifo0 kdosaf"
        client.CPF = "224245235"
        assert( ClientValidate.isValid( client ) != 0 )
    }

    @Test
    fun testIfValidateIsWorking5() {
        val client = Client()
        client.name = "Caio Henrique"
        client.CPF = "44574551801"

        //without address
        assert( ClientValidate.isValid( client ) != 0 )
    }

}
