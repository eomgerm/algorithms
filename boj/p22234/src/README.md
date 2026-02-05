# 가희와 은행

> 문제 번호 : 22234  
> 출처 : https://www.acmicpc.net/problem/22234

| 메모리 제한 | 시간 제한 |
|--------|-------|
| 512 MB | 1.5 초 |

## 문제 설명

<p>가희는 창구가 하나인 은행을 운영하고 있습니다. 가희의 은행이 영업을 시작했을 때,&nbsp;대기 줄에는 손님이&nbsp;<em>N</em>명&nbsp;있습니다.</p>
<p>&nbsp;</p>
<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/55e4fafb-f33c-4d0d-b275-dd9728c1ad7f/-/preview/"></p>
<p style="text-align: center;"><strong>[그림 1] 카운터 직원과 <em>N</em>명의 손님</strong></p>
<p><em>x</em>번 손님에 대한 정보는 <em>x</em>번 손님의&nbsp;<em>id</em> 값인 <em>P<sub>x</sub></em>와 업무를 처리하는 데 필요한 시간인 <em>t<sub>x</sub></em>초로 정보가 주어지게 됩니다.</p>
<p>은행이 영업을 시작하고 난 후에 들어오는 손님은&nbsp;<em>M</em>명 있습니다. 이 손님들은 입력을 받은 순서대로 각각 <em>N+1</em>, <em>N+2</em>, ..., <em>N+M</em>번 손님이 됩니다.</p>
<p>이 손님들에 대한 정보는 <em>x</em>번 손님의&nbsp;<em>id</em> 값인 <em>P<sub>x</sub></em>와 업무를 처리하는 데 필요한 시간인 <em>t<sub>x</sub></em>초, 영업 시작 <em>c<sub>x</sub></em>초 후에 들어왔다는&nbsp;정보가 주어지게 됩니다.</p>
<p>손님은&nbsp;은행에 들어옴과 동시에, 대기 큐의 맨 뒤에 서게 됩니다. N+1번 손님이 은행을 영업을 시작하고&nbsp;c<sub>N+1</sub>초 후에 들어왔다고 생각해 보겠습니다.</p>
<p>&nbsp;</p>
<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/33a3c56a-04b0-4119-bef6-c2dbea337ba4/-/preview/"></p>
<p style="text-align: center;"><strong>[그림 2] 은행이 영업을 시작하고 c<sub>N+1</sub>초 후 상황</strong></p>
<p>N+1번 손님은 은행에 들어오자 마자 대기 큐의 맨 뒤에 줄을 서게 되므로, 영업을 시작하고 <em>c<sub>N+1</sub></em>초 후에 대기 큐의 상태는 위와 같습니다.</p>
<p>창구에 있는 직원과 고객들은 아래와 같은 알고리즘으로 업무를&nbsp;처리합니다.</p>
<ol>
 <li>대기 큐의 맨 앞에 있는 고객이 <em>x</em>번 손님이라고 하면, 창구에 있는 직원은 
  <ul>
   <li><em>t<sub>x</sub></em>가 <em>T</em>보다 크다면, <em>x</em>번 손님의&nbsp;업무를 <em>T</em>초동안 처리합니다. 그 후, <em>x</em>번 손님의 업무가 끝나는 데 필요한 시간인 <em>t<sub>x</sub></em>는&nbsp;<em>T</em>만큼 감소합니다.</li>
   <li>그렇지 않으면, <em>x</em>번 손님의 업무를 <em>t<sub>x</sub></em>초 동안 처리합니다. 이후에, <em>x</em>번 손님의 업무가 끝나는 데 필요한 시간인 <em>t<sub>x</sub></em>는 은 0이 됩니다.</li>
  </ul></li>
 <li>대기 큐의 맨 앞에 있는 고객인 <em>x</em>번 손님은 
  <ul>
   <li>업무가 끝나는 데 필요한 시간인 <em>t<sub>x</sub></em>가&nbsp;0이 되었다면, 은행 바깥으로 나가게 됩니다.</li>
   <li>그렇지 않으면 대기 큐의 맨 뒤로 이동하게 됩니다. 만약에 이 때 도착한 손님이 있다면, 도착한 손님 뒤로 가게 됩니다.</li>
  </ul></li>
 <li>대기 큐에 고객이 남았다면 1로 돌아갑니다.</li>
</ol>
<p>은행이&nbsp;영업을 시작할&nbsp;때 부터 창구에 있는 직원은 일을 시작합니다.</p>
<p>은행이 영업을 시작한 시점으로부터 0초가 지났을 때 부터 <em>W-1</em>초가 지날&nbsp;때 까지 창구에 있는 직원이&nbsp;어떤 고객의 업무를 처리하는지 알려주세요.</p>

