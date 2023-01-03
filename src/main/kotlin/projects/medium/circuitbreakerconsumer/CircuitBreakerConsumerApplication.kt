package projects.medium.circuitbreakerconsumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.StreamsConfig
import org.apache.kafka.streams.kstream.Produced
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.system.exitProcess

@SpringBootApplication
class CircuitBreakerConsumerApplication

fun main(args: Array<String>) {
	runApplication<CircuitBreakerConsumerApplication>(*args)
	val latch = CountDownLatch(1)
	val streams = test()

	// attach shutdown handler to catch control-c
	Runtime.getRuntime().addShutdownHook(object : Thread("streams-wordcount-shutdown-hook") {
		override fun run() {
			streams.close()
			latch.countDown()
		}
	})

	try {
		streams.start()
		latch.await()
	} catch (e: Throwable) {
		exitProcess(1)
	}
	exitProcess(0)
}

private fun test() =
	StreamsBuilder()
		.also {
			System.err.println("starting test")
		}
		.apply {
			stream<String, String>("teste_topic")
				.to("another_teste_topic", Produced.with(Serdes.String(), Serdes.String()))
		}
		.let {
			KafkaStreams(it.build(), x())
		}
		.also {
			System.err.println("test done")
		}

private fun x(): Properties =
	Properties()
		.also {
			System.err.println("starting x")
		}
		.apply {
			putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, "redirect-test")
			putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
			putIfAbsent(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest")
			putIfAbsent(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String()::class.java.name)
			putIfAbsent(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String()::class.java.name)
		}
		.also {
			System.err.println("x done")
		}
