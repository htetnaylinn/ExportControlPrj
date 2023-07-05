package com.example.exportControl.messagingstompwebsocket

import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.http.server.ServletServerHttpRequest
import org.springframework.http.server.ServletServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeFailureException
import org.springframework.web.socket.server.HandshakeHandler
import org.springframework.web.socket.server.HandshakeInterceptor
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class WssHandshakeInterceptor : HandshakeInterceptor {
    override fun beforeHandshake(request: ServerHttpRequest, response: ServerHttpResponse, handler: WebSocketHandler, attributes: MutableMap<String, Any>): Boolean {
        if (request is ServletServerHttpRequest) {
            val servletRequest: HttpServletRequest = request.servletRequest
            val servletResponse: HttpServletResponse = (response as ServletServerHttpResponse).servletResponse
            val uri = servletRequest.requestURL.toString().replace("ws://", "wss://")
            servletResponse.setHeader("Location", uri)
            servletResponse.status = HttpServletResponse.SC_TEMPORARY_REDIRECT
            servletResponse.flushBuffer()
            return false
        } else {
            throw HandshakeFailureException("Unexpected request type: ${request::class.simpleName}")
        }
    }

    override fun afterHandshake(request: ServerHttpRequest, response: ServerHttpResponse, handler: WebSocketHandler, exception: Exception?) {}
}