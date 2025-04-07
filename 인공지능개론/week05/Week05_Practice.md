# 5주차 실습

## 딥러닝 분류

### 라이브러리 임포트

```python
from sklearn.datasets import load_breast_cancer
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
import seaborn as sns
from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import SGD, Adam
```

### 원-핫 인코딩

- 어떤 데이터가 어떤 클래스에 속해 있는지를 **0과 1(또는 True와 False)**만 사용해서 표현하는 방법
- 각 클래스별로 하나의 열을 가진다. **이진분류의 경우 2개의 열**만을 가진다.

```python
Y = pd.get_dummies(y).values
```

- **`reshape()`**: 데이터의 구조(행과 열) 확인

```python
print(x_train.shape, y_train.shape)
print(x_test.shape, y_test.shape)
```

- 원-핫 인코딩 전:

```python
(227, 13) (227, )
(76, 13) (76, )
```

- 원-핫 인코딩 후: y 데이터에 2개의 열 생김 (target=0, 1)

```python
(227, 13) (227, 2)
(76, 13) (76, 2)
```

### 모델 정의

- **Sequential 모델**: 레이어를 **위에서 아래로 순서대로 쌓는 구조**

```python
model = Sequential()
```

- 첫번째 층: 뉴런 10개, 입력 13개, 활성화함수 relu

```python
model.add(Dense(10,input_shape=(13,),activation='relu'))
```

- 두번째 층: 뉴런 8개, 3번째 층 뉴런 6개. 활성화함수 relu

```python
model.add(Dense(8,activation='relu'))
model.add(Dense(6,activation='relu'))
```

- 출력 층: 뉴런 2개, 활성화함수: **sigmoid**(각 클래스별 예측 확률 출력)

```python
model.add(Dense(2,activation='sigmoid'))
```

### 모델 컴파일

- **`Adam`**(최적화함수): 효율적인 경사하강법 알고리즘
- `learning_rate`: 학습속도
- **`binary_crossentropy`**(손실함수): 이진 분류에서 정답과 예측값 사이의 차이 계산
- **metrics=['accuracy']**: 정확도 출력

```python
model.compile(Adam(learning_rate=0.04),'binary_crossentropy',metrics=['accuracy'])
```

- 모델 요약 출력

```python
model.summary()
```

### 모델 학습 및 예측

- `epochs=13`**: 13번 반복 학습**
- `batch_size=32`**: 32개씩 나눠서 학습**
- `validation_split=0.1`: 전체 학습 데이터 중 **10%를 검증용으로 사용**

```python
model_history=model.fit(x=x_train, y=y_train, epochs=13, batch_size=32,validation_split=0.1) # train의 10%를 validation으로 설정. 검증에 사용
y_pred = model.predict(x_test)
```

### 클래스 분류

- `np.argmax`: 값이 가장 큰 클래스

```python

y_test_class = np.argmax(y_test,axis=1)
y_pred_class = np.argmax(y_pred,axis=1)
```

### 정확도 확인

- **`classification_report()`**: 분류 모델의 성능을 평가하는 지표들을 한눈에 정리해서 출력
- **`confusion_matrix()`**: 예측이 얼마나 맞고 틀렸느지 출력

```python
print(classification_report(y_test_class,y_pred_class))
print(confusion_matrix(y_test_class,y_pred_class))
```

---

## 딥러닝 회귀

### 라이브러리 임포트

```python
from sklearn.datasets import load_breast_cancer
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score, confusion_matrix
import seaborn as sns
from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import SGD,Adam
```

### 원-핫 인코딩

- 딥러닝 분류와 코드 동일
- 연속적인 값을 예측하므로 `.shape()` 결과에서 열 2개 이상

```python
Y = pd.get_dummies(y).values
```

### 모델 정의

- 대부분의 코드가 딥러닝 분류와 유사
- **`model.add(Dense(1))`**: 활성화함수 없음, 뉴런 1개

```python
model = Sequential()

model.add(Dense(10,input_shape=(8,),activation='relu'))
model.add(Dense(8,activation='relu'))
model.add(Dense(6,activation='relu'))
model.add(Dense(1))
```

### 모델 컴파일

- **`rmsprop`**(최적화 함수): 최근 기울기 값을 더 중요하게 반영하여 학습률을 자동 조정
- **`mse`**(손실함수=평가지표): 평균 제곱 오차

```python
model.compile(optimizer='rmsprop', loss='mse', metrics=['mse'])
model.summary()
```

### 모델 평가 및 예측

- 딥러닝 분류와 유사

```python
N_EPOCHS = 8
model_history = model.fit(x=x_train, y=y_train, epochs=N_EPOCHS, batch_size=32,validation_split=0.1) # train의 10%를 validation으로 설정. 검증에 사용
y_pred = model.predict(x_test)

y_test_class = np.argmax(y_test,axis=1)
y_pred_class = np.argmax(y_pred,axis=1)
```

### 모델 평가

- 손실과 mse 계산

```python
model.evaluate(x_test, y_test)
```

---

## underfitting 해결

- 데이터 양 늘리기 or 모델 복잡하게 만들기
- **모델 복잡하게 만들기**: 뉴런 input과 레이어 증가 (하이퍼파라미터 튜닝)