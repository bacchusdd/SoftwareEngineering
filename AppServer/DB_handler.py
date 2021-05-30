import pyrebase
import json


class DBModule:
    def __init__(self):
        with open("../auth/firebaseAuth.json") as f:
            config = json.load(f)
        self.firebase = pyrebase.initialize_app(config)
        self.historydb = self.firebase.database()
        self.historystorate = self.firebase.storage()

        self.firebase = pyrebase.initialize_app(config)

    def getPhoto(self, user_id, date):
        #for non-key jsonarray
        #photos = self.historydb.child("photolist").child(user_id).child("dates").child(date).child("urls").get()
        #photos = photos.val().values()
        #print(photos)
        #photos = tuple(photos)
        
        #convert dictionary to tuple
        photos = self.historydb.child("photolist").child(user_id).child("dates").child(date).child("urls").get().val()
        photos = tuple(photos.items())


        if len(photos) >= 0:
            return photos
        else:
            return "fail"

