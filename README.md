###  EasyRecyclerView

* add header view && footer view
* header divider

you must add header or footer before setAdapter 

<img src="https://github.com/6a209/EasyRecyclerView/raw/master/screenshots/1.png" alt="alt text" style="width:240px">
<img src="https://github.com/6a209/EasyRecyclerView/raw/master/screenshots/2.png" alt="alt text" style="width:240px">
<img src="https://github.com/6a209/EasyRecyclerView/raw/master/screenshots/3.png" alt="alt text" style="width:240px">


##### How to use

add this to your dependencies

```
compile 'com.easyrecyclerview:lib:0.4@aar'

```

in java code 
 
```
easyRecycler.addHeader(view);

easyRecycler.addFooter(view);

recyclerView.setHeaderDividerHeight(20);

recylcerView.setHeaderDrawable(drawable)

```
