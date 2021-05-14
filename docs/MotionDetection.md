# Motion Detection

## Class diagrams

<details>
<summary>UC-5 StartMonitoring class diagram</summary>
</br>

</details>

<details>
<summary>UC-6 StopMonitoring class diagram</summary>
</br>

</details>

<details>
<summary>UC-7 StopAlarm class diagram</summary>
</br>

</details>

<details>
<summary>UC-9 DetectMotion class diagram</summary>
</br>

</details>

<details>
<summary>UC-10 TakePhoto class diagram</summary>
</br>

</details>

<details>
<summary>UC-11 SendAlert class diagram</summary>
</br>

</details>

## Sequence diagrams

<details>
<summary>UC-5 StartMonitoring sequence diagram</summary>
</br>

![sdUC-5](diagram/UC-5_start_monitoring_sequence.jpg)

</details>

<details>
<summary>UC-6 StopMonitoring sequence diagram</summary>
</br>

![sdUC-6](diagram/UC-6_stop_monitoring_sequence.jpg)

</details>

<details>
<summary>UC-7 StopAlarm sequence diagram</summary>
</br>

![sdUC-7](diagram/UC-7_stop_alarm_sequence.jpg)

</details>

<details>
<summary>UC-9 DetectMotion sequence diagram</summary>
</br>

</details>

<details>
<summary>UC-10 TakePhoto sequence diagram</summary>
</br>

</details>

<details>
<summary>UC-11 SendAlert sequence diagram</summary>
</br>

</details>

<details>
<summary>sequence diagram trials</summary>
</br>

아래는 이전 커밋의 UC-5 sequence diagram 사진. MotionDetector가 camera를 시작하는 동시에 user에게 카메라의 시작을 알려주기도 했으나 MotionDetector는 user에게 알려주는 기능까지 할 필요없다고 판단, 높아지는 coupling을 감수하고 더 나은 cohesion을 얻도록 수정된 diagram에서는 Notifier에게 알려주는 기능을 위임함.

![trial_UC5](diagram/UC-5_start_monitoring_sequence_trial.jpg)

</details>
