## Problem Statement
최근 들어 자취방 등 혼자 사는 곳에 괴한이 창문을 뚫고 침입하는 범죄가 많이 일어나고 있다. '혼자 사는 사람'들의 '보다 안전한 삶'을 위해, 집을 비울 때에 지속적으로 공간을 모니터링하고 이상 행위를 알려주는 시스템이 필요하다. 회원가입 및 로그인을 하고 카메라 설정을 마치면 모니터링 시스템을 활성화 시킬 수 있다. 모니터링 시스템이 활성화 되면 이상 행위가 감지되었을 때 집 밖에서도 경보 알림을 받을 수 있다. 경보가 울린 후 사용자는 집 안의 상황을 확인할 수 있으며, 이상이 없다고 판단되면 경보를 해제할 수 있다. 이상 행위가 탐지된 이후에도 당시의 상황을 사진 기록으로 확인할 수 있으면 좋을 것이다. 해당 시스템은 간단한 UI로 구성되어 쉽게 조작할 수 있으면 좋겠다.

## Scenarios
- 앱 최초 실행 시 사용자는 회원가입을 진행한다. 
- 아이디와 비밀번호를 입력하여 로그인한 후에, 사용자는 ‘모니터링 시작’과 ‘갤러리’, ‘설정’ 버튼을 볼 수 있다. 
- 사용자는 ‘설정’ 버튼을 눌러 카메라로 사용할 스마트 기기를 세팅할 수 있다.
- 사용자는 ‘설정’ 버튼을 눌러 비밀번호를 변경할 수 있다.
- ‘모니터링 시작’ 버튼 클릭 후, 카메라 연결이 확인되고 나면 사용자는 모니터링을 시작할 수 있다. 
- 모니터링 중 카메라 기기가 꺼지거나 문제가 감지되었을 경우 모니터링이 종료되며 사용자에게 알림이 발송된다.
- 모니터링이 중 움직임이 감지됐을 때, 연동된 휴대폰에 알림음이 울리고 경보 상태로 진입한다. 
- 경보 상태를 해제하기 위해 사용자는 비밀번호를 입력해야 한다.
- 경보 상태에서 감지된 움직임에 대한 사진은 갤러리에 저장된다. 사용자는 이후 언제든지 로그인하여 갤러리에서 기록된 장면들을 볼 수 있다. 
- 사용자는 ‘모니터링 종료’ 버튼을 누르면 비밀번호를 입력하고 모니터링을 마칠 수 있다. 모니터링이 종료되면, 연동된 휴대폰에 알림과 기록을 중지한다.
- 모니터링이 너무 오랜 기간 지속될 경우 모니터링이 종료되며 사용자의 기기로 알림 메시지가 전송된다.

## Subproblems
- **[Motion detection](MotionDetection.md)** : 일정 시간 이상의 움직임 감지
  - 직접적인 행동 (창문에 물리적 힘을 가한다, 현관 잠금 해제를 시도한다, 침입한다) 이전에 일정시간 이상의 예외적인 움직임을 감지하여 범죄 예방 할 방법이 필요하다
- **[History](History.md)** : 이상 행위 감지 시 화면 사진 저장, 저장된 사진 관리
  - 집에 사람이 없어도 보안 시스템이 작동해야 한다, 과거 자료를 필요할 땐 사용할 수 있어야 하고 저장 공간을 정기적으로 확보해야 한다
- **[Admin](Admin.md)** : 활성화/비활성화(특정 시간 비활성화 등), 보안코드 설정, 알림 설정(해지 등) 등의 수동 조작 기능.
  - 시스템이 감지한 움직임을 매뉴얼하게 구별할 필요도 있다 (배달원, 이웃주민, 청소부 등의 모션 감지 시), 문제 파악 시 수동 조작이 편리해야 한다

## Method
Agile

## [Requirement](Requirement.md)
Documentation about requirements.

## [Use Case](UseCase.md)
Documentation about use cases.

## [User Interface Mockups](UserInterfaceMockups.md)
Documentation about user interface mockups.

## [Domain Analysis](DomainAnalysis.md)
Documentation about domain analysis

## [Meeting Records](MeetingRecords.md)
Records of our team meetings.

## Aditional Documents
### Detection
- [Detection Sprint](DetectionSprint.md)
- [Additional Explanation](DetectionExplain.md)

### Admin
- [Admin Sprint](AdminSprint.md)
- [Additional Explanation](AdminExplain.md)

### History
- [History Sprint](HistorySprint.md)
- [Additional Explanation](HistoryExplain.md)
