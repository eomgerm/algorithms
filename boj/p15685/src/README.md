# 드래곤 커브

> 문제 번호 : 15685  
> 출처 : https://www.acmicpc.net/problem/15685

| 메모리 제한 | 시간 제한 |
|--------|-------|
| 512 MB | 1.0 초 |

## 문제 설명

<p>드래곤 커브는 다음과 같은 세 가지 속성으로 이루어져 있으며, 이차원 좌표 평면 위에서 정의된다. 좌표 평면의 x축은&nbsp;→ 방향, y축은&nbsp;↓ 방향이다.</p>
<ol>
 <li>시작 점</li>
 <li>시작 방향</li>
 <li>세대</li>
</ol>
<p>0세대 드래곤 커브는 아래 그림과 같은 길이가 1인 선분이다. 아래 그림은 (0, 0)에서 시작하고, 시작 방향은 오른쪽인 0세대 드래곤 커브이다.</p>
<p style="text-align: center;"><img alt="" src="http://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/1.png" style="width: 191px; height: 50px;"></p>
<p>1세대 드래곤 커브는 0세대 드래곤 커브를 끝 점을 기준으로 시계 방향으로 90도 회전시킨 다음 0세대 드래곤 커브의 끝 점에 붙인 것이다. 끝 점이란 시작 점에서 선분을 타고 이동했을 때, 가장 먼 거리에 있는 점을 의미한다.</p>
<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/2.png" style="width: 210px; height: 170px;"></p>
<p>2세대 드래곤 커브도 1세대를 만든 방법을 이용해서&nbsp;만들 수 있다. (파란색 선분은 새로 추가된 선분을 나타낸다)</p>
<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/3.png" style="width: 220px; height: 285px;"></p>
<p>3세대 드래곤 커브도 2세대 드래곤 커브를 이용해 만들 수 있다. 아래 그림은 3세대 드래곤 커브이다.</p>
<p style="text-align: center;"><img alt="" src="https://onlinejudgeimages.s3-ap-northeast-1.amazonaws.com/problem/15685/4.png" style="width: 390px; height: 285px;"></p>
<p>즉, K(K &gt; 1)세대 드래곤 커브는 K-1세대 드래곤 커브를&nbsp;끝 점을 기준으로 90도 시계 방향 회전 시킨 다음, 그것을 끝 점에&nbsp;붙인 것이다.</p>
<p>크기가 100×100인 격자 위에 드래곤 커브가 N개 있다. 이때, 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수를 구하는 프로그램을 작성하시오. 격자의 좌표는 (x, y)로 나타내며, 0 ≤ x ≤ 100, 0 ≤ y ≤ 100만 유효한 좌표이다.</p>

