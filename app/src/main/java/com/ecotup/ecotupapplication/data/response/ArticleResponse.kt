package com.ecotup.ecotupapplication.data.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("data")
	val data: List<DataItemArticle>,

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class DataItemArticle(

	@field:SerializedName("article_id")
	val articleId: Int,

	@field:SerializedName("article_image")
	val articleImage: String? = null,

	@field:SerializedName("article_author")
	val articleAuthor: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("article_name")
	val articleName: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("article_link")
	val articleLink: String? = null,

	@field:SerializedName("article_date")
	val articleDate: String? = null
)
