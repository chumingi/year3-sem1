# 11주차

## 영상 내용

### 1. 프롬프트엔지니어링

- 어떤 데이터를 넣느냐에 따라 어떤 결과가 나오는지가 달라짐
- LLM(예 챗GPT)에 어떤 질문을 하느냐에 따라 대답이 달라짐 (질문을 잘해야 함)
- 질문을 구체적으로 해야 함. 질문을 만들어서 깊게 파고들기

### 2. AI는 일종의 파트너, 인간은 아님

- 반말로 질문하다 보면 반말로 대답해줌
- 사용자에게 편향된 답변을 주는 방식. 긍정적인 질문과 부정적인 질문 모두 해봐야 함

### 3. 창의력

- 지식을 쌓다 보면 창의력이 멈춰버림
- GPT를 어떻게 학습시키고 활용하느냐에 따라 결과 많이 달라짐

---

## 리뷰

### Dnse layer

- 모든 입력을 하나로 연결
- 입력 10개일 때 $b_1 + b_2 + … + b_{10}$
- 데이터를 1차원으로 펴서 입력 ⇒ 입력 간의 관계는 고려하지 않음
- 전체적인 특징 추출. 지역적인 특징 추출 x

### CNN

- 여러 특징을 추출하기 위해 필터와 합성곲 이용
- 부분적으로 필터를 옮겨가며 지역적인 특징 추출
- 항상 마지막에 Flatten(일차원 데이터로 변환)하여 Dnse layer 이용
- 플링: 너무 많아진 데이터를 줄여주는 역할
- ***합성곱 계산해보기 - 시험 문제 출제 가능성***
- 필터 값 초기 랜덤, 학습 과정에서 최적의 값으로 변경
- 스트라이드: 필터를 이동할 보폭
- 패딩: 필터를 커널 내에서 이동할 것인지(valid), 외부까지 이동하고 없는 값을 다른 값으로 대체(same)
- 플링의 역할: 정보의 축약, 이동의 불변성
- avg플링: 평균(두드러진 특징) 추출, ma플링: 최댓값 추출

---

### 계산 예제 372p

- 입력: 28x28 이미지, 필터: 3x3 32개 ⇒ 26x26
- 2x2 플링 ⇒ 출력: 13x13, 채널 수: 64개
- 연습문제 9, 10번 풀어보기

---

## 영상 인식

- 정지 영상 분석: 세그멘테이션, yolo 라이브러리 활용
- `keras.sypa10.load`
- 필터 크기 작으면: 많은 특징 추출 가능하지만 오버피팅 위험성
- 그래프 그리는 이유: 최적의 학습 지점 찾기, 학습의 안정성

## 데이터 증대

- 언더피팅(학습의 부족) 원인: 모델의 단순성 ***또는*** 데이터의 부족(라벨링 작업은 돈과 시간 많이 듬)
- 증대 방법: 흑백화, 밝기 조절, 뒤집기, 회전 등
- 단점: 다양성의 문제. 일부 데이터만을 이용하여 데이터를 증대하면 전체 데이터의 특성을 반영하기 어려움 ***또는*** MRI 데이터를 증대하였지만 의학적으로는 의미 없을 수도 있음)
- `keras.ImageDataGenetor`

## 전이 학습 (**중요)**

- 네트워크가 길이가 깊다면 학습하기 어려움
- 추상적인 특징을 추출하는 앞부분은 그대로 활용하고, 구체적인 특성을 활용하년 뒷부분만 다르게 하여 효율성 높임
- 장점
    1. 이미 검증된 네트워크 사용 가능 (예: `VGG16, ResNet`)
    2. 학습 시간 단축
- vgg16 붓글씨 예제
    
    ```java
    import tensorflow as tf
    from tensorflow.keras.applications import VGG16
    from tensorflow.keras.layers import Dense, Flatten, Dropout
    from tensorflow.keras.models import Model
    from tensorflow.keras.preprocessing.image import ImageDataGenerator
    import numpy as np
    
    # 1. 붓글씨 데이터 로드 (여기선 MNIST를 예시로 사용)
    (x_train, y_train), (x_test, y_test) = tf.keras.datasets.mnist.load_data()
    
    # 2. VGG16은 RGB 3채널 224x224 이미지를 받기 때문에 리사이즈 및 채널 확장
    x_train = np.stack([tf.image.resize(tf.expand_dims(img, -1), (224, 224)).numpy() for img in x_train])
    x_test = np.stack([tf.image.resize(tf.expand_dims(img, -1), (224, 224)).numpy() for img in x_test])
    
    x_train = np.repeat(x_train, 3, axis=-1) / 255.0  # 흑백 이미지를 RGB 3채널로 복제 후 정규화
    x_test = np.repeat(x_test, 3, axis=-1) / 255.0
    
    # 3. 라벨을 원-핫 인코딩
    y_train = tf.keras.utils.to_categorical(y_train, 10)
    y_test = tf.keras.utils.to_categorical(y_test, 10)
    
    # 4. VGG16 모델 불러오기 (pre-trained ImageNet 가중치 사용, top 제거)
    base_model = VGG16(weights='imagenet', include_top=False, input_shape=(224, 224, 3))
    
    # 5. VGG16의 가중치를 고정 (Feature Extractor로 사용)
    base_model.trainable = False
    
    # 6. 사용자 정의 출력층 추가
    x = Flatten()(base_model.output)             # Conv 레이어 출력 평탄화
    x = Dense(256, activation='relu')(x)         # 은닉층 추가
    x = Dropout(0.5)(x)                           # 과적합 방지용 드롭아웃
    output = Dense(10, activation='softmax')(x)  # 숫자 0~9 분류용 출력층
    
    # 7. 모델 구성
    model = Model(inputs=base_model.input, outputs=output)
    
    # 8. 모델 컴파일
    model.compile(optimizer='adam',
                  loss='categorical_crossentropy',
                  metrics=['accuracy'])
    
    # 9. 모델 훈련
    model.fit(x_train, y_train, epochs=5, batch_size=32, validation_split=0.2)
    
    # 10. 모델 평가
    loss, acc = model.evaluate(x_test, y_test)
    print(f"Test Accuracy: {acc:.4f}")
    
    ```
    

### 파인튜닝

- 잘 된 부분 동결(재사용)하고 내 데이터만 학습 ⇒ 동결을 풀고 학습시켜 미세조정
- 근사값을 가지고 있음. 훈련하여 정답을 찾아가는 데 오래 걸리지 않음

---

## 실습 - 텍스트 데이터 CNN

- shape, timestemp, feature 형식
- timestemp를 1로 설정 - Dense와 CNN 성능 유사. (단독 추출 - 연관관계 없음)
- `split_sequence()`
    
    ```java
    # 회귀 문제에서 사용할 수 있는 그룹화 함수
    def split_sequence(sequence, n_steps):
    
        X, y = [], [] # 새로운 배열 생성
    
        for i in range(len(sequence) - n_steps):
    
            # 입력 시퀀스와 다음 값 분리
    
            seq_x = sequence[i:i + n_steps] # n_steps 단위로 끊기
    
            seq_y = sequence[i + n_steps]
    
            X.append(seq_x)
    
            y.append(seq_y)
    
        return np.array(X), np.array(y)
    ```
    
    - n_steps이 4일 때
    - 앞 4개 데이터를 이용하여 뒤의 데이터 예측

---

## RNN

- Dense: 모든 데이터 한꺼번에 특정.
- CNN: 현재 시점의 데이터만 고려
- RNN: 앞의 데이터와 뒤의 데이터의 순서 관계 있음 (시간 순서 반영)