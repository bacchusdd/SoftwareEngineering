# Domain Analysis

## for Use Case 1, 2, 3, 4

### Concept Definitions

### Association Definitions

### Attribute Definitions

## for Use Case 5, 6, 7, 9, 10, 11

### Concept Definitions

![Domain_Model_Monitoring](image/Domain_Model_Monitoring.png)

Responsibility Description | Type | Concept Name
---------------------------|:----:|:-----------:
전반적인 모든 concept들의 행동을 제어하고 다른 concept들에게 행동을 위임한다.| D | Controller
사용자가 명시적으로 모니터링 시작과 중지할 수 있도록 한다. 일정시간이 지나면 모니터링을 중지하도록 한다. 움직임이 감지되면 사진을 촬영한다. | D | Motion Detector
움직임이 감지돼서 경보 상태에 진입하게 되면 카메라 기기에서 알람을 울린다. 경보 알림 팝업을 통해 사용자는 경보를 해제할 수 있도록 한다. | D | Alarm Operator
움직임이 감지되면 촬영된 사진을 날짜와 함께 Database/repository에 저장한다. | D | Photo Saver
움직임이 감지돼서 경보 상태에 진입하게 되면 사용자 기기에게 알림을 전송한다. | D | Notifier
사용자의 로그인 정보에 따른 카메라 등록 정보, 설정 값, 갤러리를 저장. | K | Database

### Association Definitions
Concept Pair | Association Description | Association Name
:-----------:|-------------------------|:---------------:
Motion Detector ↔ Controller | Motion Detector는 움직임을 감지하면 Controller로 촬영한 사진을 보낸다. | give detected info
Controller ↔ Alarm Operator | Controller는 경보 상태가 되면 Alarm Operator에게 알람을 울리라고 지시한다. | make alarm
Controller ↔ Notifier | Controller는 경보 상태가 되면 Notifier에게 감지 정보를 전달하고 사용자에게 알림을 전송하도록 지시한다. | give detect info
Controller ↔ Photo Saver | Controller는 경보 상태가 되면 Photo Saver에게 촬영한 사진을 전달하고 Database에 저장하도록 지시한다. | give photo
Photo Saver ↔ Database | Photo Saver는 현재 날짜 및 시각을 구해서 촬영된 사진과 함께 Database에 저장한다. | save photo with date

### Attribute Definitions
<table>
  <tr>
    <th>Concept</th>
    <th>Attributes</th>
    <th>Attribute Description</th>
  <tr>
    <td rowspan="3">Motion Detector</td>
    <td>startMonitoring</td>
    <td>모니터링을 시작</td>
  </tr>
  <tr>
    <td>stopMonitoring</td>
    <td>모니터링을 중지</td>
  </tr>
  <tr>
    <td>takePhoto</td>
    <td>움직임이 감지되었을 때 사진을 촬영함</td>
  </tr>
  <tr>
    <td>Controller</td>
    <td>isAlert</td>
    <td>현재 상태가 경보 상태인지 아닌지를 저장함</td>
  </tr>
  <tr>
    <td>Alarm Operator</td>
    <td>stopAlarm</td>
    <td>(사용자에게 도착한 알림 팝업을 통해)카메라 기기의 알람을 종료</td>
  </tr>
  <tr>
    <td>Notifier</td>
    <td>socket</td>
    <td>경보 상태일 때 사용자 기기로 알림을 전송하도록 하는 소켓</td>
  </tr>
  <tr>
    <td>Photo Saver</td>
    <td>getDate</td>
    <td>사진을 저장하기 위해 현재 날짜 및 시간을 구함</td>
  </tr>
  <tr>
    <td rowspan="2">Database</td>
    <td>detectedImages</td>
    <td>움직임이 감지됐을 당시에 촬영된 사진</td>
  </tr>
  <tr>
    <td>timeStamp</td>
    <td>움직임이 감지됐을 당시에 기록된 날짜 및 시간</td>
  </tr>
</table>

## for Use Case 8
