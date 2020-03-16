package io.wunderschild.country_codes

import scala.io.Source
import java.nio.file.Paths

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import CountryHelpers._

object ISOCountryCodes {
  def using[A, B <: {def close(): Unit}] (closeable: B) (f: B => A): A =
    try { f(closeable) } finally { closeable.close() }

  /** Create lookup table that can be used for fast search of country by name.
   *
   * @param localization language to be used for the country names
   */
  def apply(localization: String = "en", indexedFields: Seq[String] = Seq("officialName", "otherNames")): LookupTable = {
    type mT = Map[String, Map[String, Any]]

    val mapper = new ObjectMapper(new YAMLFactory())
    mapper.registerModule(DefaultScalaModule)

    val countriesPath = Paths.get("/countries/").toString

    val countryDataPaths = using(getClass.getResourceAsStream("/countries/hint")) { stream =>
      Source.fromInputStream(stream).getLines.map(country => Paths.get(countriesPath, country + ".yaml").toString).toList
    }

    def readMap(path: String): mT = using(getClass.getResourceAsStream(path)) { stream =>
      mapper.readValue(stream, classOf[mT])
    }

    val localizationPath = Paths.get(s"/localization/${localization.toLowerCase}.yaml").toString
    val localizationMap = readMap(localizationPath)
    val countryDataMap = countryDataPaths.map{ countryDataPath =>
      readMap(countryDataPath).mapValues(data => {
       (data ++ localizationMap(data("alpha2").asInstanceOf[String])).toCaseClass[Country]
      })
    }

    new LookupTable(countryDataMap.flatMap(_.values).toSeq, indexedFields)
  }
}

object ISOCountryCodesTest {
  def main(args: Array[String]): Unit = {
    val iso = ISOCountryCodes("ru", Seq("officialName", "otherNames", "nationality"))
    require(iso.lookup("Russian").isDefined)
  }
}
