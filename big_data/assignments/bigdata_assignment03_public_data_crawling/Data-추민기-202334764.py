import requests
import pandas as pd
import matplotlib.pyplot as plt
import json
import datetime

plt.rcParams['font.family'] = 'Malgun Gothic'
plt.rcParams['axes.unicode_minus'] = False

SERVICE_KEY = "jleTMFFJw9vlY5ZDc16Jf24Q6ZCpS8GLkjYDJHZviiwcYDbCE5bQjEtf4vGTqgT%2Bd6EGlB5JP616SLRMzZmuuQ%3D%3D"

def generate_dates(start, end):
    start_dt = datetime.datetime.strptime(start, "%Y%m%d")
    end_dt = datetime.datetime.strptime(end, "%Y%m%d")
    return [(start_dt + datetime.timedelta(days=i)).strftime("%Y%m%d")
            for i in range((end_dt - start_dt).days + 1)]

def get_covid_json_by_date(date_str):
    url = "http://apis.data.go.kr/1352000/ODMS_COVID_04/callCovid04Api"
    params = {
        "serviceKey": SERVICE_KEY,
        "pageNo": "1",
        "numOfRows": "500",
        "apiType": "JSON",
        "status_dt": date_str
    }
    try:
        response = requests.get(url, params=params)
        response.raise_for_status()
        return response.json().get('body', {}).get('items', [])
    except:
        print(f"[{date_str}] 요청 실패")
        return []

def collect_covid_data(start_date, end_date):
    all_data = []
    for date in generate_dates(start_date, end_date):
        items = get_covid_json_by_date(date)
        if items:
            all_data.extend(items)
        else:
            print(f"[{date}] 데이터 없음")
    return all_data

def save_data(data, json_file, csv_file):
    with open(json_file, "w", encoding="utf-8") as f:
        json.dump(data, f, indent=4, ensure_ascii=False)

    df = pd.DataFrame(data)

    if df.empty:
        print("❗ 데이터가 없습니다. 파일 저장 및 그래프 생략")
        return df

    rename_map = {
        "stateDt": "기준일자",
        "accExamCnt": "누적 검사 수",
        "dPntCnt": "격리 해제 수",
        "gPntCnt": "사망자 수",
        "hPntCnt": "확진자 수",
        "careCnt": "치료중 환자 수",
        "accDefRate": "누적 확진률"
    }
    df.rename(columns=rename_map, inplace=True)

    available_columns = [col for col in rename_map.values() if col in df.columns]
    df = df[available_columns]

    if "기준일자" in df.columns:
        df["기준일자"] = pd.to_datetime(df["기준일자"], format="%Y%m%d", errors="coerce")
        df.dropna(subset=["기준일자"], inplace=True)
        df.sort_values("기준일자", inplace=True)
    else:
        print("⚠️ '기준일자' 컬럼이 존재하지 않습니다.")

    df.to_csv(csv_file, index=False, encoding="utf-8-sig")
    return df

def monthly_trend_lineplot(df):
    if df.empty or "기준일자" not in df.columns or "확진자 수" not in df.columns:
        print("⚠️ 유효한 데이터가 없어 그래프를 그릴 수 없습니다.")
        return

    df["월"] = df["기준일자"].dt.to_period("M")
    monthly = df.groupby("월")["확진자 수"].sum().reset_index()
    x_labels = monthly["월"].astype(str)

    plt.figure(figsize=(10, 5))
    plt.plot(x_labels, monthly["확진자 수"], marker='o', linestyle='-')
    plt.title("월별 확진자 수 추이")
    plt.xlabel("월")
    plt.ylabel("확진자 수")
    plt.grid(True)
    plt.xticks(ticks=range(len(x_labels)), labels=x_labels, rotation=45)
    plt.tight_layout()
    plt.savefig("코로나_월별_그래프.png")
    plt.show()

if __name__ == "__main__":
    start = input("시작일 입력 (예: 20220301): ")
    end = input("종료일 입력 (예: 20220430): ")

    covid_data = collect_covid_data(start, end)
    df = save_data(covid_data, "코로나_월별_변화.json", "코로나_월별_변화.csv")
    monthly_trend_lineplot(df)
