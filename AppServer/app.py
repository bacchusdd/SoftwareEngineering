from flask import Flask, request, redirect, jsonify
from flask.helpers import url_for
from db_handler import DBModule
from socket_app import SocketApp

import config

app = Flask(__name__)
db = DBModule()


@app.route("/startdetection", methods=["GET", "POST"])
def start_detection():
    if request.method == "GET":
        return "Start_detection..."


@app.route("/stopdetection", methods=["GET", "POST"])
def stop_detection():
    if request.method == "GET":
        return "Stop_detection..."


@app.route("/", methods=["GET", "POST"])
def handle_request():
    print("Successful Connection")
    return "Hello AppServer!"


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=3306, debug=True)