import io.wunderschild.country_codes.ISOCountryCodes
import org.scalatest.funsuite.AnyFunSuite

class ISOCountryCodesTest extends AnyFunSuite {
  test("ISOCountryCodes") {
    val iso = ISOCountryCodes("ru", Seq("officialName", "otherNames", "nationality"))
    require(iso.lookup("Russian").isDefined)
  }

  test("Diacritics ignored") {
    val iso = ISOCountryCodes("fr", Seq("officialName", "otherNames", "nationality"))
    require(iso.lookup("Etats-Unis d'Áḿéŕíq́úé́").isDefined)
  }
}
