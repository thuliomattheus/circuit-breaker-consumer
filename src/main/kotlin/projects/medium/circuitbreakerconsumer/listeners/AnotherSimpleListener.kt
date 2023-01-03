package projects.medium.circuitbreakerconsumer.listeners

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.RetryableTopic
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component
import java.util.*


@Component
class AnotherSimpleListener {

    @KafkaListener(
        topics = ["another_teste_topic"],
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
