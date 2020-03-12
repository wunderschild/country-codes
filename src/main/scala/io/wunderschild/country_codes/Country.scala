package io.wunderschild.country_codes

case class Country(
    alpha2: String,
    alpha3: String,
    nationality: String,
    sovereignty: Option[String] = None,
    officialName: String,
    otherNames: Option[Seq[String]] = None)