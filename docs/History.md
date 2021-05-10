# History

## Class Diagram
<details>
<summary>UC-8 class diagram</summary>
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
첫 번째는 Database가 Display History를 직접 call 하는 설계이며 두번째는 Database가 결과로 나온 history를 Controller에게 return 한 뒤 Controller가 Display History를 call하는 방식이다.
</br>
Database가 Display History를 call하는 것은 Database의 기능에 맞지 않다고 판단하여 두번째 설계로 결정하였다.
</br>

![sequence_trials](diagram/history_sequence_trial.jpg)


</details>
