from flask import Flask
from historymanage.DB_history import History


class Action:
    def __init__(self):
        self.delete = False

    def selecthistory(self, user_id, date):
        photo = history.getPhoto(user_id, date)
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

@app.route("/delete/<user_id>/<date>/<photo_id>")
def deletephoto(user_id, date, photo_id):
    action.deletehistory(user_id, date, photo_id)
    return "Success"

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

if __name__ == "__main__":
    app.run(debug=True)