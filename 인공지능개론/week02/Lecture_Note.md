# 2주차 인공지능개론 강의노트 (XLSX 파일 읽기부터)

## 1. 데이터 읽기
### 1-1. 파일에서 가져오기
```python
file_path = r"C:\Users\MinGi\Desktop\year3-sem1\인공지능개론\dataset\mobile.csv"
```
### 1-2. 웹에서 가져오기
- raw.githubusercontent를 사용해야 오류 발생하지 않음.
```python
url = "https://raw.githubusercontent.com/MyungKyuYi/AI-class/main/combined_dataset-1.xlsx"
```
### 1-3. csv 데이터 가져오기
```python
df = pd.read_csv(file_path)
```
### 1-4. xlsx 데이터 가져오기
- xlsx 파일을 읽기 위해서는 반드시 `openpyxl` 엔진이 필요함
```python
df = pd.read_excel(url, engine="openpyxl")
```

---

## 2. 산점도 (Scatter Plot)
- 데이터를 시각적으로 표현하여 관계 확인  
- 기울기(**w**)와 y절편(**b**)을 구하는 과정 필요  
### 2-1. KNN (k-Nearest Neighbors) 알고리즘
- 기본적으로 **지도 학습**이지만, **비지도 학습**으로도 활용 가능
- **K 값 (k \>= 2) 사용하여 가장 가까운 점 찾기**
### 2-2. KNN 코드
#### 최적의 k 값 찾기
- 1부터 k 값을 늘려가며 scores 배열에 정확도 추가
- 최대 정확도가 있는 인덱스 = 최적의 k
```python
from sklearn.neighbors import KNeighborsClassifier

k_range = range(1, 100)
scores = []
for k in k_range:
    knn_model = KNeighborsClassifier(n_neighbors=k)
    knn_model.fit(x_train_scaled, y_train)
    y_pred_knn = knn_model.predict(x_test_scaled)
    scores.append(accuracy_score(y_test, y_pred_knn))
max_index = scores.index(max(scores))
optimal_k = k_range[max_index]

knn_model = KNeighborsClassifier(n_neighbors=optimal_k)
knn_model.fit(x_train_scaled, y_train)
y_pred_knn = knn_model.predict(x_test_scaled)

print("Optimal K:", optimal_k)
print("KNN Accuracy:", accuracy_score(y_test, y_pred_knn))
print(confusion_matrix(y_test, y_pred_knn))
```
#### 결과(diabiates.csv)
```
Optimal K: 3
KNN Accuracy: 0.7705627705627706
[[128  22]
 [ 31  50]]
```
- 최적의 k : 3
- 정확도 : 약 77%
- 클래스 0 : 128개 정확히 예측, 22개 클래스 1로 잘못 예측
- 클래스 1 : 50개 정확히 예측, 31개 클래스 0으로 잘못 예측

---

## 3. 데이터의 편향성 (Bias)
- **불균형 데이터** 학습 시 **Major Class**에 치우칠 가능성 높음  
- **데이터 분포 확인 : `value_counts()` 활용**
```python
for label in df.columns:
    print("\n", df[label].value_counts())
```
#### 결과(일부)
```
 PassengerId
1      1
599    1
588    1
589    1
590    1
      ..
302    1
303    1
304    1
305    1
891    1
Name: count, Length: 889, dtype: int64

 Survived
0    549
1    340
Name: count, dtype: int64

 Pclass
3    491
1    214
2    184
Name: count, dtype: int64
```

---

## 4. 결측치 확인 및 처리
### 4-1. 결측치 확인
- 데이터셋의 **누락된 값 개수 확인** : `isnull().sum()`
```python
df.isnull().sum()
```
#### 결과
```
PassengerId      0
Survived         0
Pclass           0
Name             0
Sex              0
Age            177
SibSp            0
Parch            0
Ticket           0
Fare             0
Cabin          687
Embarked         2
dtype: int64
```
### 4-2. 결측치 처리
- 결측치 제거 또는 다른 값으로 채우기
- subset에 해당하는 컬럼이 결측치인 행을 삭제하고, 원본에 적용한다.
- 결측치가 있는 컬럼이 꼭 필요한 경우, mean()으로 평균값으로 대체 가능
```python
df.dropna(subset=["Embarked"], inplace=True) # Embarked 컬럼의 결측치 제거
mean_age = df['Age'].mean()
df['Age'] = df['Age'].fillna(mean_age)
```
#### 결과
```
PassengerId      0
Survived         0
Pclass           0
Name             0
Sex              0
Age              0
SibSp            0
Parch            0
Ticket           0
Fare             0
Cabin          687
Embarked         0
dtype: int64
```

---

## 5. 데이터 프레임
### 5-1. Pandas와 Numpy 비교
- **Pandas** : 컬럼명 기반 조작 가능, 직관적
- **Numpy** : 빠름, 열(Column) 개념 없음
### 5-2. 데이터 탐색 분석 (EDA)
- **히스토그램 등 시각화 \-> 이후 Numpy로 변환**
```python
import seaborn as sns

sns.histplot(df['feature_column'])
plt.show()
```
### 5-3. 불필요한 컬럼 삭제
- 삭제할 컬럼을 배열에 나열
- df.drop()으로 배열에 나열된 컬럼 제외
```python
# 불필요한 컬럼 제거
columns_to_delete = ['Name', 'PassengerId', 'Ticket', 'Cabin', 'Embarked']
df = df.drop(columns=columns_to_delete)
```
### 5-3. 문자 -> 숫자로 변환
- 머신러닝: **문자형도 가능**  
- 딥러닝: **반드시 숫자로 변환 필요**  
#### 레이블 인코딩 (LabelEncoder)
- fit_transform() : 데이터에 있는 고유한 값 학습 -> 문자를 숫자로 변환
```python
from sklearn.preprocessing import LabelEncoder

label_encoder = LabelEncoder()
df['Sex'] = label_encoder.fit_transform(df['Sex'])
```
#### value_counts() 결과(일부)
```
 Survived
0    549
1    340
Name: count, dtype: int64

 Pclass
3    491
1    214
2    184
Name: count, dtype: int64

 Sex
1    577
0    312
Name: count, dtype: int64

 Age
29.642093    177
24.000000     30
22.000000     27
18.000000     26
28.000000     25
            ... 
36.500000      1
55.500000      1
0.920000       1
23.500000      1
74.000000      1
Name: count, Length: 89, dtype: int64
```