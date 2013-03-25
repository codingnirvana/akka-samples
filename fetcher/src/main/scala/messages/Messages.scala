package messages

case class CrawlerConfig(siteName: String,
                         politenessDelay: Int)

case class WebUrl(Url: String)

case class UrlBatch(urls: List[WebUrl], siteConfig: CrawlerConfig)



