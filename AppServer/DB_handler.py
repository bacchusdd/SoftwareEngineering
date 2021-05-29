from PIL import Image
import pyrebase
import json


class DBModule:
    def __init__(self):
        with open("./auth/firebaseAuth.json") as f:
            config = json.load(f)

        self.firebase = pyrebase.initialize_app(config)

    def login(self, id, pwd):
        pass

    def signup(self, id, pwd, name, email):
        pass

    def save_photo(self, image: Image):
        # upload photo object to to DB
        pass

    # DB functions...
