# Admin Subgroup Log

## Product Backlog

| Backlog item                                                 | Estimate |
| ------------------------------------------------------------ | -------- |
| 사용자로서, 나는 회원가입을 할 수 있다.                         | 2        |
| 사용자로서, 나는 아이디와 비밀번호를 입력하여 로그인 할 수 있다.  | 2        |
| 사용자로서, 나는 카메라로 사용할 스마트 기기를 설정할 수 있다.	  | 4       |
| 사용자로서, 나는 비밀번호를 변경할 수 있다.                     | 2        |

## 1st Sprint (5.27~30)

- 사용자로서, 나는 회원가입을 할 수 있다.
- 사용자로서, 나는 아이디와 비밀번호를 입력하여 로그인 할 수 있다.
- 사용자로서, 나는 비밀번호를 변경할 수 있다.

### Sprint Goal

회원가입, 로그인, 비밀번호 변경 기능 구현

### Sprint Backlog

| Tasks                                                   |
| ------------------------------------------------------- |
| 서버와 클라이언트 간 통신 함수 인터페이스 구축             |
| DB 관련 함수 인터페이스 구축                             |
| Login 함수 구현                                         | 
| Register 함수 구현                                      | 
| ChangePassword 함수 구현                                |
| UI 제작                                                 |

## Daily Scrum

1. 어젠 뭘 하셨나요?
2. 오늘은 뭘 하실건가요?
3. is anything in your way?

### 2021-05-27 Thu

상엽

1. main Activity implementation
2. Home, Setting Activity implementation
3. main Activity 에서 로그인 정보(id, password)를 flask 서버로 넘겨주는 방법을 모르겠음.

주휘

1. 없음 (26일)
2. setup a development environment
3. activity - xml (loginsetting, register)

### 2021-05-28 FRI

상엽

1. Home, Setting Activity implementation
2. Implement communication between client and server
3. X

주휘

1. setup a development environment
2. Register Activity implementation
3. 서버 단에서 response.body()에 status 변수 추가, 설정

### 2021-05-29 SAT

상엽

1. Implement communication between client and server
2. Implement communication between server and database, change_password(), and delete_account().
3. X

주휘

1.
2.
3.
