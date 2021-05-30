from flask import Flask
from DB_handler import DBModule

app = Flask(__name__)
db = DBModule()


@app.route("/")
def hello_world():
    return "Hello AppServer!"

@app.route("/photolist", methods=["GET"])
def getPhoto_():
    user_id = request.args.get("user_id")
    date = request.args.get("date")
    ph = db.getPhoto(user_id, date)

    if ph:
        return jsonify(ph), 200
    else:
        return jsonify({"isSuccess": False}), 200


if __name__ == "__main__":
    app.run(debug=True)
