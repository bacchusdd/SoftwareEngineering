# History

## Class Diagram
<details>
<summary>UC-8 class diagram</summary>
</br>

![class_diagram](diagram/history_class_dg.png)

</br>
</details>


<details>
<summary>sequence diagram trials</summary>
</br>

![class_diagram_trial](diagram/history_class_trial.jpg)

</br>
</details>


## Object Sequence Diagram

<details>
<summary>UC-8 sequence diagram</summary>
</br>

![sequence_diagram](diagram/history_sequence_diagram_1.jpg)

</br>

</details>


<details>
<summary>sequence diagram trials</summary>
</br>
첫번째 설계는 Database가 Display History를 직접 call하는 설계이며 두번째 설계는 Database가 결과로 나온 history 데이터를 Controller에게 return 한 뒤 Controller가 Display History를 call하는 방식이다.
</br></br>
Database가 Display History를 call하는 것은 Database의 기능에 맞지 않다고 판단하여 두번째 설계로 결정하였다.
</br></br>

![sequence_trials](diagram/history_sequence_trial.jpg)


</details>
