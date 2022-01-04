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
  구체적으로 사용사례 명세, 클래스 다이어그램 설계, 스프린트 백로그 작성,<br/>
  UI 설계, 스프린트 과정을 거쳐 본 프로젝트를 마무리하였다.<br/>
</div>

##### 프론트엔드 개발
2016112144 김민석<br/>
2017112083 김범규<br/>

##### 백엔드 개발
2017112129 이정욱<br/>
2017112095 최준호 - DB 설계 및 관리, 스프링 DATA JPA 활용한 도메인 개발, 컨트롤러 일부 처리 <br/><br/>



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

### 구현 결과물
---

