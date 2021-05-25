from flask import Flask
from DB_handler import DBModule

app = Flask(__name__)
db = DBModule()


@app.route("/")
def hello_world():
    return "Hello AppServer!"


if __name__ == "__main__":
    app.run(debug=True)