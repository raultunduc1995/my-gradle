package com.example.businesslogic

import com.example.datamodel.Message
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory

class PrintService {
    companion object {
        private val logger = LoggerFactory.getLogger(PrintService::class.java)
    }

    fun print(message: Message) {
        logger.info("Printing the message: ${message.text}")
        println(
            StringUtils.trim(message.text.trim())
        )
    }
}