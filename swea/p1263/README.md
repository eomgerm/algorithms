# SW Expert Academy

> 문제 번호 : 1263

| 메모리 제한 | 시간 제한 (초) |
|--------|-------|
| 힙, 정적 메모리 합쳐서 256MB 이내, 스택 메모리 1MB 이내 | 20 |

## 문제 설명

<p class="txt">
							<span style="color:#000000">※ SW Expert 아카데미의 문제를 무단 복제하는 것을 금지합니다.<br>
<br>
어떤 사화과학 연구 단체에서 사람의 네트워크에 대하여 여러 가지 측정 방법을 사용하여 연구하기로 하였다.<br>
<br>
이를 위해 우선 주어진 사람 네트워크에서 누가 가장 영향력이 있는 사람인지를 알 수 있는 척도로서 다음을 분석하는 프로그램을 작성하시오.<br>
<br>
단, N은 입력 사람 네트워크 (그래프)의 노드 수이다.<br>
<br>
Closeness Centrality(CC):Closeness는 네트워크 상에서 한 사용자가 다른 모든 사람에게 얼마나 가까운가를 나타낸다.<br>
<br>
따라서 사용자 i의 CC(i)는 다음과 같이 계산된다.<br>
<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; CC(i) = ∑ j dist(i,j) 단, dist(i,j)는 노드i로부터 노드 j까지의 최단 거리이다.<br>
<br>
예제 1)</span><br>
&nbsp;
</p><div style="text-align:center" bis_skin_checked="1"><span style="color:#000000"><img alt="" src="/main/common/fileDownload.do?downloadType=CKEditorImages&amp;fileId=AV2XjYN6DmcBBASl" style="height:300px; width:512px"></span></div>
<br>
<span style="color:#000000">위의 예제는 사람 네트워크에서 사용자 2의 dist합이 가장 작으며, CC(2) = 4임을 통해 사용자 2가 모든 다른 사용자들로부터 가장 가까운 사용자이다.<br>
<br>
예제 2)</span><br>
&nbsp;
<div style="text-align:center" bis_skin_checked="1"><span style="color:#000000"><img alt="" src="/main/common/fileDownload.do?downloadType=CKEditorImages&amp;fileId=AV2Xjbx6DmgBBASl" style="height:296px; width:548px"></span></div>
<br>
<span style="color:#000000">위의 예제는 사람 네트워크에서 사용자 3의 dist합이 가장 작으며, CC(3) = 5임을 통해 사용자 3이 모든 다른 사용자들로부터 가장 가까운 사용자이다.<br>
&nbsp;<br>
<strong>[제약 사항]</strong><br>
<br>
입력으로 주어지는 사람 네트워크에서 노드 자신을 연결하는 간선은 없다.<br>
<br>
또한 사람 네트워크는 하나의 연결 요소(connected component)로 구성되어 있다.<br>
<br>
단, 사람 네트워크의 최대 사용자 수는 1,000 이하이다.<br>
<br>
테스트 케이스들은 아래의 그룹들로 이루어져 있다.<br>
그룹 1 싸이클이 없고 N &lt;= 10 인 경우<br>
그룹 2 싸이클이 없고 10 &lt; N &lt;= 100 인경우<br>
그룹 3 싸이클이 있고 N&lt;= 10<br>
그룹 4 싸이클이 있고10 &lt; N &lt;= 100<br>
그룹 5 모든 경우가 존재하고 100 &lt; N &lt;= 1000<br>
<br>
<strong>[입력]</strong><br>
<br>
맨 위의 줄에는 전체 테스트 케이스의 수 T가 주어진다.<br>
<br>
그 다음 줄부터 T개의 테스트 케이스가 주어진다.<br>
<br>
각 테스트 케이스는 한 줄에 주어지며, 사람 수인 양의 정수 N이 주어진 다음, 사람 네트워크의 인접 행렬이 행 우선 (row-by-row) 순으로 주어진다.<br>
<br>
단, 각 숫자 사이에는 1개의 공백이 주어진다.<br>
<br>
<strong>[출력]</strong><br>
<br>
총 T줄에 T개의 테스트 케이스 각각에 대한 답을 한 줄에 출력한다.<br>
<br>
각 줄은 ‘#x’로 시작하고 공백을 하나 둔 다음, 각 테스트 케이스에 주어진 사람 그래프에서 사람들의 CC 값들 중에서 최솟값을 한 줄에 출력한다.</span>
							<p></p>
