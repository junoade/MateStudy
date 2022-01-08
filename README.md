# MateStudy
<div align ="center">
<img src="https://img.shields.io/badge/-Java 11-007396?style=flat-square&logo=Java"> <img src="https://img.shields.io/badge/Gradle 7.2-02303A.svg?style=flat-square&logo=Gradle"> <img src="http://img.shields.io/badge/-Springboot2.5.6-000000?style=flat-square&logo=SpringBoot">
<br/>
<img src="http://img.shields.io/badge/-Thymeleaf-005F0F?style=flat-square&logo=Thymeleaf"> <img src="http://img.shields.io/badge/-MySQL 8-fbceb1?style=flat-square&logo=MySQL"><br/>
<img src="https://img.shields.io/github/commit-activity/w/junoade/MateStudy">  
</div>
<br/>

<div align = "center">
  <h3>2021-2 소프트웨어공학개론 일찍칼퇴팀</h3>
  🥇 <h4> 프로젝트 우수팀 선정</h4>
  -11팀 중 6팀 우수팀 시상-
  <h4> 주제 : 실습 관리 시스템</h4>
  <h4> 개발 기간 : 21.11.06 ~ 21.12.01</h4>
  <h4> 유지보수 기간 : </h4>
  <h4> 프로젝트 개요 </h4>
  2021-2학기 최은만 교수님의 소프트웨어공학개론을 수강하며 배운 <br/>
  소프트웨어 개발 주기 사이클을 프로젝트로서 체화하는 경험을 갖었다.</br>
  구체적으로 사용사례 명세, 클래스 다이어그램 설계, 프로젝트 백로그 수립, 스프린트와 백로그,<br/>
  UI 설계, 스프린트 과정을 거쳐 본 프로젝트를 마무리하였다.<br/>
</div>

##### 프론트엔드 개발
2016112144 김민석<br/>
2017112083 김범규<br/>

##### 백엔드 개발
2017112129 이정욱<br/>
2017112095 최준호 - DB 설계 및 RDS 관리, 스프링 DATA JPA 활용한 도메인 개발, 컨트롤러 일부 처리 <br/><br/>



### 소프트웨어 개발 문서, DB 설계 내용, 백엔드 구현 문서 관련 
---
사용사례 명세, 클래스 다이어그램 설계, DB 설계등의 내용과<br/>
애자일 방법론으로 스프린트 백로그 작성 및<br/>
스프린트 회고 과정에 관한 문서, 백엔드 개발 과정에서 참고한 문서들이 다음의 노션 링크에 정리되어있다.<br/>
https://periodic-leopard-f2d.notion.site/21-11-06-21-12-01-86cb225a9ed9452b9b4cc85d5e0e42b0

### DB 관련 설정
---
- `src/main/resources/` 경로에 application.yml 작성 필요
- DB의 경우 데모때 사용한 DB를 백업하여 DB_Backups에 제공하고 있음

```
spring:
  datasource:
    url: jdbc:mysql://[MySQL DB 경로 입력]
    username: [유저네임 입력]
    password: [비밀번호 입력]
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: none
      naming:
        physical-strategy: org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl # JPA에서 Physical 모델 생성 관련 설정
    database: mysql
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    properties:
      hibernate:
        show_sql: true
        format_sql: true
        use_sql_comments: true
logging:
  level:
    org:
      springframework:
        security: DEBUG

```

### 구현 결과물 요약
---

#### 1. 로그인 
- 실습 관리자와 학생이 실습관리시스템에 접속하기 위해 로그인을 한다.
-- 회원의 Role에 따라 실습 관리자 또는 학생 계정으로 로그인이 된다.
![login](https://user-images.githubusercontent.com/54317409/148642864-b80339d6-4162-46d8-b8f7-6c5a4e128d80.PNG)


#### 2. 실습과제 관리
- 실습과제 관리자는 과제를 등록, 조회, 수정 삭제한다.
- 학생은 부여받은 과제를 등록, 조회, 수정, 삭제한다.
![hw-admin](https://user-images.githubusercontent.com/54317409/148643049-452ebe29-74b2-4ccc-a9b4-598e2c08c360.PNG)

![과제등록하기-교수](https://user-images.githubusercontent.com/54317409/148643066-b5faea13-e7ce-4612-b279-eeac5c4ac24d.PNG)

![과제 세부](https://user-images.githubusercontent.com/54317409/148643069-ac5d87c5-9370-45a3-a1bd-21b76c26c86a.PNG)

- 학생은 과제 점수를 확인한다.
![image](https://user-images.githubusercontent.com/54317409/148643113-71e7386c-2bd6-4f3d-95fd-92134af34105.png)


#### 3. 실습과제 채점
- 실습과제 관리자는 자신이 관리하는 학생들의 과제를 확인하여 점수를 부여한다.
![채점하기-교수](https://user-images.githubusercontent.com/54317409/148643126-1d6065c4-886a-44fa-b783-0cf6b4960da7.PNG)

#### 4. 질문 관리
- 실습과제 관리자는 자신이 담당하는 모든 과목 또는 특정 과목에서 학생이 올린 질문들을 확인하고 답변한다.
- 학생은 자신이 속한 모든 과목 또는 특정 과목에서 질문을 등록하고 조회한다.
- 학생은 답변을 확인이 가능하고 추가 질문을 한다.
![question-admin](https://user-images.githubusercontent.com/54317409/148643177-452d7ed6-e985-4c1b-ad04-2c37624be1dc.PNG)

![image](https://user-images.githubusercontent.com/54317409/148643199-5c55187d-c8ac-46c7-af6a-421d558143cc.png)

#### 5. 출석 관리
- 실습과제 관리자는 자신이 담당하는 과목들에 소속된 학생들의 출석('미정', '출석', '지각', '결석', '공결')을 확인하고, 등록, 갱신한다.
- 학생은 자신이 속한 과목들의 출석 정보를 확인한다.

![attend-admin](https://user-images.githubusercontent.com/54317409/148643222-adbd9d0b-7a06-4c24-a8de-c70d8f24099c.PNG)

![attend-admin-specific-class](https://user-images.githubusercontent.com/54317409/148643228-931f3f28-7b06-4079-8d3c-55174fb88824.PNG)



#### 6. 채팅 (미구현)

