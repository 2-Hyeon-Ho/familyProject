아래와 같은 요구사항의 과제를 Spring Boot로 개발하기 


# Database

- MySQL을 사용

# ERD

- 제공해드린 ERD를 참고해주세요!

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d36caf13-89ad-4f26-b0a1-b3f331fa2ad9/Untitled.png)

# DDL 및 기반 데이터 등록 스크립트

제공해드린 주민관리 스크립트를 mysql서버에서 실행합니다.

- [(평가실습문제 정답) 주민관리 스크립트.sql](https://nhnacademy.dooray.com/share/pages/VtSlZePJQXuRu6ff7wgTaw/files/3418279901239775759)

# data access layer 구현 - JPA 이용

- 제공해드린 ERD와 스크립트를 기준으로 과제를 진행합니다.
- **Entity 맵핑 -**ERD 상에 표현된 컬럼들을 모두 맵핑한다
- **연관관계 맵핑 -**ERD 상에 표현된 relation은 모두 맵핑한다
- **Repository**
    - REST API`와` 웹페이지를 위한 쿼리들을 Repository로 구현한다
    - 아래 기능들을 모두 이용해서 Repository를 구현해야 한다
        - JpaRepository에 정의된 메서드
        - Spring Data JPA Repository에서 제공하는 메서드 이름 규칙을 이용한 쿼리 생성
        - `@Query`를 이용한 JPQL 수행
        - Querydsl을 이용한 Custom Repository 구현
    - 너무 한 가지 기능에 의존해서 개발한 경우 감점한다
- 단순 컬럼 조회 시 Dto Project 기능을 최대한 활용한다
- `Pageable`을 이용해 페이징 기능을 구현해야 한다

# 기반 데이터 추가 입력을 위한 REST API

- REST API로 구현한다
    - jackson library를 이용해서 JSON으로 요청 및 응답
- API 별로**IntelliJ http request (*.http)**파일 생성

# **주민 (주민 자체 정보만 등록/수정)**

- 등록`POST /residents`
- 수정`PUT /residents/{serialNumber}`

# **가족관계**

- 등록

```
POST /residents/{serialNumber}/relationship     // 여기 `serialNumber`는 기준 주민의 일련번호

{
    "familySerialNumber": 123,                  // 대상 가족의 주민 일련번호
    "relationShip": "father"                    // 부(father),모(mother),배우자(spouse),자녀(child)
}

```

- 수정

```
PUT /residents/{serialNumber}/relationship/{familySerialNumber}

{
    "relationShip": "mother"
}

```

- 삭제

```
DELETE /residents/{serialNumber}/relationship/{familySerialNumber}

```

# **출생 신고**

- 출생신고서 등록`POST /residents/{serialNumber}/birth`
- 출생신고서 수정`PUT /residents/{serialNumber}/birth/{targetSerialNumber}`
- 출생신고서 삭제`DELETE /residents/{serialNumber}/birth/{targetSerialNumber}`

### **cf.)**

- `{serialNumber}`: 행위자의 주민 일련번호
- `{targetSerialNumber}`: 출생한 사람의 주민 일련번호

# **사망 신고**

- 사망신고서 등록`POST /residents/{serialNumber}/death`
- 사망신고서 수정`PUT /residents/{serialNumber}/death/{targetSerialNumber}`
- 사망신고서 삭제`DELETE /residents/{serialNumber}/death/{targetSerialNumber}`

### **cf.)**

- `{serialNumber}`: 행위자의 주민 일련번호
- `{targetSerialNumber}`: 사망한 사람의 주민 일련번호

# **세대**

- 세대 등록`POST /household`
- 세대 삭제`DELETE /household/{householdSerialNumber}`

### **cf.)**

- 세대 구성은 복잡하므로 등록과 삭제만 제공한다
- 수정은 삭제 후 등록으로 대신한다

# **세대 전입 주소**

- 세대 전입 주소 등록`POST /household/{householdSerialNumber}/movement`
- 세대 전입 주소 수정`PUT /household/{householdSerialNumber}/movement/{reportDate}`
- 세대 전입 주소 삭제`DELETE /household/{householdSerialNumber}/movement/{reportDate}`

### **cf.)**

- `{reportDate}`
    - 전입신고일자
    - `YYYYMMDD`형태의 날짜 정보

# 웹페이지

- `Thymeleaf`를 이용해서 HTML로 응답한다

# **주민 목록**

- 페이징 기능이 제공되어야 한다
- 개별 주민마다 아래 페이지들에 대한 링크를 제공한다
    - 가족관계증명서
    - 주민등록등본
    - 출생신고서 (있는 경우에만 링크 출력)
    - 사망신고서 (있는 경우에만 링크 출력)
    - 증명서 발급 목록
- 개별 주민마다 삭제 버튼을 제공한다

# **가족관계증명서**

# **주민등록등본**

# **출생신고서**

# **사망신고서**

# **증명서 발급 목록**

- 페이징 기능이 제공되어야 한다
- 상세 화면 없이 목록만 제공하면 된다
- 가족관계증명서, 주민등록등본, 출생신고서, 사망신고서 조회 시 증명서 발급 기록

# **주민 삭제**

- 해당 주민에 대한 모든 정보 (주민, 가족관계, 세대, 출생/사망 신고 등)를 삭제한다
- 세대주는 다른 가족이 없는 경우에만 삭제가 가능하다
    - 다른 가족이 있는데 삭제 요청을 한 경우`남은 가족이 있어 삭제가 불가능합니다`에러 메시지 출력