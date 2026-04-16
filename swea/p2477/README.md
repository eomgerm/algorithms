# 차량 정비소

> 문제 번호 : 2477

| 메모리 제한 | 시간 제한 (초) |
|--------|-------|
| 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내 | 3 |

## 문제 설명

<p class="txt">
							<strong>※ SW Expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.</strong><br>
<br>
<br>
고객이 차량 정비소에 지갑을 두고 갔다.<br>
<br>
차량 정비소에서 근무하는 이 대리는 고객에게 지갑을 돌려주려고 한다.<br>
<br>
하지만, 차량 정비소를 방문한 고객이 너무 많아 모두 전화하여 확인하기가 불가능하다.<br>
<br>
다행히 지갑에는 고객만족도 설문지가 있다.<br>
<br>
고객만족도 설문지에는 고객이 이용했던 접수 창구번호와 정비 창구번호가 있다.<br>
<br>
이 대리는 이 정보를 이용하여 전화로 확인할 고객을 정하려고 한다.<br>
<br>
차량 정비소에는 N개의 접수 창구와 M개의 정비 창구가 있다.<br>
<br>
접수 창구는 1부터 N까지 번호가 붙어 있다. 정비 창구도 1부터 M까지 번호가 붙어 있다.<br>
<br>
차량 정비소에는 <strong>[Fig. 1]</strong>와 같이 두 단계를 거쳐 고객의 차량을 정비한다.<br>
<br>
첫 단계는 접수 창구에서 고장을 접수하는 것이고, 두 번째 단계는 정비 창구에서 차량을 정비하는 것이다.<br>
&nbsp;
</p><div bis_skin_checked="1"><img alt="" src="/main/common/fileDownload.do?downloadType=CKEditorImages&amp;fileId=AV6c7kpKIusDFAXy"><br>
<span><strong>[Fig. 1]</strong></span></div>
<br>
차량 정비가 끝난 고객은 고객만족도 조사를 위해 고객만족도 설문지를 받는다.<br>
<br>
접수 창구 및 정비 창구의 담당자 업무 능력이 달라서 담당자 별 처리 시간이 다르다.<br>
<br>
한 담당자의 처리 시간은 고객과 고장의 종류에 상관 없이 항상 같다.<br>
<br>
접수 창구 i에서 고객 한 명의 고장을 접수하는 데 걸리는 처리 시간은 ai이다. (1 ≤ i ≤ N)<br>
<br>
정비 창구 j에서 고객 한 명의 차량을 정비하는 데 걸리는 처리 시간은 bj이다. (1 ≤ j ≤ M)<br>
<br>
지금까지 차량 정비소를 방문한 고객은 K명이다.<br>
<br>
고객은 도착하는 대로 1번부터 고객번호를 부여 받는다.<br>
<br>
고객번호 k의 고객이 차량 정비소에 도착하는 시간은 tk이다. (1 ≤ k ≤ K)<br>
<br>
고객이 차량 정비소에 도착하면, 빈 접수 창구가 있는 경우 빈 접수 창구에 가서 고장을 접수한다.<br>
<br>
빈 접수 창구가 없는 경우 빈 접수 창구가 생길 때까지 기다린다.<br>
<br>
고장 접수를 완료하면 정비 창구로 이동한다.<br>
<br>
빈 정비 창구가 있는 경우 빈 정비 창구에 가서 차량을 정비 받는다.<br>
<br>
빈 정비 창구가 없는 경우 빈 정비 창구가 생길 때까지 기다린다.<br>
<br>
접수 창구의 우선순위는 아래와 같다.<br>
<br>
&nbsp;&nbsp; ① 여러 고객이 기다리고 있는 경우 고객번호가 낮은 순서대로 우선 접수한다.<br>
&nbsp;&nbsp; ② 빈 창구가 여러 곳인 경우 접수 창구번호가 작은 곳으로 간다.<br>
<br>
<br>
정비 창구의 우선순위는 아래와 같다.<br>
<br>
&nbsp;&nbsp; ① 먼저 기다리는 고객이 우선한다.<br>
&nbsp;&nbsp; ② 두 명 이상의 고객들이 접수 창구에서 동시에 접수를 완료하고 정비 창구로 이동한 경우, 이용했던 접수 창구번호가 작은 고객이 우선한다.<br>
&nbsp;&nbsp; ③ 빈 창구가 여러 곳인 경우 정비 창구번호가 작은 곳으로 간다.<br>
<br>
<br>
고객이 차량 정비소에 도착하여 접수 창구로 가는 시간 또는 접수 창구에서 정비 창구로 이동하는 시간은 무시할 수 있다. 즉, 이동 시간은 0이다.<br>
<br>
고객의 도착 시간 tk, 접수 창구의 처리 시간 ai, 정비 창구의 처리 시간 bj가 주어졌을 때, 지갑을 분실한 고객과 같은 접수 창구와 같은 정비 창구를 이용한 고객의 고객번호들을 찾아 그 합을 출력하는 프로그램을 작성하라.<br>
<br>
만약, 그런 고객이 없는 경우 -1을 출력한다.<br>
<br>
<br>
<strong>[예시]</strong><br>
<br>
<strong>[Fig. 2]</strong>과 같이 접수 창구가 2개, 정비 창구가 2개가 있다고 하자. (N = 2, M = 2)<br>
&nbsp;
<div bis_skin_checked="1"><img alt="" src="/main/common/fileDownload.do?downloadType=CKEditorImages&amp;fileId=AV6c73UqIuwDFAXy"><br>
<span><strong>[Fig. 2]</strong></span></div>
<br>
<br>
접수 창구의 처리 시간 ai와 정비 창구의 처리 시간 bj는<strong> [Table 1]</strong>과 같이 주어진다.<br>
<br>
&nbsp;
<div bis_skin_checked="1"><img alt="" src="/main/common/fileDownload.do?downloadType=CKEditorImages&amp;fileId=AV6dBVU6IxwDFAXy"><br>
<span><strong>[Table 1]</strong></span></div>
<br>
<br>
지금까지 고객 6명이 이용했다. (K = 6)<br>
<br>
고객 k의 도착 시간 tk는 <strong>[Table 2]</strong>와 같이 주어진다.<br>
<br>
&nbsp;
<div bis_skin_checked="1"><img alt="" src="/main/common/fileDownload.do?downloadType=CKEditorImages&amp;fileId=AV6dBglaIx0DFAXy"><br>
<span><strong>[Table 2]</strong></span></div>
<br>
<br>
지갑을 분실한 고객은 접수 창구 1과 정비 창구 2를 이용했다고 한다.<br>
<br>
이 대리는 지갑을 분실한 고객과 같은 접수 창구 1과 정비 창구 2를 이용한 고객들의 고객번호를 알고 싶어 한다.<br>
<br>
시간 별 고객들의 이동은 <strong>[Fig. 3]</strong>와 같다.

