# 4주차

## fitting

- 학습을 잘 맞추는가
- AI가 주어진 데이터에서 패턴을 찾는 것룰을 습득하는 것

### **overfitting**

- 학습이 많이 된 것
- 노이즈에도 반응하게 된다.
- 원인
    - 모델이 너무 복잡하다. (노이즈까지 반영된 모델)
- 해결방법
    - 모델을 단순하게 만든다
    - 규제

### **underfitting**

- 학습이 적게 된 것
- 일반화 능력이 떨어진다
- 원인
    - 학습 데이터의 부족
    - 모델이 간단함
- 해결방법
    - 데이터 양을 늘린다. (수집, 증대기법 등)
    - 모델을 좀 더 복잡하게 만든다.

## 데이터의 편향성

- 훈련 데이터와 테스트 데이터의 형태가 다르면, 테스트 성능 떨어짐
- 모델이 훈련할 때는 성능이 좋지만 테스트할 때는 성능이 떨어지는 과적합(overfitting) 문제 발생

### 인덱싱

- 데이터를 나눌 때 data[:80]를 훈련, data[80:]를 테스트로 하면 실제로는 비슷한 패턴이 한쪽으로 몰릴 수 잇다. (데이터 편향성 발생)
- 해결방법: 데이터를 무작위로 섞는다.

## 스케일링

- (데이터 - 평균) / 분산 ⇒ StandardScaler()
- 데이터를 0과 1 사이로 변환해서 기준을 맞추어준다.

## 교차분석(cross varidation)

### 문제

- 데이터 1~5에서 1~4, 2~5 뽑을 때
- 특성이 비슷하다면 결과 유사하지만 특성이 다르면 성능이 떨어진다.

### 개념

- 모델의 일반화 성능을 공정하게 평가하기 위한 검증 기법
- 전체 데이터에서 하나를 검증용으로, 나머지를 훈련용으로 사용하는 과정을 반복하고, 평균값을 구한다.

### K-fold 교차검증

- 데이터를 k개의 동일한 크기로 나누고 각 조각을 검증용으로 사용한다.
- 모델을 학습하고 평가하는 과정을 k번 반복하고 평균성능을 구한다.
- train data와, test data에서 test data는 그대로 사용하고, train data는 trai data와 validation data로 나눈다.

## 규제

### 목적

- overfitting을 방지하기 위해서 사용한다.
- 복잡한 모델을 단순하게 조정하여 일반화 성능을 높인다.

### 종류

- **L1 규제 (Lasso)**: 불필요한 특성의 가중치를 0으로 만들어 **중요한 특성만 남김**
- **L2 규제 (Ridge)**: 불필요한 특성의 가중치를 0에 가깝게 줄여 **특성의 영향력을 약화**시킴

### 조기 종료 (Early Stopping)

- 모델이 최적의 성능에 도달하

## 회귀(Regression)

### 개념

- **연속적인 수치 값을 예측**할 때 사용하는 지도학습 방법
- x(특성)와 y(정답) 사이의 상관관계를 수식으로 표현: `y = wx + b`
- 딥러닝의 목적은 기울기와 편향을 구하는 것, 모델에서 coef와 intersept 속성 사용

### 상관계수 R과 R^2

- **R**: -1 ~ 1 범위. x와 y의 선형 관계 강도 측정
- **R^2**: 0 ~ 1 범위. 예측값이 실제값을 얼마나

### 종류

- 선형 회귀 : x가 하나
- 다항 회귀 : x가 여러 개

### feature engineering

- 특성의 수를 증가시켜 모델의 성능을 높인다.
- 새로운 특성을 만들었을 때 기존의 특성과 상관관계까 있는지 판단 → 적을 때 도움이 된다.

### 차원의 저주

- 차원이 많아질수록 모델이 복잡해져 성능이 떨어지는 문제

## KNN (K-Nearest Neighbors)

- **레이블을 예측**하는 **지도학습 알고리즘**
- 새로운 데이터가 주어졌을 때, **주변의 K개 이웃의 레이블을 이용하여 예측**

## 로지스틱 회귀 (Logistic Regression)

- 특정 시점 이후를 상수함수(y=1)
- 0 또는 1로 분류

## 분류 알고리즘

### 결정트리 (Decision Tree)

- 스무고개와 유사, 계속해서 분류
- 불순도가 높은 것부터 분류하며 불순도를 낮추어간다.
- **불순도:** 클래스가 섞여 있는 정도, 주머니에 검은 공과 흰 공이 저란씩 섞여 잇으면 불순도가 높음

### 랜덤 포레스트 (Random Forest)

- 앙상블 중 하나 : 결정 트리를 여러 개 사용, 분류하는 기준을 서로 다르게 하여 최적의 결과 찾기

### 앙상블

- 여러 개의 모델을 학습시켜 가장 좋은 결과를 사용한다.
- **장단점**
    - **장점** : 하나의 모델을 사용할 때보다 성능이 좋다.
    - **단점** : 하드웨어 자원이 많이 필요하다.

### 부트스트래핑

- 동일한 샘플을 중복하여 여러 개의 샘플을 여러 개의 샘플을 만든다.
- **예제**
    - 1, 2, 3,, 4에서
    - (1, 1, 2, 3, 4), (4, 4, 4, 4, 4), (2, 3, 3, 4, 4)
    - 각각의 샘플에 결정 트리를 사용하여 최적의 결과를 찾는다.
- **장단점**
    - **장점** : 결정트리보다 성능이 좋다 과적합 방지 가능
    - **단점** : 시간이 오래 걸린다

## 하이퍼파라미터 튜닝

- 그리드 서칭: 범위를 나누어서 모든 파라미터의 조합 실행 → 가장 좋은 조합 찾기
- 하이퍼파라미터: 불순도, 깊이 등 모델의 학습에 영향을 주는 변수 설정
- 튜닝: 모델의 성능이 최상이 되도록 하이퍼파라미터 값을 조절하는 것

# 딥러닝

- 원-핫 인코딩, 넘파이 배열 변환 등 전처리 필수
- 입력 특성 수, 활성화 함수 선택 중요
- **다중분류**: softmax 사용
- **이진분류**: sigmoid 사용
- **회귀** 문제에서는 출력층 활성화 함수 없이 값 그대로 출력 (또는 linear)

## Last-layer activation and loss function combinations

| Problem type | Last-layer activation | Loss function | Example |
| --- | --- | --- | --- |
| Binary classification | sigmoid | binary_crossentropy | Dog vs cat, Sentiment analysis (pos/neg) |
| Multi-class, single-label classification | softmax | categorical_crossentropy | MNIST has 10 classes, one prediction is one digit |
| Multi-class, multi-label classification | sigmoid | binary_crossentropy | News tags classification, one blog can have multiple tags |
| Regression to arbitrary values | None | mse | Predict house price (an integer/float point) |
| Regression to values between 0 and 1 | sigmoid | mse or binary_crossentropy | Engine health assessment (0 = broken, 1 = new) |

---