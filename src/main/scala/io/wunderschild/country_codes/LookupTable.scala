package io.wunderschild.country_codes

/** Allows to make a lookup for a country by its name.
 *
 * @param countries list of countries loaded from the disk
 */
class LookupTable(countries: Seq[Country]) {
  private lazy val holder: Map[Int, Country] = {
    countries.foldLeft(Map.empty[Int, Country]) { (lookup, country) =>
      val hashes = (country.officialName +: country.otherNames).map(name => name.toLowerCase.hashCode)
      lookup ++ hashes.map(hash => (hash, country)).toMap
    }
  }

  /** Do actual lookup with given search string.
   *
   * @param countryName the search string
   */
  def lookup(countryName: String): Option[Country] = {
    val hash = countryName.toLowerCase.hashCode
    holder.get(hash)
  }
}
