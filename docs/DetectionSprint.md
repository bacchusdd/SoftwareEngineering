# Motion Detection Subgroup Log

## Product Backlog

| Backlog item                                                                  | Estimate |
| ----------------------------------------------------------------------------- | -------- |
| 사용자로서, 나는 카메라를 이용하여 모니터링을 시작할 수 있다.                 | 2        |
| 사용자로서, 나는 원격으로 모니터링을 종료할 수 있다.                          | 2        |
| 사용자로서, 나는 경보가 울리면 비밀번호를 입력하여 해제할 수 있다.            | 2        |
| 인가되지 않은 사용자로서, 나는 모니터링을 강제로 끌 수 없다.                  | 1        |
| 기기는 카메라에 움직임이 감지되면 사진을 저장하고 사용자에게 알림을 전송한다. | 4        |

## 1st Sprint (5.27~30)

- 사용자로서, 나는 카메라를 이용하여 모니터링을 시작할 수 있다.
- 기기는 카메라에 움직임이 감지되면 사진을 저장하고 사용자에게 알림을 전송한다.

### Sprint Goal

모니터링의 시작 및 실행, 이상 감지 시 사용자에게 알림을 전송하는 기능의 구현

### Sprint Backlog

| Tasks                                                                 | Thu | Fri | Sat | Sun |
| --------------------------------------------------------------------- | --- | --- | --- | --- |
| 서버와 클라이언트 간 모니터링 함수 인터페이스 구축                    | 4   |     |     |     |
| DB 관련 함수 인터페이스 구축                                          | 3   |     |     |     |
| Motion Detection 알고리즘 선정 및 디텍션 함수 구현                    |     | 4   | 2   |     |
| Save Photo 함수 구현                                                  |     | 3   |     |     |
| Send Alert 함수 구현                                                  |     | 2   |     |     |
| 카메라 기기와 사용자 기기 사이 연결 (Camera Setting 구현)             | 3   |     |     |     |
| 사용자 기기에서 원격으로 카메라 시작 함수 구현 (Motion Detector 구현) | 2   |     |     |     |
| UI 제작                                                               | 2   |     |     | 2   |
| Test 코드 작성 및 테스트                                              |     |     | 4   |     |

## Daily Scrum

1. 어젠 뭘 하셨나요?
2. 오늘은 뭘 하실건가요?
3. is anything in your way?

### 2021-05-27 Thu

하윤

1. 안했음
2. 함수 인터페이스들 완성해서 실행되는 앱인것 마냥 흉내내기
3. 아직 없어요!

한울

1. 안했음
2. 카메라 원격 연결 구현
3. 아직은.. 없음!

### 2021-05-28 Fri

하윤

1. 함수 틀 제작
2. 합쳐서 제대로 돌아가도록!..
3. 디텍션 알고리즘을 뭘 써야 할지 고민됨

한울

1. 카메라 모듈을 CamreaX 라이브러리로 교체, CameraSetting, MotionDetector 클래스 완성
2. 클라이언트(사용자 기기, 카메라 기기)와 서버간 연동
3. 연동을 위해서 플라스크 알기..??

논의한 것

- 클래스 몇개에 싱글턴 패턴을 적용해야할지 의문이 있었음 -> 안하기로
- 디텍션 알고리즘 결정
- 디텍션 사진분석을 서버에서 하기로 했었는데 클라이언트에서 직접 수행하기로

### 2021-05-29 Sat

하윤

1. 서버 클라이언트 사이 통신
2. 서버 클라이언트 사이 통신 마무리, savePhoto 구현, 테스트 코드 작성
3. 에뮬레이터 두개를 각각 서버와 어떻게 통신시켜야 할지

한울

1. 클라이언트 카메라 권한 확인 코드 작성, CameraSetting, MotionDetector 클래스 테스트
2. 디텍션 알고리즘 완성, 카메라 기기에서 서버로 디텍팅 정보 보내기
3. 어떻게 서버로 보낼지 고민됨.

논의한 것

- 서로 지금까지 진행상황
- 어떻게 클라이언트와 서버가 통신할 것인가?


### Daily Scrum 5.30 Sun

하윤
1. 서버 클라이언트 사이 통신, 소켓 시도
2. Retrofit 말고 소켓과 파이어베이스 RPC 시도해보기로
3. 네트워크 통신이 어렵다...


한울
1. CameraSetting, MotionDetector 클래스 버그 고치기.
2. MotionDetector 클래스에서 모션 감지되면 Retrofit으로 REST API형식으로 서버(플라스크)로 보내고 파이어베이스 Storage에 사진 load까지
3. Retrofit이 어렵다.

논의한 것
- 양측 클라이언트 간의 통신 진행상황


### Daily Scrum 5.31 Mon

하윤
1. 서버와 에뮬레이터 사이 통신을 소켓으로 시도
2. 전반적인 마무리
3. 네트워크는 정말 어렵다.


한울
1. 모션감지시 Retrofit으로 서버에 사진 보낸 후 서버에서 파이어베이스 Storage에 사진 적재까지 구현
2. 사용자, 카메라 클라이언트 완성
3. 여전히 Retrofit은 어렵다.

논의한 것
- 발표는 어떻게 할 것인가?
- 어떻게 완성할 것인가??