<div bis_skin_checked="1"><img alt="" src="/main/common/fileDownload.do?downloadType=CKEditorImages&amp;fileId=AV6c8FtKIu0DFAXy"><br>
<span><strong>[Fig. 3]</strong></span></div>
&nbsp;<br>
<br>
접수 창구 1과 정비 창구 2를 이용한 고객의 고객번호는 1과 6이다.<br>
<br>
따라서, 정답은 1 + 6 = 7이다.<br>
<br>
<br>
<strong>[제약사항]</strong><br>
<br>
1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 3초<br>
<br>
2. 접수 창구의 개수 N과 정비 창구의 개수 M은 1 이상 9 이하의 정수이다. (1 ≤ N, M ≤ 9)<br>
<br>
3. 접수 창구 i에서 고장을 접수하는 처리 시간 ai는 1 이상 20 이하의 정수이다. (1 ≤ ai ≤ 20)<br>
<br>
4. 정비 창구 j에서 차량을 정비하는 처리 시간 bj는 1 이상 20 이하의 정수이다. (1 ≤ bj ≤ 20)<br>
<br>
5. 차량 정비소를 방문한 고객의 수 K는 2 이상 1,000 이하의 정수이다. (2 ≤ K ≤ 1,000)<br>
<br>
6. 고객 k의 도착 시간 tk는 0 이상 1,000 이하의 정수이다. (0 ≤ tk ≤ 1,000)<br>
<br>
7. 지갑을 두고 간 고객이 이용한 접수 창구번호 A는 1 이상 N 이하의 정수이다. (1 ≤ A ≤ N)<br>
<br>
8. 지갑을 두고 간 고객이 이용한 정비 창구번호 B는 1 이상 M 이하의 정수이다. (1 ≤ B ≤ M)<br>
<br>
9. 창구번호와 고객번호는 1부터 시작한다.<br>
<br>
<br>
<strong>[입력]</strong><br>
<br>
입력의 맨 첫 줄에는 총 테스트 케이스의 개수 T가 주어지고, 그 다음 줄부터 T개의 테스트 케이스가 주어진다.<br>
<br>
각 테스트 케이스의 첫 번째 줄에는 접수 창구의 개수 N, 정비 창구의 개수 M, 차량 정비소를 방문한 고객의 수 K, 지갑을 두고 간 고객이 이용한 접수 창구번호 A와 정비 창구번호 B가 주어진다.<br>
<br>
두 번째 줄에는 i번째 접수 창구가 고장을 접수하는 데 걸리는 시간 ai가 N개 주어진다.<br>
<br>
세 번째 줄에는 j번째 정비 창구가 차량을 정비하는 데 걸리는 시간 bj가 M개 주어진다.<br>
<br>
네 번째 줄에는 k번째 고객이 차량 정비소를 방문하는 시간 tk가 순서대로 K개 주어진다.<br>
<br>
<br>
<strong>[출력]</strong><br>
<br>
테스트 케이스의 개수만큼 T줄에 T개의 테스트 케이스 각각에 대한 답을 출력한다.<br>
<br>
각 줄은 "#x"로 시작하고 공백을 하나 둔 다음 정답을 출력한다. (x는 1부터 시작하는 테스트 케이스의 번호이다)<br>
<br>
출력해야 할 정답은 지갑을 두고 간 고객과 같은 접수 창구 A와 같은 정비 창구 B를 이용한 고객들의 고객번호의 합이다. 만약 그런 고객이 없는 경우 정답으로 -1을 출력한다.
							<p></p>
