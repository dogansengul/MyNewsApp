package com.example.foxandroidrvexample.DataPackage

object NewsList {

    fun getNewsList(): ArrayList<News> {
        val newsList: ArrayList<News> = ArrayList()

        val n1 = News(1,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/C514/production/_130825405_1d2c6397-27a9-425e-a6e1-140a90aaa5c0.jpg.webp",
            "Looting, barricades, bodies - volunteers rush to help Lahaina after fire",
            "https://www.bbc.com/news/world-us-canada-66529890")
        newsList.add(n1)

        val n2 = News(2,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/18440/production/_130829399_malaysiamain.png.webp",
            "Private jet crashes onto Malaysia highway, killing 10 - police",
            "https://www.bbc.com/news/world-asia-66533412")
        newsList.add(n2)

        val n3 = News(3,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/10344/production/_130827366_gettyimages-1240913527.jpg.webp",
            "Saeed Roustaee: Martin Scorsese backs director jailed in Iran for Cannes screening",
            "https://www.bbc.com/news/entertainment-arts-66531884")
        newsList.add(n3)

        val n4 = News(4,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/5AAB/production/_130811232_gettyimages-1186530827-170667a.jpg.webp",
            "The baby at the centre of an India-Germany diplomatic row",
            "https://www.bbc.com/news/world-asia-india-66477396")
        newsList.add(n4)

        val n5 = News(5,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/1E13/production/_130699670_1-whatsappimage2023-07-13at17.31.26-5.jpg.webp",
            "Ukrainian prisoners of war say they were tortured at Russian prison",
            "https://www.bbc.com/news/world-europe-66453692")
        newsList.add(n5)

        val n6 = News(6,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/C514/production/_130825405_1d2c6397-27a9-425e-a6e1-140a90aaa5c0.jpg.webp",
            "Looting, barricades, bodies - volunteers rush to help Lahaina after fire",
            "https://www.bbc.com/news/world-us-canada-66529890")
        newsList.add(n6)

        val n7 = News(7,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/18080/production/_130823489_9d3b528e02bcf1447285f527a9dc92e40205c808.jpg.webp",
            "Pakistan: More than 100 arrested after churches burned",
            "https://www.bbc.com/news/world-asia-66517901")
        newsList.add(n7)

        val n8 = News(8,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/C514/production/_130825405_1d2c6397-27a9-425e-a6e1-140a90aaa5c0.jpg.webp",
            "Police sent to some ATMs after Bank of Ireland glitch",
            "https://www.bbc.com/news/articles/crgk4ky17lwo")
        newsList.add(n8)

        val n9 = News(9,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/1EBE/production/_130807870_gettyimages-1236197802.jpg.webp",
            "Firm regrets taking Facebook moderation work",
            "https://www.bbc.com/news/technology-66465231")
        newsList.add(n9)

        val n10 = News(10,
            "https://ichef.bbci.co.uk/news/976/cpsprodpb/BDD8/production/_130800684_capture.jpg.webp",
            "China suspends youth unemployment data after record high",
            "https://www.bbc.com/news/business-66506132")
        newsList.add(n10)
        return newsList
    }
}