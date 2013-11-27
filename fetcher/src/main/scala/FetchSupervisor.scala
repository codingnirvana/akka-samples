import edu.uci.ics.crawler4j.crawler.{Page, WebCrawler, CrawlController, CrawlConfig}
import edu.uci.ics.crawler4j.fetcher.PageFetcher
import edu.uci.ics.crawler4j.robotstxt.{RobotstxtConfig, RobotstxtServer}
import edu.uci.ics.crawler4j.url.WebURL

object FetchSupervisor {

  def main(args:Array[String]) {

      val crawlConfig = new CrawlConfig()
      crawlConfig.setCrawlStorageFolder("/tmp/crawl")
      crawlConfig.setMaxDepthOfCrawling(1)
      val fetcher: PageFetcher = new PageFetcher(crawlConfig)
      val controller = new CrawlController(crawlConfig,
        fetcher,
        new RobotstxtServer(new RobotstxtConfig(), new PageFetcher(crawlConfig)))

      controller.addSeed("http://www.indix.com")
      controller.start(new MyCrawler().getClass, 1)
  }
}

class MyCrawler extends WebCrawler {
  override def handlePageStatusCode(webUrl: WebURL, statusCode: Int, statusDescription: String) {
    System.out.println("%s - %s".format(statusCode, webUrl.getURL))
  }
}

