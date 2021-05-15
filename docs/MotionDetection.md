# Motion Detection

## Class diagrams

<details>
<summary>UC-5 StartMonitoring & UC-6 StopMonitoring class diagram</summary>
</br>

![class_UC5_6](diagram/UC-5_6_class.jpg)

</details>

<details>
<summary>UC-7 StopAlarm class diagram</summary>
</br>

![class_UC_7](diagram/UC-7_stop_alarm_class.jpg)

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

![sdUC-5](diagram/UC-9_MotionDetection_0.png)
![sdUC-5](diagram/UC-9_MotionDetection_1.png)

</details>

<details>
<summary>UC-10 TakePhoto sequence diagram</summary>
</br>

![sdUC-5](diagram/UC-10_TakePhoto_0.png)
![sdUC-5](diagram/UC-10_TakePhoto_1.png)
![sdUC-5](diagram/UC-10_TakePhoto_2.png)

</details>

<details>
<summary>UC-11 SendAlert sequence diagram</summary>
</br>

![sdUC-5](diagram/UC-11_SendAlert_0.png)
![sdUC-5](diagram/UC-11_SendAlert_1.png)

</details>

<details>
<summary>sequence diagram trials</summary>
</br>

이전 커밋의 UC-5 sequence diagram. MotionDetector가 camera를 시작하는 동시에 user에게 카메라의 시작을 알려주기도 했으나 MotionDetector는 user에게 알려주는 기능까지 할 필요없다고 판단, 높아지는 coupling을 감수하고 더 나은 cohesion을 얻도록 수정된 diagram에서는 Notifier에게 알려주는 기능을 위임함.

![trial_UC5](diagram/UC-5_start_monitoring_sequence_trial.jpg)

이전 커밋의 UC-6 sequence diagram. UC-5와 마찬가지로 MotionDetector에서 카메라를 종료하면 user에게 알려주는 기능을 Notifier에게 위임하도록 수정함.

![trial_UC6](diagram/UC-6_stop_monitoring_sequence_trial.jpg)

이전 커밋의 UC-7 sequence diagram.

![trial_UC7](diagram/UC-7_stop_alarm_sequence_trial.jpg)

</details>
