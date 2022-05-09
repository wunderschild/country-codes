import io.wunderschild.country_codes.ISOCountryCodes
import org.scalatest.funsuite.AnyFunSuite

class ISOCountryCodesTest extends AnyFunSuite {
  test("ISOCountryCodes") {
    val iso = ISOCountryCodes("ru", Seq("officialName", "otherNames", "nationality"))
    require(iso.lookup("Russian").isDefined)
  }
}
