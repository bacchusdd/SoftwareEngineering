from photo_handler import PhotoHandler
import config
import time
from PIL import Image

from notifier import Notifier


class MotionDetector:  # UC-9
    def start_monitoring():
        # turn on camera
        # capture photo until config duration (loop)
        time_start = time.time()

        while True:
            capture_image = capture_image()

            # Intruder detected
            if MotionDetector.__detect_intruder(capture_image) is True:
                Notifier.notify_alert()
                PhotoHandler.handle_photo()
                break

            # Anomaly detected
            elif MotionDetector.__detect_anomaly() is True:
                Notifier.notify_notification()
                break

            # Time out
            elif time.time() >= time_start + config.MAX_DETECTION_DURATION:
                break

            time.sleep(1 / config.DETECTION_PER_SEC)
            print("Nothing detected")

    def __detect_intruder(self, capture):
        return False

    def __detect_anomaly(self):
        return False

    def capture_image(self):
        pass  # return image

    def stop_monitoring(self):
        pass
