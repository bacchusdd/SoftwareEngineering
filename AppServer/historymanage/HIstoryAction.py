from flask import Flask, render_template, request, jsonify
from historymanage.DB_history import History
from DB_handler import DBModule

class Action:
    def __init__(self):
        self.delete = False

    def selecthistory(self, user_id, date):
        photo = history.getPhoto(user_id, date)
        photo = {"photo": photo}
        return photo

    def deletehistory(self, user_id, date, photo_id):
        history.deletePhoto(user_id, date, photo_id)

    def selectdate(self, user_id):
        date = history.getDate(user_id)
        date = {"date": date}
        return date

app = Flask(__name__)
history = History()
action = Action()
db = DBModule()

@app.route("/history/<user_id>/<date>")
def accessphotos(user_id, date):
    photos = action.selecthistory(user_id, date)
    return photos

@app.route("/history/<user_id>")
def accesshistory(user_id):
    date = action.selectdate(user_id)
    return date

@app.route("/")
def useraction():
    return ""

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
