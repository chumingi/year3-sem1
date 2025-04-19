# 실습 Pythouch

- 머신러닝에는 없음. 딥러닝만 있음.
1. pythouch형 tensor로 변환
2. 로더에 업로드

## Pytorch Classification(분류)

### 1. 환경 설정 및 라이브러리 임포트

```python
import torch                          # 핵심 라이브러리
import torch.nn as nn                 # 신경망 모듈
import torch.optim as optim           # 최적화 알고리즘
from torch.utils.data import DataLoader, TensorDataset  # 데이터 로더
import matplotlib.pyplot as plt       # 학습 과정 시각화
```

- **torch**: 텐서와 GPU 연산 등 PyTorch 핵심 기능 제공
- **nn.Module**: 신경망의 구조를 정의하는 베이스 클래스
- **optim**: 손실을 줄이기 위한 가중치 업데이트 기법
- **DataLoader**: 배치 처리, 셔플, 멀티프로세싱 지원
- **matplotlib**: 손실과 정확도를 시각화

---

### 2. 장치(Device) 선택

```python
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
```

- **cuda**(GPU) 사용 가능 시 선택, 아니면 **cpu**
- 모델과 데이터를 `to(device)`로 이동시켜 연산 속도 최적화

---

### 3. 데이터 준비(Data Preparation)

1. **Raw Data** → `numpy` 또는 `pandas` → `torch.tensor` 변환
2. **정규화** / **스플릿**
3. **TensorDataset** 생성
4. **DataLoader** 설정

```python
X_train_tensor = torch.tensor(x_train.values, dtype=torch.float32)
y_train_tensor = torch.tensor(y_train.values, dtype=torch.long)

train_dataset = TensorDataset(X_train_tensor, y_train_tensor)
train_loader = DataLoader(train_dataset, batch_size=32, shuffle=True)
```

- **TensorDataset**: 특징과 레이블을 쌍으로 묶음
- **shuffle=True**: 에폭마다 데이터 순서 무작위화로 일반화 성능 향상

---

### 4. 모델 정의(Model Definition)

```python
class SimpleClassifier(nn.Module):
    def __init__(self):
        super().__init__()
        self.fc1 = nn.Linear(input_dim, hidden_dim)
        self.relu = nn.ReLU()
        self.fc2 = nn.Linear(hidden_dim, num_classes)

    def forward(self, x):
        x = self.relu(self.fc1(x))
        return self.fc2(x)

model = SimpleClassifier().to(device)
```

- **nn.Linear**: 선형 변환 계층(완전연결층)
- **ReLU**: 비선형 활성화 함수
- **forward**: 순전파 정의, 입력이 연산을 거쳐 출력으로 변환되는 흐름

---

### 5. 손실 함수 및 옵티마이저 설정(Loss & Optimizer)

```python
criterion = nn.CrossEntropyLoss()          # 분류 손실 함수
optimizer = optim.Adam(model.parameters(), lr=0.001)  # 가중치 갱신 기법
```

- **CrossEntropyLoss**: 소프트맥스+클래스 인덱스 기반 오차 계산
- **Adam**: 적응적 학습률을 사용하는 최적화 알고리즘

---

### 6. 학습 루프(Training Loop)

```python
train_losses = []
num_epochs = 8
for epoch in range(num_epochs):
    model.train()
    running_loss = 0.0
    for inputs, labels in train_loader:
        inputs, labels = inputs.to(device), labels.to(device)

        optimizer.zero_grad()               # 기울기 초기화
        outputs = model(inputs)             # 순전파
        loss = criterion(outputs, labels)   # 손실 계산
        loss.backward()                     # 역전파
        optimizer.step()                    # 가중치 갱신

        running_loss += loss.item()

    train_losses.append(running_loss / len(train_loader))
```

- **model.train()**: 드롭아웃, 배치정규화 등 학습 모드 활성화
- **zero_grad()**: 이전 기울기 누적 방지
- **loss.backward()**: 오차 역전파로 기울기 산출
- **optimizer.step()**: 가중치 업데이트

---

### 7. 평가 루프(Evaluation Loop)

```python
model.eval()
all_labels, all_preds = [], []
with torch.no_grad():               # 기울기 계산 비활성화로 메모리 절약
    for inputs, labels in test_loader:
        inputs = inputs.to(device)
        outputs = model(inputs)
        _, predicted = torch.max(outputs, 1)

        all_labels.extend(labels.numpy())
        all_preds.extend(predicted.cpu().numpy())
```

- **model.eval()**: 드롭아웃/배치정규화 평가 모드로 설정
- **torch.no_grad()**: 기울기 계산 중단, 연산 속도 향상 및 메모리 절약
- **torch.max**: 가장 높은 로짓 값을 갖는 클래스 인덱스를 예측값으로 선택

---

### 8. 성능 지표 및 시각화(Visualization)

```python
from sklearn.metrics import accuracy_score, confusion_matrix

acc = accuracy_score(all_labels, all_preds)
print(f"Test Accuracy: {acc * 100:.2f}%")

# 손실 및 정확도 추이 시각화\ nplt.figure(figsize=(12, 5))
plt.subplot(1, 2, 1)
plt.plot(train_losses, label='Training Loss')
plt.xlabel('Epoch')
plt.ylabel('Loss')
plt.title('Training Loss Over Epochs')
plt.legend()

plt.subplot(1, 2, 2)
plt.plot(test_accuracies, label='Test Accuracy')
plt.xlabel('Epoch')
plt.ylabel('Accuracy (%)')
plt.title('Test Accuracy Over Epochs')
plt.legend()

plt.show()
```

