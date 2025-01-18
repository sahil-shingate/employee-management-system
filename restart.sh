#!/bin/bash

# Define the JAR file name
JAR_NAME="application-0.0.1-SNAPSHOT.jar"

# Find the process ID (PID) of the running JAR
echo "Looking for the process running $JAR_NAME..."
PID=$(ps aux | grep "[j]ava.*$JAR_NAME" | awk '{print $2}')

# If the PID exists, kill the process
if [ -n "$PID" ]; then
  echo "Found process with PID: $PID. Terminating..."
  kill -9 "$PID"
  echo "Process terminated."
else
  echo "No process found for $JAR_NAME."
fi

# Start the new instance (optional)
echo "Starting a new instance of $JAR_NAME..."
nohup java -jar build/libs/application-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
echo "New instance started."
