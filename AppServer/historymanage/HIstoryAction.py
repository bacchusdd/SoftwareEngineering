from flask import Flask
from historymanage.DB_history import History

app = Flask(__name__)
history = History()

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
        return date

@app.route("/")
def useraction():
    # user에게 action과 filename을 받는다
    # 받은 action을 처리
    action = Action()
    #p = action.selecthistory("kang", "2021-05-26")
    p = action.selectdate("kang")
    return p



if __name__ == "__main__":
    app.run(debug=True)