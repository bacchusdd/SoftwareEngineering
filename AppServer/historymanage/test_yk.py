import pyrebase

config = {
    "apiKey": "AIzaSyDg6YB90dDWMXuyJMFVWEPdcSGgmrzOjSE",
    "authDomain": "motion-detection-app-39b9b.firebaseapp.com",
    "databaseURL": "https://motion-detection-app-39b9b-default-rtdb.firebaseio.com",
    "projectId": "motion-detection-app-39b9b",
    "storageBucket": "motion-detection-app-39b9b.appspot.com",
    "messagingSenderId": "630374650472",
    "appId": "1:630374650472:web:a250121341a75fe33661dd",
    "measurementId": "G-EZ2JHNE4CC"
};

firebase = pyrebase.initialize_app(config)
firemain = firebase.database()

//update : 값 생성
firemain.child("photolist").child("kang").child("dates").child("date").child("2021-05-29").child("urls").update({"photo_url":"gs://motion-detection-app-39b9b.appspot.com/ex1.jpg"})
