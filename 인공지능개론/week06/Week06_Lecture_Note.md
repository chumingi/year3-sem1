# 6주차 강의노트

## 리뷰

### 머신러닝

- x와 y의 관계
- x와 y: 특성(입력)과 정답 나누기
- train과 test: 정답과 예측값 비교
- 학습한 모델에 입력(x)를 넣어서 답 얻기
- 예) 스마트폰: 가속도, GPS 등의 센서 데이터 모델 학습 ⇒ 실제 사용자의 센서 데이터(x)를 바탕으로 움짐임 여부 판단

### 분류와 회귀

- 분류: 예측한 값 중 얼마나 정답인지 정확도 계싼
- 회귀: 실제값과 예측값의 차이를 mse를 이용하여 계산

### 머신러닝과 딥러닝 차이

- (딥러닝)
    - `shape()`로 구조 확인 후
    - numpy 배열로 변경
    - 레이블 원-핫 인코딩 (원-핫 인코딩)
- (공통) 예측 및 평가: accuracy_score(분류), mse(회귀), f1_score

### iloc

- 전처리 이후에 최종 사용
- 넘파이 배열로 자동 전환되기 때문에 데이터 조작 불가능

### DataFrame에서의 index

- 사용하지 않는 것 권장
- 인덱스 열이 있는 경우에만 제거하기 위해서 사용.
- 인덱스 열 없을 시 데이터 열이 인덱스로 사용되어 성능이 떨어짐

### 그래프 확인

- accuracy: 처음 train 높음 test 낮음. ⇒ 이후 test는 높아졋다 낮아짐(과적합)
- loss: 처음 train 낮음, test 높음 ⇒ 이후 0에 가까워짐 | 스케일링, 하이퍼파라미터튜닝(뉴런 수, 활성화함수 등), 규제)

---

## 성능 평가

### 정확도(accuracy)

- 정확도 = (올바르게 맞춘 샘플 수) / (전체 샘플 수)
- 데이터가 불균형(편향)한 경우, 정확도가 높음에도 성능이 떨어질 수 있음

### 혼동 행렬

- 학습된 모델이 예측을 하면서 얼마나 혼동하고 잇는지를 나타내는 행렬
- tp: 긍정을 정확하게 정상으로 예측
- tn: 긍정을 부정으로 잘못 예측
- fp: 부정을 정확하게 부정으로 예측
- fn: 부정을 긍정으로 잘못 예측
- f1_scroe, recall, precesion 중요, 시험 출제

### F1_score

- 데이터의 편향성 고려 - 데이터가 불균형할 때 효과적
- recall과 precesion의 적절히 균형

### recall

- true(관심의 대상)이 잘 예측되었는지를 평가하기 위해 사용
- 놓치면 안 되는 데이터를 잘 찾는 것이 중요할 때 사용

### precesion

- 모델이 긍정이라고 예측한 것 중 실제로 긍정인 것의 비율
- 가능한 한 많이 맞추기

### AUC

- 0과 1 사이의 값. 1에 가까우면 좋음

---

## 딥러닝 개념

### 1. forward propagation

- 가중치 조정하여 손실함수 계산

### 2. backward propagation

- 손실함수를 참고하여 가중치 업데이트

### 3. loss function

- 실제 값과 예측한 값의 차이 계산

### 4. optimizer

- 손실 함수로부터 계산된 기울기를 기반으로, **가중치와 편향을 업데이트하는 알고리즘**
- 경사하강법을 실제로 구현하여 최적의 weight와 bias를 찾는 알고리즘
- `SGD` (확률적 경사하강법)
- `Adam` (모멘텀 + 적응적 학습률)

### 5. activate function

- 층을 아무리 깊게 쌓아도, 선형만 있으면 의미 없음 ⇒ 선형 중간에 비선형 추가
- Relu, Sigmoid, Softmax 등

## 퍼센트론

- 0보다 크면 1, 작으면 0
- 뉴런이 입력을 받아들일 것인지 아닐지 결정

### 경사하강법

- 손실함수를 최소화하기 위해 weight와 bias를 조정하는 알고리즘 (개념) ⇒ optimizer 구현
- learning_rate: 학습을 얼마나 시킬 것인가? optimizer에서 설정 가능
- 현재 위치 알기 위해 손실함수 미분 ⇒ 양수이면 줄이기, 음수이면 늘리기

### 경사하강법 예제

- loss_func = (x-2)^2 + 10
- 미분 = 2x-6
- 학습률 = 0.02
- y = 10 - 0.02*14 = 7.2

### 경사하강법 종류

1. 배치: 전체 데이터를 사용하여 계산. 안정적이지만 느림
2. 확률적: 샘플 하나씩 기울기 계산. 빠르지만 정확도 떨어짐.
3. 미니배치: 소규모 묶음 단위로 계산. 속도와 완정성 균형
