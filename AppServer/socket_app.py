import sys
import socket
import time
import config
from multiprocessing import Process


class SocketApp:
    def __init__(self, host=config.LOCALHOST, port=config.PORT):
        print("SocketApp Initialized")
        self.port = port
        self.host = host

        self.server_socket = socket.socket(socket.AF_INET)
        self.server_socket.bind((self.host, self.port))

    def start_connection(self):
        while True:
            self.server_socket.listen(1)
            print("\n Waiting...")
            self.client_socket, addr = self.server_socket.accept()

            print("Connected by", addr)

            sending_data = 0

            received_data = self.client_socket.recv(1024)
            print(received_data.decode("utf-8"))

            if received_data == 1:  # start_monitoring
                sending_data = 1
            elif received_data == 2:  # stop_monitoring
                sending_data = 2

            self.client_socket.send(sending_data.to_bytes(4, byteorder="little"))

    def stop_connection(self):
        self.client_socket.close()
        self.server_socket.close()


if __name__ == "__main__":
    socketApp = SocketApp(port=int(sys.argv[1]))
    socketApp.start_connection()

    # socketAppUser = SocketApp(port=int(9999))
    # socketAppCamera = SocketApp(port=int(9998))
    # Process(target=socketAppUser.start_connection()).start()
    # Process(target=socketAppCamera.start_connection()).start()