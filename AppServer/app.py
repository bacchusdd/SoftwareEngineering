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
    u = db.login(u_id, u_pw)
    if u:
        return jsonify({"isSuccess": True, "id": u["id"], "password": u["password"], "name": u["name"], "email": u["email"]}), 201
    else:
        return jsonify({"isSuccess": False}), 201

@app.route("/signup", methods=["GET"])
def signup():
    u_id = request.args.get("id")
    u_pw = request.args.get("pw")
    u_name = request.args.get("name")
    u_email = request.args.get("email")
    if db.signup(u_id, u_pw, u_name, u_email):
        return jsonify({"isSuccess": True, "id": u_id, "password": u_pw, "name": u_name, "email": u_email}), 201
    else:
        return jsonify({"isSuccess": False}), 201


if __name__ == "__main__":
    app.run(debug=True)