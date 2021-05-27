import pyrebase
import json

class History:
    def __init__(self):
        with open("../auth/firebaseAuth.json") as f:
            config = json.load(f)
        self.firebase = pyrebase.initialize_app(config)

    def filenametoURL(self, filename)
        url = ""
        return url

    def deletePhoto(self, filename):
        url = self.filenametoURL(filename)
        pass

    def getPhoto(self, filename):
        url = self.filenametoURL(filename)
        pass