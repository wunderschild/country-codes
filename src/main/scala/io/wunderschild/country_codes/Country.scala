package io.wunderschild.country_codes

import scala.reflect._
import scala.reflect.runtime.universe._

case class Country(
    alpha2: String,
    alpha3: String,
    nationality: String,
    sovereignty: Option[String] = None,
    officialName: String,
    otherNames: Seq[String])