# 📘 3주차 빅데이터분석개론 BigData의 이해와 활용 및 처리과정

## 개발자 컨퍼런스 및 주요 이벤트
- Apple WWDC (6월)
- Amazon Re:Invent (12월)
- Google I/O (5월)
- IBM Think (5월)
- Microsoft Build (5월)
- Nvidia GTC (3월)
- NAVER DEVIEW (2월)
- KAKAO IF (11월)
- Samsung SDC  
- **CES 2025 주요 테마**
- 인구구조 변화, 디지털 공존, 인간 안전, 공동체 강화, 수명 연장 등

---

## 🔬 과학과 공학의 차이 (Science vs Engineering)
- **과학적 접근**: 현상을 관찰하고 설명함  
- **공학적 접근**: 가설을 세우고 실험을 통해 결과를 검증함
### 📚 데이터과학 – 3가지 융합 영역 (Interdisciplinary Areas)
- 융합의 시대: 다양한 능력을 골고루 갖춰야 함
1. 확률, 통계  
2. 프로그래밍 능력 (알고리즘, 언어 등)  
3. 도메인 지식 (해당 산업에 대한 이해)

---

## 01. 빅데이터의 이해
### 📌 빅데이터 정의
- 디지털 환경에서 생성되는 대규모 데이터  
- HW, SW, 데이터 유통·분석 프로세스 전체 포함  
- 플랫폼을 통한 가치 창출 지향

### 📌 빅데이터의 출현 배경
- 기술 발전과 비용 하락  
- SNS 사용량 증가  
- 경제·사회적 가치 창출 가능성 주목

### 📌 빅데이터 분류
| 유형           | 설명                                             |
|----------------|--------------------------------------------------|
| 정형 데이터     | 고정된 구조로 즉시 분석 가능 (예: DB, 엑셀 등)  |
| 반정형 데이터   | XML, HTML 등 메타데이터와 스키마 포함           |
| 비정형 데이터   | 구조가 없는 데이터 (예: SNS, IoT, 이미지 등)     |

### 📌 빅데이터의 특징 – 5V
1. **Volume**: 대용량  
2. **Variety**: 다양한 형식 (소셜 데이터, GPS 등)  
3. **Velocity**: 빠른 생성 속도  
4. **Veracity**: 데이터가 많을수록 전체의 신뢰도 향상  
5. **Value**: 의미 있는 정보로 가치를 창출

### 📌 분석 환경에 따른 특징
1. **데이터**  
   - 과거: 정형 데이터 위주  
   - 현재: SMS, CCTV, GPS 등 비정형 데이터도 처리 가능

2. **하드웨어**  
   - 과거: 고가 저장장치 및 대형 DW  
   - 현재: 클라우드 컴퓨팅 기반으로 효율 향상

3. **소프트웨어 분석 방법**  
   - 과거: DBMS, SaaS, 데이터 마이닝, 머신러닝 등  
   - 현재: 오픈소스 기반 텍스트 마이닝, 오피니언 마이닝, 감성 분석 등

### 📌 처리 방식에 따른 특징
| 구분               | 기존의 데이터 처리 방식                                   | 빅데이터 처리 방식                                                 |
|--------------------|------------------------------------------------------------|---------------------------------------------------------------------|
| **데이터 트래픽**    | 트랜잭션 중심                                              | 웹/모바일 등 다수 사용자로부터 생성되는 대규모 트래픽 (100배 이상) |
| **데이터 유형**      | 정형 데이터 중심                                           | 비정형 및 반정형 포함 (SNS, 로그, 이미지, 음성 등)                  |
| **프로세스 및 기술** | 관계형 DB, 서버 성능 향상, 중앙 집중형 설계               | 분산 시스템(Hadoop), 클라우드, NoSQL, 수평 확장 등                  |

---

## 📈 빅데이터의 가치
### 🔧 혁신과 창조의 도구
- 스마트 서비스 제공  
- 비즈니스 효율화, 개인화, 예측 가능성 향상  
- 새로운 패러다임과 생태계 형성

### 💡 사회·경제적 가치
- 정치, 경제, 사회, 과학 분야 전반에 정보 제공  
- 산업 경쟁력 강화, 생산성 향상, 혁신 가능성 증대

## 02. 빅데이터의 활용
### 📌 빅데이터의 역할
- 미래 사회 특성: **불확실성, 리스크, 스마트, 융합**  
- 다양한 가능성 → **시나리오 기반 시뮬레이션**  
- 리스크 대응, 개인화, 지능형 서비스 → **삶의 질 향상**

---

## 📌 빅데이터 활용 전략
### 🔁 빅데이터 처리 단계
**데이터 흐름:**  
`수집 → 저장 → 처리 → 분석 → 표현`
1. 수집
- 크롤링 - 데이터 가져오기
- IoT 센서
2. 저장
- 데이터 형식에 따른 방법
- 저장소 고려
- 데이터의 **속도** 특성
3. 처리
- 데이터를 어디에 저장하고 관리할 것인지
4. 분석
- AI
- 전처리
5. 표현
- 시각화
---

### 🔬 분석 기술
- 통계  
- 데이터 마이닝  
- 머신러닝  
- 딥러닝  
- 자연어 처리  
- 패턴 인식  
- 소셜 네트워크 분석  
- 이미지/오디오/비디오 분석 등

### 🧱 인프라 기술
- BI (Business Intelligence)  
- DW (Data Warehouse)  
- 클라우드 컴퓨팅  
- NoSQL  
- 분산 처리, DFS

### 🌐 관련 신기술
- Hadoop  
- In-memory 처리  
- 자연어 처리  
- 머신러닝, 딥러닝  
- NoSQL 기반 처리 시스템

---

## 🧠 데이터 과학자의 역량
### 📌 존 라우치(John Rauth)의 데이터 과학자 6가지 자질
1. 수학적 역량  
2. 공학적 역량  
3. 비판적 사고 능력  
4. 글쓰기 능력  
5. 커뮤니케이션 능력  
6. 호기심과 개인적 만족 추구