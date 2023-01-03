package projects.medium.circuitbreakerconsumer.listeners

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class SimpleListener {

    @KafkaListener(
        topics = ["teste_topic"],
    )
    @RetryableTopic(
        numPartitions = "3",
        replicationFactor = "1",
    )
    fun consume(message: String, ack: Acknowledgment) {
        System.err.println(message)
        ack.acknowledge()
    }
}
