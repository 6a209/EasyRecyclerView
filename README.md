###  EasyRecyclerView

* add header view && footer view
* header divider

you must add header or footer before setAdapter 

[[ https://github.com/6a209/EasyRecyclerView/raw/master/screenshots/1.png | width = 160px ]]

[[ https://github.com/6a209/EasyRecyclerView/raw/master/screenshots/2.png | width = 160px ]]

[[ https://github.com/6a209/EasyRecyclerView/raw/master/screenshots/3.png | width = 160px]]

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
