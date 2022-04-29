package com.sumologic.client.searchjob

import com.sumologic.client.{Credentials, SumoLogicClient}
import org.scalatest.Tag
import org.scalatest.Ignore
import org.scalatest.concurrent.Eventually
import org.scalatest.matchers.should.Matchers
import org.scalatest.time.{Seconds, Span}
import org.scalatest.wordspec.AnyWordSpecLike

import scala.jdk.CollectionConverters.*

class SearchJobClientTest extends AnyWordSpecLike with Matchers with Eventually {

  implicit override val patienceConfig =
    PatienceConfig(timeout = scaled(Span(120, Seconds)), interval = scaled(Span(20, Seconds)))

  "SearchJobClient" should {
    "succeed in searching" taggedAs WhenCredentialsAreProvided in {
      // NOTE: this test is not self-contained, it assumes particular log data being available in the Sumo org

      // given
      val sut = SearchJobClientTest.createClient
      val queryText = "_sourceName=dummy"
      val timeRange = Seq("2022-04-29T15:59:00", "2022-04-29T16:00:00")

      // when
      val searchJobId = sut.createSearchJob(
        queryText,
        timeRange(0), timeRange(1),
        "Europe/Warsaw"
      )

      // test
      eventually {
        sut.getSearchJobStatus(searchJobId).getState shouldBe("DONE GATHERING RESULTS")
      }
      val lines = sut.getMessagesForSearchJob(searchJobId, 0, 10).getMessages.asScala
      lines.size shouldBe(3)
      lines.map(_.getLogLine) should contain theSameElementsAs List("a message 123", "a message 123", "a message 123")
    }
  }
}

object SearchJobClientTest {
  def createClient: SumoLogicClient = {
    val accessId: String = System.getenv("SUMO_ACCESS_ID")
    val accessKey: String = System.getenv("SUMO_ACCESS_KEY")
    val credential: Credentials = new Credentials(accessId, accessKey)
    val sumoClient: SumoLogicClient = new SumoLogicClient(credential)
    sumoClient.setURL(System.getenv("SUMO_URL"))
    sumoClient
  }
}

object WhenCredentialsAreProvided
  extends Tag(if (System.getenv("SUMO_ACCESS_ID") != null) "" else classOf[Ignore].getName)
