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
        return jsonify({"isSuccess": True, "id": u["id"], "password": u["password"]}), 200
    else:
        return jsonify({"isSuccess": False}), 200

@app.route("/registercheck", methods=["GET"])
def register_check():
    u_id = request.args.get("id")
    if db.register_verification(u_id):
        return jsonify({"isSuccess": True, "id": u_id, "password": u_pw}), 200
    else:
        return jsonify({"isSuccess": False}), 400

@app.route("/register", methods=["GET"])
def register():
    u_id = request.args.get("id")
    u_pw = request.args.get("pw")
    if db.register(u_id, u_pw):
        return jsonify({"isSuccess": True, "id": u_id, "password": u_pw}), 200
    else:
        return jsonify({"isSuccess": False}), 400

@app.route("/changepw", methods=["GET"])
def change_password():
    u_id = request.args.get("id")
    u_pw = request.args.get("pw")
    if db.change_password(u_id, u_pw):
        return jsonify({"isSuccess": True, "id": u_id, "password": u_pw}), 200
    else:
        return jsonify({"isSuccess": False}), 400

@app.route("/deleteaccount", methods=["GET"])
def delete_account():
    u_id = request.args.get("id")
    if db.delete_account(u_id):
        return jsonify({"isSuccess": True}), 200
    else:
        return jsonify({"isSuccess": False}), 400

if __name__ == "__main__":
    app.run(debug=True)