import pyrebase
import json

class DBModule:
    def __init__(self):
        with open("./auth/firebaseAuth.json") as f:
            config = json.load(f)

        firebase = pyrebase.initialize_app(config)
        self.db = firebase.database()

    def login(self, u_id, u_pw):
        userlist = self.db.child("userlist").get().val()
        try:
            userinfo = userlist[u_id]
            if userinfo["password"] == u_pw:
                return userinfo
            else:
                return False
        except:
            return False

    def register(self, u_id, u_pw):
        information = {
            "id": u_id,
            "password": u_pw
        }
        if self.register_verification(u_id):
            self.db.child("userlist").child(u_id).set(information)
            return True
        else:
            return False

    def register_verification(self, u_id):
        userlist = self.db.child("userlist").get().val()
        for i in userlist:
            if u_id == i:
                return False
        return True

    def change_password(self, u_id, u_pw):
        userlist = self.db.child("userlist").get().val()
        try:
            userinfo = userlist[u_id]
            if userinfo["password"] != u_pw:
                information = {
                    "id": u_id,
                    "password": u_pw
                }
                self.db.child("userlist").child(u_id).set(information)
                return True
            else:
                return False
        except:
            return False

    def delete_account(self, u_id):
        userlist = self.db.child("userlist").get().val()
        try:
            userinfo = userlist[u_id]
            if userinfo:
                self.db.child("userlist").child(u_id).remove()
                return True
            else:
                return False
        except:
            return False
