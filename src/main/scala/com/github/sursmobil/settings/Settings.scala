package com.github.sursmobil.settings

import com.typesafe.config.Config
import com.unstablebuild.settler.Settler

trait Settings {
  val serverPort: Int
}

object Settings {
  def apply(config: Config): Settings =
    Settler.settings[Settings](config.getConfig("cookhub"))
}
