package io.wunderschild.country_codes

/**
 * Allows to make a lookup for a country by indexed fields.
 *
 * @param countries list of countries loaded from the disk
 */
class LookupTable(countries: Seq[Country], indexedFields: Seq[String]) extends Serializable {

  private def toOption(anyVal: Any): Option[Any] = {
    anyVal match {
      case _ @ Some(null)   => None
      case v @ Some(_: Any) => v
      case _                => Option(anyVal)
    }
  }

  private lazy val holder: Map[Int, Country] = {
    val fieldsLens = classOf[Country]
      .getDeclaredFields
      .filter(f => indexedFields.contains(f.getName))

    countries.foldLeft(Map.empty[Int, Country]) { (lookup, country) =>
      val values = fieldsLens.foldLeft(Seq.empty[Any]) { (values, field) =>
        field.setAccessible(true)
        toOption(field.get(country)) match {
          case Some(v: Seq[Any]) => v ++ values
          case Some(v: Any)      => v +: values
          case None              => values
        }
      }
      val hashes = values.map(name => name.toString.toLowerCase.hashCode)
      lookup ++ hashes.map(hash => (hash, country)).toMap
    }
  }

  /**
   * Do actual lookup with given search string.
   *
   * @param query the search string
   */
  def lookup(query: Any): Option[Country] = {
    val queryExp = Option(query).getOrElse(return None)
    val hash = queryExp.toString.toLowerCase.hashCode
    holder.get(hash)
  }

}
