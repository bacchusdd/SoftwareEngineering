from flask import Flask
from historymanage import History

app = Flask(__name__)
historydb = History()


@app.route("/")
class Action:
    def __init__(self):
        self.delete = False

    def selecthistory(self, historyfile):
        photo = historydb.getPhoto(historyfile)
        return photo

    def deletehistory(self, historyfile):
        historydb.deletePhoto(historyfile)


if __name__ == "__main__":
    app.run(debug=True)