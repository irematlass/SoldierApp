package com.example.soldierapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random
import androidx.core.view.size as size

class MainActivity : AppCompatActivity() {
    val listDataManager: ListDataManager = ListDataManager(this)

    private lateinit var myNameList: RecyclerView
    private lateinit var myCityList: RecyclerView
    private lateinit var myDistributeList: RecyclerView
    private var nameList = arrayListOf<String>()
    private var cityList = arrayListOf<String>()
    private var soldierList = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameList = listDataManager.readLists("nameList")
        myNameList = findViewById(R.id.nameList)
        myNameList.layoutManager = LinearLayoutManager(this)
        myNameList.adapter = MyAdapter(nameList)

        cityList = listDataManager.readLists("cityList")
        myCityList = findViewById(R.id.cityList)
        myCityList.layoutManager = LinearLayoutManager(this)
        myCityList.adapter = MyAdapter(cityList)

        soldierList = listDataManager.readLists("soldierList")
        myDistributeList = findViewById(R.id.distributionList)
        myDistributeList.layoutManager = LinearLayoutManager(this)
        myDistributeList.adapter = MyAdapter(soldierList)
        addName_btn.setOnClickListener {
            val personName = personName_edt.text.toString().trim()
            if (personName.isEmpty()) {
                personName_edt.error = "İsim alanı boş geçilemez"
                personName_edt.requestFocus()
                return@setOnClickListener
            } else {

                val adapter = myNameList.adapter as MyAdapter
                adapter.addNewItem(personName_edt.text.toString())
                listDataManager.saveList("nameList", nameList)
                personName_edt.text.clear()
            }
        }

        addCity_btn.setOnClickListener {
            val city = city_edt.text.toString().trim()
            if (city.isEmpty()) {
                city_edt.error = "Şehir alanı boş geçilemez"
                city_edt.requestFocus()
                return@setOnClickListener
            } else {

                val adapter = myCityList.adapter as MyAdapter
                adapter.addNewItem(city_edt.text.toString())
                listDataManager.saveList("cityList", cityList)
                city_edt.text.clear()
            }

        }

        distribute_btn.setOnClickListener {
            soldierList.clear()

            var tempList = listDataManager.readLists("nameList")
            cityList = listDataManager.readLists("cityList")
            while (tempList.size > 0) {
                for (x in 0 until cityList.size) {
                    var soldierNo = (0 until tempList.size).random()
                    val adapter = myDistributeList.adapter as MyAdapter
                    var item: String = tempList[soldierNo] + "-" + cityList[x]
                    Log.d("itemerror", item)
                    adapter.addNewItem(item)
                    listDataManager.saveList("soldierList", soldierList)
                    tempList.removeAt(soldierNo)
                    if (tempList.size == 0) break
                }
            }
        }
        delete_btn.setOnClickListener {
            listDataManager.deleteLists()
            nameList.clear()
            (myNameList.adapter as MyAdapter).notifyDataSetChanged()
            cityList.clear()
            (myCityList.adapter as MyAdapter).notifyDataSetChanged()
            soldierList.clear()
            (myDistributeList.adapter as MyAdapter).notifyDataSetChanged()
        }

    }
}