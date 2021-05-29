from flask import Flask, request, jsonify
from db_handler import DBModule
from motion_detector import MotionDetector

import config

app = Flask(__name__)
db = DBModule()


@app.route("/")
def hello_world():
    return "Hello AppServer!"


@app.route("/", methods=["GET", "POST"])
def handle_request():
    print("Successful Connection")
    return "Successful Connection"
    # Get orders. Startdetection, stopdetection


@app.route("/startdetection", methods=["GET", "POST"])
def start_detection():
    MotionDetector.start_monitoring()
    return "Start detection..."


@app.route("/stopdetection", methods=["GET", "POST"])
def stop_detection():
    MotionDetector.stop_monitoring()
    return "Stop detection..."


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=3306, debug=True)