- **accuracy_score**: 정확도 계산
- **confusion_matrix**: 분류 성능 분포 확인
- **Matplotlib**: 학습 곡선 시각화로 과적합 및 수렴 여부 파악

---

## Pytorch Regression(회귀)

### 1. 환경 설정 및 라이브러리 임포트

```python
import torch                          # 핵심 라이브러리
import torch.nn as nn                 # 신경망 모듈
import torch.optim as optim           # 최적화 알고리즘
from torch.utils.data import DataLoader, TensorDataset  # 데이터 로더
import matplotlib.pyplot as plt       # 학습과 평가 결과 시각화
import numpy as np                    # 수치 연산
from sklearn.metrics import mean_squared_error, accuracy_score, confusion_matrix  # 평가 지표
```

---

### 2. 장치(Device) 선택

```python
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
```

- GPU가 사용 가능하면 `cuda`, 아니면 `cpu`를 선택
- 모델과 데이터를 `to(device)` 호출로 이동시켜 연산 가속

---

### 3. 데이터 준비(Data Preparation)

1. 원본 데이터(`numpy`/`pandas`) → `torch.tensor` 변환
2. 정규화 및 학습/테스트 분할
3. `TensorDataset` → `DataLoader` 생성

```python
X_train_tensor = torch.tensor(x_train.values, dtype=torch.float32)
y_train_tensor = torch.tensor(y_train.values, dtype=torch.float32).view(-1, 1)  # 회귀용 float

train_dataset = TensorDataset(X_train_tensor, y_train_tensor)
train_loader = DataLoader(train_dataset, batch_size=32, shuffle=True)
```

- 회귀 문제에서는 레이블을 `float32`로, 분류 문제에서는 `long`으로 준비
- `shuffle=True`로 매 에폭마다 데이터 순서 무작위화

---

### 4. 모델 정의(Model Definition)

```python
class RegressionModel(nn.Module):
    def __init__(self, input_dim):
        super().__init__()
        self.fc1 = nn.Linear(input_dim, 64)
        self.relu = nn.ReLU()
        self.fc2 = nn.Linear(64, 1)  # 회귀 출력은 1개

    def forward(self, x):
        x = self.relu(self.fc1(x))
        return self.fc2(x)

model = RegressionModel(input_dim=x_train.shape[1]).to(device)
```

- `nn.Linear(..., 1)`로 회귀 출력층 구성

---

### 5. 손실 함수 및 옵티마이저 설정(Loss & Optimizer)

```python
# 회귀 손실: MSE
criterion = nn.MSELoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)
```

- `MSELoss()`는 회귀 모델의 평균 제곱 오차를 계산

---

### 6. 학습 루프(Training Loop)

```python
train_losses = []
num_epochs = 8
for epoch in range(num_epochs):
    model.train()
    running_loss = 0.0
    for inputs, labels in train_loader:
        inputs, labels = inputs.to(device), labels.to(device)

        optimizer.zero_grad()
        outputs = model(inputs)
        loss = criterion(outputs, labels)
        loss.backward()
        optimizer.step()

        running_loss += loss.item()

    train_losses.append(running_loss / len(train_loader))
    print(f"Epoch {epoch+1}, Loss: {train_losses[-1]:.4f}")
```

- `model.train()` 모드 활성화 후 순전파·역전파·가중치 업데이트

---

### 7. 테스트 루프(Evaluation Loop)

```python
model.eval()
preds, actuals = [], []
with torch.no_grad():
    for X_batch, y_batch in test_loader:
        X_batch = X_batch.to(device)
        outputs = model(X_batch).cpu().numpy()
        preds.extend(outputs.flatten())
        actuals.extend(y_batch.numpy())
```

- `model.eval()`으로 평가 모드 전환
- `torch.no_grad()`로 기울기 계산 비활성화
- `.flatten()`으로 shape 정리 후 리스트에 확장

---

### 8. 회귀 평가 지표 및 베이스라인(Regression Metrics)

```python
# MSE 계산
mse = mean_squared_error(actuals, preds)
print(f"Test MSE: {mse:.4f}")

# 기준 모델: 평균만 예측
baseline = np.mean(y_test)
mse_baseline = mean_squared_error(y_test, [baseline] * len(y_test))
print(f"Baseline MSE: {mse_baseline:.2f}")

# 개선률 계산
improvement = (mse_baseline - mse) / mse_baseline * 100
print(f"MSE Improvement: {improvement:.1f}%")
```

- `mean_squared_error`로 모델 MSE와 베이스라인 MSE 비교
- 개선률 `%` 계산으로 학습 성능 정량적 평가

---

### 9. 시각화(Visualization)

```python
plt.figure(figsize=(12, 5))

# 9.1 손실 곡선 (회귀 학습)
plt.subplot(1, 2, 1)
plt.plot(train_losses, label='Training Loss')
plt.xlabel('Epoch')
plt.ylabel('Loss (MSE)')
plt.title('Training Loss Over Epochs')
plt.legend()

# 9.2 예측 vs 실제 산점도
plt.subplot(1, 2, 2)
plt.scatter(actuals, preds, alpha=0.6)
plt.plot([0, 100], [0, 100], 'r--')  # 완벽 예측선
plt.xlabel('Actual')
plt.ylabel('Predicted')
plt.title('Prediction vs Actual')
plt.grid(True)

plt.tight_layout()
plt.show()
```

- 학습 손실 추이와 **실제 vs 예측**을 한눈에 파악