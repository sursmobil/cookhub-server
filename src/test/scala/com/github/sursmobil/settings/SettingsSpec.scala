package com.github.sursmobil.settings

import com.github.sursmobil.BaseSpec
import com.typesafe.config.ConfigFactory
import com.unstablebuild.settler.error.SettlerException

class SettingsSpec extends BaseSpec {

  describe("Settings") {
    it("should fail if PORT env variable is missing") {
      val config = ConfigFactory.parseString(
        """
          cookhub {
            serverPort = ${?PORT}
          }
        """)
      assertThrows[SettlerException] {
        Settings(config)
      }
    }

    it("should pass when port is set") {
      val config = ConfigFactory.parseString(
        """
          cookhub {
            serverPort = 6666
          }
        """)
      val settings = Settings(config)
      settings.serverPort shouldEqual 6666
    }
  }

}
