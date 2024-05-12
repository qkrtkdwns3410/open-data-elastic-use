## 프로젝트 목표

1. 데이터 수집 : 공공데이터 호출 -> 배치작업을 통한 DB 저장.
2. 검색 성능 최대화 : Elasticsearch를 통한 검색 서비스 구현.
3. 데이터 시각화 : Kibana를 통한 데이터 시각화.(선택)
4. 데이터 API : Spring Boot를 통한 데이터 API 서비스 구현.
5. vue.js를 통한 검색 프론트엔드 구현.

## 기술스택

- Backend : Spring Webflux
- Frontend : Vue.js
- Database : MongoDB
- Search : Elasticsearch
- Progrmming Language : Java, Javascript
- Build Tool : Gradle

## 기능 구현 영상(패치전)

![시연 영상](play.gif)

## 패치
1. 2024-05-12
    - es 첫번째 토크나이저에 해당하는 영화 제목만 검색하도록 수정 
    - 관련 테스트 코드 작성