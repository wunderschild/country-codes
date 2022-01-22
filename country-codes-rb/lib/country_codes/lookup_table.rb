# frozen_string_literal: true

require 'java'
require 'country-codes-rb_jars'

require 'country_codes/internal/helpers'
require 'country_codes/country'

module CountryCodes
  class LookupTable
    include Internal::Helpers

    java_import 'io.wunderschild.country_codes.ISOCountryCodes'

    # @note This code is not supposed to be used externally!
    def initialize(locale, indexed_fields)
      @lookup_table = ISOCountryCodes.apply(locale, pack_seq(indexed_fields))
    end

    # Searches for a country that matches a given term.
    # If nothing was found, returns `nil`.
    #
    # @param [String] term Search term
    # @return [CountryCodes::Country, nil] Search result
    def find(term)
      packed = @lookup_table.lookup(term)

      java_country = unpack_some(packed)

      return nil if java_country.nil?

      Country.new(java_country)
    end
  end
end
