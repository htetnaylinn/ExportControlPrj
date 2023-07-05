package com.example.exportControl.messagingstompwebsocket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import java.util.concurrent.TimeUnit

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(config: MessageBrokerRegistry) {
        config.enableSimpleBroker("/topic")
        config.setApplicationDestinationPrefixes("/app")
    }

    override fun configureWebSocketTransport(registration: WebSocketTransportRegistration) {
        registration
            .setSendTimeLimit(10 * 1000)
            .setSendBufferSizeLimit(512 * 1024)
            .setMessageSizeLimit(128 * 1024)
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry
            .addEndpoint("/gs-guide-websocket")
            .setAllowedOrigins("https://hl-gcs.com/")
            .withSockJS()

    }
}
