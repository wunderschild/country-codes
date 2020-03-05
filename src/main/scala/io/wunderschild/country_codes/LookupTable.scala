package io.wunderschild.country_codes

/*
 *
 */
class LookupTable(countries: Seq[Country]) {
  private lazy val holder: Map[Int, Country] = {
    countries.foldLeft(Map.empty[Int, Country]) { (lookup, country) =>
      val hashes = (country.officialName +: country.otherNames).map(name => name.toLowerCase.hashCode)
      lookup ++ hashes.map(hash => (hash, country)).toMap
    }
  }

  /*
   *
   */
  def lookup(countryName: String): Option[Country] = {
    val hash = countryName.toLowerCase.hashCode
    holder.get(hash)
  }
}
