package com.aotuman.nbahubu.data.mapper.news

import com.aotuman.nbahubu.data.entity.news.NewsEntity
import com.aotuman.nbahubu.data.entity.player.PlayerEntity
import com.aotuman.nbahubu.data.mapper.Mapper
import com.aotuman.nbahubu.model.news.NewsItemModel
import com.aotuman.nbahubu.model.player.PlayerItemModel

/**
 * <pre>
 *     author: aotuman
 *     date  : 2021/05/11
 *     desc  :
 * </pre>
 */
class Entity2ItemModelMapper : Mapper<NewsEntity, NewsItemModel> {

    override fun map(input: NewsEntity): NewsItemModel =
        NewsItemModel(
            newsId = input.newsId,
            title = input.title,
            url = input.url,
            imgurl = input.imgurl,
            pub_time = input.pub_time,
            upNum = input.upNum,
            commentNum = input.commentNum,
            shareUrl = input.shareUrl,
            content = input.content
        )
}