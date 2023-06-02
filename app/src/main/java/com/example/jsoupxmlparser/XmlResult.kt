package com.example.jsoupxmlparser

import android.util.Log
import com.example.jsoupxmlparser.model.Currency
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements


class XmlResult {

    fun xmlGetCurrency(): List<Currency> {
        val arr = mutableListOf<Currency>()
        val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
        val doc: Document = Jsoup.connect(url).timeout(10000).ignoreContentType(true).get()
        val elements: Elements = doc.getElementsByTag("Currency")
        for (item in elements) {
            val currencyName = item.getElementsByTag("CurrencyName").text()
            val forexBuying = item.getElementsByTag("ForexBuying").text()
            val forexSelling = item.getElementsByTag("ForexSelling").text()
            val banknoteBuying = item.getElementsByTag("BanknoteBuying").text()
            val banknoteSelling = item.getElementsByTag("BanknoteSelling").text()
            val currencyObject =
                Currency(currencyName, forexBuying, forexSelling, banknoteBuying, banknoteSelling)
            arr.add(currencyObject)
        }
        return arr
    }

    fun xmlGetCurrencyDate() {
        val date: String
        val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
        val doc: Document = Jsoup.connect(url).timeout(10000).ignoreContentType(true).get()
        val elements: Elements = doc.getElementsByTag("Tarih_Date")
        for (item in elements) {
            val date = item.getElementsByAttribute("Date")
            Log.d("item", date.text())
        }
    }
}