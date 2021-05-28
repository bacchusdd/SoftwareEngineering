from flask import Flask, jsonify, request
from DB_handler import DBModule

app = Flask(__name__)
db = DBModule()

@app.route("/")
def hello_world():
    return jsonify({"about": "Hello AppServer!"})

@app.route("/login", methods=["GET"])
def login():
    u_id = request.args.get("id")
    u_pw = request.args.get("pw")
    if u_id == "abc" and u_pw == "123":
        return jsonify({"isSuccess": True,"id": "abc","password": "123","name": "test","email": "test"}), 201
    else:
        return jsonify({"isSuccess": False}), 201


if __name__ == "__main__":
    app.run(debug=True)