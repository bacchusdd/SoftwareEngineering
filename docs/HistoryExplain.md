# Additional Explain for History Branch

## 실행 전 안내 사항

- Admin과 연동되지 않았기 때문에, 임의로 user id를 지정함. user id는 'kang'으로 지정.
- (Server) AppServer file에서 'HistoryAction.py' 파일을 실행시킨 후 사용.
- (Client) AndroidStudio에서 AppClient file 전체를 실행시키면 Gallery 화면부터 실행.

## 구현된 기능

- 서버를 통해 db 내에 저장된 사용자의 날짜 목록을 불러옴.
- 목록에서 날짜 선택 시, db 내에 저장된, 해당 날짜에 찍힌 사진을 목록으로 불러옴
- 목록에서 사진 선택 시, 해당 사진을 원본 사이즈로 보여주며, 해당 사진을 삭제할 수 있음.
- 사진 삭제를 누르면, db에서 해당 사진이 삭제됨.

## 프로그램 구조

- 서버는 Flask를 사용하며 데이터베이스는 Firebase를 사용하며 서버와 클라이언트는 Json을 이용하여 통신

## 실행화면

- 클라이언트를 실행하면 갤러리 메뉴가 뜨며 기록된 날짜들이 뜸
  </br>![갤러리](/docs/image/history_execution_gallery.png)
- 날짜를 클릭하면 해당 날짜의 기록된 모니터링 사진이 뜸
  </br>![날짜별포토리스트](/docs/image/history_execution_photolist.png)
- 사진을 클릭하면 크케 볼 수 있고 삭제할 수 있음
  </br>![크게보기](/docs/image/history_execution_bigimage.png) ![삭제한뒤](/docs/image/history_execution_photolist_delete.png)

- 사진 삭제 한 뒤 데이터베이스 비교
  </br>![삭제전](/docs/image/history_database.PNG) ![삭제후](/docs/image/history_database_delete.PNG)
