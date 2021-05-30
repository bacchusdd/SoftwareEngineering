import pyrebase
import json

class History:
    def __init__(self):
        with open("../auth/firebaseAuth.json") as f:
            config = json.load(f)
        self.firebase = pyrebase.initialize_app(config)
        self.historydb = self.firebase.database()
        self.historystorage = self.firebase.storage()


    def deletePhoto(self, user_id, date, photo_id):
        self.historydb.child("photolist").child(user_id).child("dates").child(date).child("urls").child(photo_id).remove

    def getPhoto(self, user_id, date):
        photos = self.historydb.child("photolist").child(user_id).child("dates").child(date).child("urls").get()
        photos = photos.val().values()
        print(photos)
        photo_list = []
        for url in photos:
            photo_list.append(self.historystorage.child(url).get_url(None))
        photo_list = tuple(photo_list)
        print(photo_list)

        return photo_list

    def getDate(self, user_id):
        date_list = self.historydb.child("photolist").child(user_id).child("dates").get()
        date_list = tuple(date_list.val().keys())
        return date_list
