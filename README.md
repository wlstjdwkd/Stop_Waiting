# 스마트 기기를 이용한 교내 비정기 행사 관리 시스템 스탑웨이팅 

> 개발 기간 : 2022.01.04 ~ 2022.06.14</br>  
> 개발 인원 : 4명

## 📑 목차

1.  [프로젝트 기획 배경](#프로젝트-기획-배경)
2.  [주요 기능 설명](#주요-기능-설명)
3. [기술 스택](#기술-스택)
4. [아키텍처](#아키텍처)
5. [ERD](#erd)
6. [인터페이스](#인터페이스)
7. [팀원 소개 및 역할](#팀원-소개-및-역할)
8. [회고](#회고)

## 📌프로젝트 기획 배경

- 유명하거나 인기 있는 장소에 입장하기 위해 입장 대기를 하는 곳이 많다.
  특히 사람이 많은 시간대에는 어느 가게를 가도 필연적으로 기다림이 발생하게 된다.
  때문에 불필요한 시간 낭비 및 대면접촉을 하게 되고, 이는 교내에서도 마찬가지이다.
  현재 대부분의 웨이팅 애플리케이션은 사업자가 사용료를 납부해야 서비스를 이용할 수 있기 때문에 교내 식당 및 미용실 등 편의 시설이나 교내 특식 배부 장소에서는 사용이 불가능하다.

=> 코로나로 인해 비대면 활동이 많아진 지금, 교내 QR코드를 이용하여 교내 식당/미용실 같은 편의 시설과 특식 배부같은 이벤트에서 사용 가능한 교내 웨이팅 어플리케이션을 개발하였다.


## 🔎주요 기능 설명

- 지도에 원하는 카테고리별 아이콘 표시
- 지도상의 위치 및 현재 인원, 대기 인원 현황 확인
- 비대면 대기 번호 발급 및 처리
- 웨어러블 장치(스마트 워치)를 통한 대기열 확인/취소 및 알람
- 현재 위치 주위에 근처의 웨이팅 조회
- 교내 QR코드를 이용하여 웨이팅 체크인
- Firebase Cloud Messaging을 이용한 어플 푸시알림


## 🛠기술 스택

<table>
<tr>
 <td align="center">언어</td>
 <td>
  <img src="https://img.shields.io/badge/Java-orange?style=for-the-badge&logo=Java&logoColor=white"/>

 </td>
</tr>
<tr>
 <td align="center">프레임워크</td>
 <td>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=ffffff"/>
</tr>
<tr>
 <td align="center">라이브러리</td>
 <td>

<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=SpringBoot&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=ffffff"/>
<img src="https://img.shields.io/badge/jwt-6DB33F?style=for-the-badge&logo=jwt&logoColor=ffffff"/>
</tr>
<tr>
 <td align="center">패키지 매니저</td>
 <td>
    <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

  </td>
</tr>
<tr>
 <td align="center">인프라</td>
 <td>
  <img src="https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=MYSQL&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/amazons3-569A31?style=for-the-badge&logo=amazons3&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=ffffff"/>
  <img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=ffffff"/>

</tr>

<tr>
 <td align="center">협업툴</td>
 <td>
    <img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"/>
    <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"/> 
    <img src="https://img.shields.io/badge/Discord-0058CC?style=for-the-badge&logo=Discord&logoColor=white"/> 
 </td>
</tr>
<tr>
 <td align="center">기타</td>
 <td>
    <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=Figma&logoColor=white"/>
    <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"/> 
    <img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white"/>
 </td>
</tr>
</table>

## 🧱아키텍처
![img.png](images/img0.png)

## ERD
![img.png](images/ERD.png)

## 인터페이스
 - 웨이팅 진행 현황 조회(지도)

    ![img.png](images/img.png)

 - 웨이팅 시간 선택

    ![img_1.png](images/img_1.png)
 - 웨이팅 등록

    ![img_2.png](images/img_2.png)

 - 장소 설정

    ![img_3.png](images/img_3.png)

 - 장소 세부 설정(웨이팅 종류 설정)

    ![img_4.png](images/img_4.png)

 - 장소 세부 설정(장소 시간 설정)

    ![img_5.png](images/img_5.png)

 - 승인 대기중인 웨이팅

    ![img_6.png](images/img_6.png)

 - 대기중인 웨이팅 목록 - 웨어러블 디바이스

    ![img_7.png](images/img_7.png)

 - 웨이팅 세부 확인 - 웨어러블 디바이스

    ![img_8.png](images/img_8.png)

 - 지도 내 위치 확인 - 웨어러블 디바이스

    ![img_9.png](images/img_9.png)
## 팀원 소개 및 역할
* 방진성 : 스탑웨이팅 서버 개발, Wear 어플리케이션 개발
* 이윤석 : 스탑웨이팅 메인 어플리케이션 개발
* 한기윤 : 스탑웨이팅 서버 개발
* 한유현 : 스탑웨이팅 관리자 어플리케이션 개발

## 💬 회고
알림 기능을 구현하는 데, 어플리케이션이 꺼져있는 상황에도 계속 알림을 켜기 위해서는</br>
통신 채널을 항상 활성화해야 했으며, 이에 백그라운드 자원소모가 큰 문제가 발생했다.</br>
토론을 거쳐 크로스 플랫폼의 필요성을 도출하게 되었고,</br>
Firebase Cloud Messaging 방식으로 변경하여</br>
FCM 구글 연결 서버를 두어서 백그라운드와 네트워크 리소스에 대한 부담을 줄이는 것에 성공했다.</br>
또한 FCM을 이용하면 플랫폼에 종속되지 않고 푸쉬 메시지를 전송할 수 있었다.</br>
iOS, Android, Web 각 플랫폼에서 푸쉬 메시지를 보내면 하나로 통합하여 관리해주어 편리했다.</br>
</br>
교내QR코드는 교내 구성원 전부가 가지고 있었는데, 해당 QR코드에는 그 사람의 학번이 담겨있었다.</br>
우리는 예약 개설자 어플리케이션안에서 카메라를 띄우게 하고 예약자의 교내QR코드를 인식시키면</br>
해당 학번에 대한 로그인을 하고, DataBase에서 해당 이벤트에 대한 예약이 있다면</br>
그 예약을 처리해주는 편리한 기능을 제공하여 사용자 경험을 증가시켰다.</br>
</br>
스마트 워치와도 연결되게 하여 사용자가 굳이 핸드폰을 꺼내지 않더라도</br>
스마트 워치로만 예약 번호를 확인하거나 예약 완료/취소 처리를 하는 등 사용자 경험을 고려하였다.
