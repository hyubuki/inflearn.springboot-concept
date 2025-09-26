## Test
### 構成要素（コンポーネント)
- テスト対象
- テストコード 
- 協力者（コラボレーター）
- テストスタブ (Imposter, Test Double)

### JUnit
全てのテストは隔離された環境で実行されるべきである。 <br/>
そのため、JUnitは各テストメソッドの前に新しいインスタンスを生成する。

---
#### Test Annotation

|     annotation     | Description |                Declaration                 |
|:------------------:|:-----------:|:------------------------------------------:|
|    @DisplayName    |   Method    |                  Test 名称                   |
|       @Test        |   Method    |                  Test 宣言                   |
| @ParameterizedTest |   Method    |              パラメータ化されたテストの宣言               |
|    @ValueSource    |   Method    | パラメータ化されたテストの引数を提供<br/> パラメータを繰り返しながらテストを実行 |
|@NullAndEmptySource |   Method    |       パラメータ化されたテストの引数に null と空文字を提供        |
|@EnumSource     |   Method    |        パラメータ化されたテストの引数に Enum の全値を提供        |

---

#### Lifecycle Annotation

| annotation  | Description |             Declaration              |
|:-----------:|:-----------:|:------------------------------------:|
| @BeforeAll  |   Method    | クラス内で最初に一度だけ実行される。　全てのテストが行われる前に修行する |
|  @AfterAll  |   Method    |  クラス内で最後に一度だけ実行される。全てのテストが終わったら行われる  |
| @BeforeEach |   Method    |           各テストメソッドの前に実行される           |
| @AfterEach  |   Method    |           各テストメソッドの後に実行される           |

---

#### Mock
|    annotation    | Description |             Declaration              |
|:----------------:|:-----------:|:------------------------------------:|
| @ExtensionWith |単体テストを拡張するために付けるもので、拡張クラスのコールバックが呼ばれる|クラス|
## テストすべきもの
- １。制御不能な外部システムに障害が発生した場合
- ２。為替レートは ExRateProvider が提供する値で計算されているか
- ３。為替レートの有効期限（TTL）の計算は正確か？


### テストをする為に最も大切なこと
- 全てのコードを一つのクラスに全部入れ込んだら、テストコードを柔軟にできない
    - Spring-FrameworkのDIを利用して、テストコードを柔軟にすることが大事
    - 特定のコードをテストするだけなのに、他の部分まで変更が必要になり、テストスタブも使えなくなる。

### Spring Beanを利用するテスト
- テスト用の協力・依存オブジェクトをSpring Application Contextから取得する
  - @ContextConfiguration
  - @Autowired
  