# 5주차 강의노트

## 리뷰

- 회귀 : x와 y의 연속적인 관계
- 결정트리=스무고개, 기준: 불순도
- 랜덤포레스트: 샘플링 다르게 하여 여러개의 결정트리 사용(앙상블)

---

## SVM

- support vector: 두 클래스의 경계에서 가장 가까운 두 요소
- 마진을 최대한 넓히도록 선을 긋기 -> 역직교각 긋기

---

## 딥러닝

1. forward propagation
2. loss function
3. backward propagation
4. optimizer
5. activate function

### 1. forward propagation

- 입력층: 특성 수
- 출력층 : 1개 (Regression)
- 파라미터 많음 = 기울기, 편향 많음 ⇒ 성능 좋음
- 입력 $x_1$, $x_2$와 가중치에 따라 출력 결정됨

### 2. loss function (손실함수)

- 예측값과 실제값의 차이를 계산하는 함수
- **회귀**: mse, **분류**: cross entropy

### 3. backward propagation

- 오차를 바탕으로 weight와 bias 조정하여 업데이트

### 4. optimizer (최적화 함수)

- 손실함수를 줄이기 위해 weight와 bias 조정

### 5. activate function (활성화 함수)

- **회귀**: 없음
- **이진분류**: sigmoid
- **다중분류**: softmax

### 예시

- foward propagation: RGB 색 조정
- loss function: 빨간색 예측했는데 주황색 나옴
- backward propagation: RGB 조합 조정
- 위의 3가지 반복하여 최적의 결과 도출

### 머신러닝과의 차이

1. 레이블을 원.핫인코딩으로 변환 (레이블간의 연관성제거 - 0001, 0010, 0100 …)
2. 데이터를 넘파이 배열로 변환
3. 출력층과 activatefunction의 쌍
    - 회귀: activate function 없음
    - 이중분류: sigmoid와
    - 다중분류: softmax

### 평가기준

- 분류 : accuracy_score, f1_score 등
- 회귀 : mse 등

---

## 경사하강법

- 딥러닝에서 각 뉴런의 최적의 weight와 bias를 구하기 위한 알고리즘
- loss function(손실함수) 적절히 조정하는 방법
- 손실함수 구한 후, 얼만큼 이동해야 최적값에 다다르는지를 계산
- 수식: $다음 w = 현재 w - 학습률(고정값)*(손실함수의 미분식에 현재 w 대입)$

---

## 과제

1. diabetes 딥러닝 분류, 회귀
2. car_evaluation 딥러닝 분류
3. abalone 딥러닝 회귀