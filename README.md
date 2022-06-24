# Country Codes

Convert country name on different languages to its corresponding ISO code.

## How to use

First add the following import statement to access the library:

```
import io.wunderschild.country_codes._
```

To retrieve information on a particular country, use object `ICOCountryCodes`.
It returns a lookup table that can be used to resolve a country by name or any other fields.
It takes two arguments, the first one specifies the language of the country names,
the second one specifies on which fields a lookup table should be built.

By default, the table is built on fields with official and others country names.
On lookup returns an `Option[Country]` object.
It contains general information on the corresponding country,
including alpha2 and alpha3 ISO-3166 Country Codes.

```
scala> val lt = ISOCountryCodes(localization = "ru")
scala> lt.lookup("Российская Федерация")
res1: Option[Country] = Some(RU)
```
