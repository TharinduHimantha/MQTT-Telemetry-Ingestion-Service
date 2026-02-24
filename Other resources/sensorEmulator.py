import json
import time
import paho.mqtt.client as mqtt

BROKER = "broker.hivemq.com"
PORT = 1883
TOPIC = "thw/telemetry/deviceA"
CLIENT_ID = "python-mqtt-publisher"

payload = {
    "deviceId": "Gayumi",
    "value": 155.7,
    "sensorHealth": 108,
    "timestamp": "2026-02-20T16:21:06Z"
}

message = json.dumps(payload)


# -----------------------
# Callback Functions
# -----------------------

def on_connect(client, userdata, flags, reason_code, properties):
    if reason_code == 0:
        print("Connected to broker successfully")
    else:
        print(f"Connection failed with reason code {reason_code}")


def on_publish(client, userdata, mid, reason_code, properties):
    print(f"Message ID {mid} published successfully")


def on_disconnect(client, userdata, flags, reason_code, properties):
    print("Disconnected from broker")


# -----------------------
# Create Client
# -----------------------

client = mqtt.Client(
    client_id=CLIENT_ID,
    callback_api_version=mqtt.CallbackAPIVersion.VERSION2
)

client.on_connect = on_connect
client.on_publish = on_publish
client.on_disconnect = on_disconnect

# -----------------------
# Connect
# -----------------------

client.connect(BROKER, PORT, 60)

# Start network loop (required for QoS handling)
client.loop_start()

# Small delay to ensure connection is established
time.sleep(1)

# -----------------------
# Publish
# -----------------------

result = client.publish(TOPIC, message, qos=1)

# Wait until publish is completed (PUBACK received)
result.wait_for_publish()

if result.is_published():
    print("Publish confirmed (QoS 1 handshake completed)")
else:
    print("Publish not confirmed")

print("Message sent:", message)

# -----------------------
# Cleanup
# -----------------------

time.sleep(1)  # allow callback to print
client.loop_stop()
client.disconnect()
