package com.example.jsoupxmlparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.util.Xml
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.jsoupxmlparser.model.Currency

class MainActivity : AppCompatActivity() {

    lateinit var currencyList : List<Currency>
    lateinit var autoCompleteText: AutoCompleteTextView
    lateinit var forexBuyingTextView : TextView
    lateinit var forexSellingTextView : TextView
    lateinit var banknoteBuyingTextView : TextView
    lateinit var banknoteSellingTextView : TextView
    lateinit var forexBuyingLayout : LinearLayout
    lateinit var forexSellingLayout : LinearLayout
    lateinit var banknoteBuyingLayout : LinearLayout
    lateinit var banknoteSellingLayout : LinearLayout

    var currencyNameList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        autoCompleteText = findViewById(R.id.dropDownItems)
        forexBuyingTextView = findViewById(R.id.forexBuyingText)
        forexSellingTextView = findViewById(R.id.forexSellingText)
        banknoteBuyingTextView = findViewById(R.id.banknoteBuyingText)
        banknoteSellingTextView = findViewById(R.id.banknoteSellingText)
        forexBuyingLayout = findViewById(R.id.forexBuyingLayout)
        forexSellingLayout = findViewById(R.id.forexSellingLayout)
        banknoteBuyingLayout = findViewById(R.id.banknoteBuyingLayout)
        banknoteSellingLayout = findViewById(R.id.banknoteSellingLayout)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var xmlResult = XmlResult()
        currencyList = xmlResult.xmlGetCurrency()
        xmlResult.xmlGetCurrencyDate()
        for (item in currencyList) {
            currencyNameList.add(item.currencyName)
        }

        var adapter = ArrayAdapter<String>(this, R.layout.list_item,currencyNameList)
        autoCompleteText.setAdapter(adapter)

        autoCompleteText.onItemClickListener = AdapterView.OnItemClickListener{ adapterView,view,position,l ->

            val currency = currencyList.get(position)
            forexBuyingTextView.text = currency.forexBuying
            forexSellingTextView.text = currency.forexSelling
            banknoteBuyingTextView.text = currency.banknoteBuying
            banknoteSellingTextView.text = currency.banknoteSelling
            forexBuyingLayout.visibility = View.VISIBLE
            forexSellingLayout.visibility = View.VISIBLE
            banknoteBuyingLayout.visibility = View.VISIBLE
            banknoteSellingLayout.visibility = View.VISIBLE
        }

    }
}