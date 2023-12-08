package com.ecotup.ecotupapplication.util

data class ArticleStatic(
    val article_id : String,
    val article_name : String,
    val article_author: String,
    val article_link : String,
    val article_image : String,
    val article_date : String
)

val dummyArticleNoSuffle = listOf(
    ArticleStatic("ATC-001", "Solusi Asyik, Kurangi Sampah Plastik", "Admin Dinas", "https://dlh.semarangkota.go.id/solusi-asyik-kurangi-sampah-plastik/", "https://dlh.semarangkota.go.id/wp-content/uploads/2023/01/20230112_131639-300x225.jpg","2023-01-12"),
    ArticleStatic("ATC-002", "5 Manfaat Pengolahan Sampah yang Baik", "Webmaster", "https://dlh.semarangkota.go.id/5-manfaat-pengolahan-sampah-yang-baik/", "https://dlh.semarangkota.go.id/wp-content/uploads/2021/02/wrodpress-com-2.jpg", "2020-11-19"),
    ArticleStatic("ATC-003", "Cara Sederhana Pilah Sampah dari Rumah", "Mita Defitri", "https://waste4change.com/blog/cara-sederhana-pilah-sampah-dari-rumah/", "https://waste4change.com/blog/wp-content/uploads/DSC03366.jpg","2020-11-19")
)


val dummyArticle = dummyArticleNoSuffle.shuffled()
