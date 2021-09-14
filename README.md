# kotlin-simple-test-MVVM
kotlin simple feeling test app using MVVM pattern.

--------------- `start` ------------------------- `question` ------------------------ `result` --------------------- 
<img src="https://user-images.githubusercontent.com/71416677/133190895-10b33185-79e6-44a2-bedd-95a1f583e97f.gif" width="250" height="400"/>
<img src="https://user-images.githubusercontent.com/71416677/133190902-35782911-e6d6-4df9-b76a-e056cebe5ff2.gif" width="250" height="400"/>
<img src="https://user-images.githubusercontent.com/71416677/133190889-8a2fc301-0cdd-4e01-a0d4-fa2fd84198fe.gif" width="250" height="400"/>  




-----------`log` ----------------
<img src="https://user-images.githubusercontent.com/71416677/133190904-69a5f771-f37e-413b-aa22-cedfbe16d08e.gif" width="250" height="400"/>


## Architecture Pattern
MVVM 

![final-architecture](https://user-images.githubusercontent.com/71416677/132950781-3b8c1373-825b-4685-a900-de84f4e5f062.png)  

## Details
* `main`    
Start gif animation when click Start button

* `question`  
5 Questions to analyze your feeling

* `result`  
Sad/Excited percentage and your feeling analysis

* `log`  
Log of previous results using RecyclerView / Using Room (Local Database), all logs keep until delete the app. 


## Used Libraries
* Koin
* Room
* Shared Preference
* Coroutine  

## Directory Tree 
```bash
└───todo_mvvm   
       │
       │  
       ├───data   
       │     ├────db   
       │     │     └─ResultDao.kt   
       │     │     └─ResultDatabase.kt   
       │     │   
       │     ├────entity   
       │     │      └─ResultEntity.kt   
       │     │
       │     ├────preference
       │     │      └─AppPreferenceManager.kt
       │     │
       │     └────repository   
       │            └─ResultRepository.kt   
       │            └─ResultRepositoryImpl.kt   
       │   
       │   
       ├───di   
       │    └─AppModule.kt   
       │    └─ProvideDB.kt   
       │   
       │   
       ├───state  
       │     └─PercentState.kt   
       │     └─ProcessState.kt 
       │     └─ResultState.kt    
       │
       │
       ├───util
       │     └─ButtonType.kt   
       │
       │
       ├───view
       │     ├────base   
       │     │      └─BaseActivity.kt   
       │     │      └─BaseFragment.kt      
       │     │      └─BaseViewModel.kt   
       │     │
       │     │
       │     ├────my
       │     │      └─MyFragment.kt   
       │     │      └─MyViewModel.kt      
       │     │      └─ResultAdapter.kt 
       │     │
       │     │
       │     │
       │     ├────question    
       │     │      ├─fifth
       │     │      │  └─FifthFragment.kt
       │     │      │  └─FifthViewModel.kt
       │     │      │
       │     │      │
       │     │      ├─first
       │     │      │  └─FirstFragment.kt
       │     │      │  └─FirstViewModel.kt
       │     │      │
       │     │      │
       │     │      ├─fourth
       │     │      │  └─FourthFragment.kt
       │     │      │  └─FourthViewModel.kt
       │     │      │
       │     │      │
       │     │      ├─second
       │     │      │  └─SecondFragment.kt
       │     │      │  └─SecondViewModel.kt
       │     │      │
       │     │      │
       │     │      └─third
       │     │         └─ThirdFragment.kt
       │     │         └─ThirdViewModel.kt
       │     │
       │     │
       │     ├────result
       │     │      └─ResultFragment.kt   
       │     │      └─ResultViewModel.kt 
       │     │
       │     │
       │     ├────test
       │     │      └─TestFragment.kt   
       │     │      └─TestViewModel.kt
       │     │
       │     ├────MainActivity.kt
       │     │
       │     └────MainViewModel.kt   
       │   
       └───TestApplication.kt
