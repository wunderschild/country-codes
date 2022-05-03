# frozen_string_literal: true

require 'country_codes/country'
require 'country_codes/lookup_table'
require 'country_codes/version'

module CountryCodes
  # A list of allowed search fields (see {.instance}).
  SEARCHABLE_FIELDS = %i[officialName otherNames nationality sovereignty alpha2 alpha3].freeze

  # Creates an instance of {CountryCodes::LookupTable} to be used for querying.
  #
  # @param [String] locale A locale of expected search terms.
  # @param [Array<Symbol>] search_fields Fields available for searching (see {SEARCHABLE_FIELDS}).
  # @return [LookupTable]
  def self.instance(locale: 'en', search_fields: %i[officialName otherNames nationality])
    unless (search_fields - SEARCHABLE_FIELDS).empty?
      raise ArgumentError, "Unknown search fields: #{search_fields - SEARCHABLE_FIELDS}"
    end

    CountryCodes::LookupTable.new(locale, search_fields.map(&:to_s))
  end

  # Searches for a country that matches a given term.
  # If nothing was found, returns `nil`.
  # Check {.instance} for the description of named parameters.
  #
  # @param [String] term Search term
  # @param [String] locale
  # @param [Array<Symbol>] search_fields
  # @return [Country, nil] Search result
  def self.find(term, locale: 'en', search_fields: %i[officialName otherNames nationality])
    instance(locale: locale, search_fields: search_fields).find(term)
  end
end
