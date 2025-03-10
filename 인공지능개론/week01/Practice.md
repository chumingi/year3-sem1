# 📚 붓꽃 데이터 머신러닝 실습
붓꽃 데이터(`iris.csv`)를 활용하여 **4가지 머신러닝 모델(Decision Tree, Random Forest, SVM, Logistic Regression)**을 학습하고 평가하는 실습

---

## 🌱 1. 공통 코드 (데이터 로드 및 전처리)
```python
import pandas as pd
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.metrics import accuracy_score, classification_report

# 붓꽃 데이터 CSV 파일 읽기
file_path = r"C:\Users\MinGi\Desktop\year3-sem1\AI\week01\iris.csv"
df = pd.read_csv(file_path)

# 데이터프레임 확인
print(df.head())
print(df.columns)

# 특성과 타겟 분리
x = df.iloc[:, :-1]  # 마지막 열을 제외한 모든 열 (특성)
y = df.iloc[:, -1]   # 마지막 열 (타겟)

# 학습(70%), 테스트(30%) 데이터 나누기
x_train, x_test, y_train, y_test = train_test_split(x, y, test_size=0.3, random_state=42, stratify=y)

# 데이터 정규화 (SVM, Logistic Regression에서 필요)
scaler = StandardScaler()
x_train_scaled = scaler.fit_transform(x_train)
x_test_scaled = scaler.transform(x_test)
```

### ✅ 데이터 샘플
| SepalLength | SepalWidth | PetalLength | PetalWidth | Name         |
|-------------|------------|-------------|------------|--------------|
| 5.1         | 3.5        | 1.4         | 0.2        | Iris-setosa  |
| 4.9         | 3.0        | 1.4         | 0.2        | Iris-setosa  |
| 4.7         | 3.2        | 1.3         | 0.2        | Iris-setosa  |

---

## 🔥 2. 머신러닝 모델 학습 및 평가
### 1️⃣ Decision Tree (DT)
```python
from sklearn.tree import DecisionTreeClassifier

# 모델 정의 및 학습
dt_model = DecisionTreeClassifier(random_state=42)
dt_model.fit(x_train, y_train)

# 예측 및 평가
y_pred_dt = dt_model.predict(x_test)
print("Decision Tree Accuracy:", accuracy_score(y_test, y_pred_dt))
print(classification_report(y_test, y_pred_dt))
```
#### 🏆 결과
```
Decision Tree Accuracy: 0.9333
                 precision    recall  f1-score   support
    Iris-setosa       1.00      1.00      1.00        15
Iris-versicolor       1.00      0.80      0.89        15
 Iris-virginica       0.83      1.00      0.91        15
```

---

### 2️⃣ Random Forest (RF)
```python
from sklearn.ensemble import RandomForestClassifier

# 모델 정의 및 학습
rf_model = RandomForestClassifier(n_estimators=100, random_state=42)
rf_model.fit(x_train, y_train)

# 예측 및 평가
y_pred_rf = rf_model.predict(x_test)
print("Random Forest Accuracy:", accuracy_score(y_test, y_pred_rf))
print(classification_report(y_test, y_pred_rf))
```
#### 🏆 결과
```
Random Forest Accuracy: 0.8889
                 precision    recall  f1-score   support
    Iris-setosa       1.00      1.00      1.00        15
Iris-versicolor       0.78      0.93      0.85        15
 Iris-virginica       0.92      0.73      0.81        15
```

---

### 3️⃣ Support Vector Machine (SVM)
```python
from sklearn.svm import SVC

# 모델 정의 및 학습
svm_model = SVC(kernel='linear', random_state=42)
svm_model.fit(x_train_scaled, y_train)

# 예측 및 평가
y_pred_svm = svm_model.predict(x_test_scaled)
print("SVM Accuracy:", accuracy_score(y_test, y_pred_svm))
print(classification_report(y_test, y_pred_svm))
```
#### 🏆 결과
```
SVM Accuracy: 0.9111
                 precision    recall  f1-score   support
    Iris-setosa       1.00      1.00      1.00        15
Iris-versicolor       0.82      0.93      0.88        15
 Iris-virginica       0.92      0.80      0.86        15
```

---

### 4️⃣ Logistic Regression (LR)
```python
from sklearn.linear_model import LogisticRegression

# 모델 정의 및 학습
lr_model = LogisticRegression(max_iter=200, random_state=42)
lr_model.fit(x_train_scaled, y_train)

# 예측 및 평가
y_pred_lr = lr_model.predict(x_test_scaled)
print("Logistic Regression Accuracy:", accuracy_score(y_test, y_pred_lr))
print(classification_report(y_test, y_pred_lr))
```
#### 🏆 결과
```
Logistic Regression Accuracy: 0.9111
                 precision    recall  f1-score   support
    Iris-setosa       1.00      1.00      1.00        15
Iris-versicolor       0.82      0.93      0.88        15
 Iris-virginica       0.92      0.80      0.86        15
```

---

## 📊 5. 모델 성능 비교
| 모델                            | 정확도(%) | 특징                                    |
|---------------------------------|-----------|-----------------------------------------|
| **Decision Tree (DT)**          | **93.33%**| 높은 정확도, 특정 클래스에서 과적합 가능성 |
| **Random Forest (RF)**          | **88.89%**| 안정적이지만 일부 클래스 성능 저하          |
| **Support Vector Machine (SVM)**| **91.11%**| 정규화된 데이터 사용 시 우수한 성능          |
| **Logistic Regression (LR)**    | **91.11%**| 단순하지만 SVM과 유사한 성능                |