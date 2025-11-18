#!/bin/bash
cleanup() {
  echo "Cleaning up /tmp/kafk-logs..."
  rm -rf /tmp/kafka-logs
}

trap cleanup EXIT

/usr/lib/kafka/bin/kafka-server-start.sh /usr/lib/kafka/config/server.properties

echo "Kafka Broker has stopped."
