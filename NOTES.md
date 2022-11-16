# Android News App

## Simple app

### News Api
- URL
```
https://newsapi.org/v2/top-headlines?country=id&category=technology&apiKey=9f760f53bc054697a8a10766019b44ff
```

- Category
```
technology,
science
```

### Setup dependencies
- Retrofit (networking)
- Coroutine (asynchronous lib)
- Koin (dependencies injection)

### Setup networking and data layer
- Create webservices
- Create data response with plugin JsonToKotlin
  - Nullable
  - Gson serializer
- Setup retrofit provider
- Setup koin module for provide webservices instance

### Create Koin provider
- Bind koin provider into the Application

### Create Repository in domain and data layer
- Setup data domain (entity)
  - Non-nullable for avoiding nullable in view layer
  - Default value
  - Immutable property
- Create mapper for map `News Response` into `News`
- Setup koin module

### Setup ViewModel
- Convert `Flow` from repository into `LiveData` for observer in view
- Setup dependencies for provided `asLiveData()`
- Setup koin module

### Setup simple view
- Observer live data in activity
- Check result in text view
- Run on emulator

## Render result in recyclerview

### Setup recyclerview
- Add recyclerview in layout
- Create item layout for each news
- Create view holder for each news item
- Setup Glide Library dependencies for image networking
  - Change `annotationProcessor` with `kotlin-kapt`
- Create news adapter
  - Create auto notifier instead of `notifyDataSetChanged`
  - Change `addNews` to kotlin style
- Setup in activity
- Create clickable item

### Restructure app package

## Next?
- View binding
- State Event (MVI)
- Kotlin extensions util

---
Halo

### View Binding
- Setup gradle
- Setup binding
- Change all `findViewById` to id generated

### State Event (MVI)
- Create event for `Idle`, `Loading`, `Success`, `Error`
- Implement state event in repository
- Create view for each event
- Add delay between `Loading` and `Success` event in repository
  - Not working
  - Try with different job coroutine
  - Not working
  - Let's log with println wkwkw
  - Shit, wrong logic in render state loading hahahaha
  - Let's remove delay and use suspend again
  - WORK'S

### Kotlin extensions for utilities
- Refactor to extensions util:
  - Fetching web services with state
    - Create `Response<T>` extensions to flow event
    - Setup with mapper
  - When operator in state event observer

### Error handling
- Next...

## Next?
- Git
- Error handling

----
Halo
Part 3

---
## Git
- Create github repository
- Connect to remote
- Commit and push
- Branching
  - feat/xxx

## Error handling
- Git branch feat/error_handling
- Create fake error api
- Create coroutine error handler
- Connect coroutine error handler to ui event
- Create simple UI error
- Success!!
- update git and branch
- merge branch tp main

## Create detail page