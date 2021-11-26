# MateStudy
<div align ="center">
<img src="https://img.shields.io/badge/-Java 11-007396?style=flat-square&logo=Java"> <img src="https://img.shields.io/badge/Gradle 7.2-02303A.svg?style=flat-square&logo=Gradle"> <img src="http://img.shields.io/badge/-Springboot2.5.6-000000?style=flat-square&logo=SpringBoot">
<br/>
<img src="http://img.shields.io/badge/-Thymeleaf-005F0F?style=flat-square&logo=Thymeleaf"> <img src="http://img.shields.io/badge/-MySQL 8-fbceb1?style=flat-square&logo=MySQL"><br/>
<img src="https://img.shields.io/github/commit-activity/w/junoade/MateStudy">  
</div>
<br/>

<div align = "center">
  <h3>2021-1 소프트웨어공학개론 일찍칼퇴팀</h3>
  <h4> 주제 : 실습 관리 시스템</h4>
</div>

##### 프론트엔드 개발
2016112144 김민석<br/>
2017112083 김범규<br/>

##### 백엔드 개발
2017112129 이정욱<br/>
2017112095 최준호<br/>


### DB 관련 설정
- `src/main/resources/` 경로에 application.yml 작성 필요
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

