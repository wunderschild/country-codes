# frozen_string_literal: true

require 'country_codes/internal/helpers'

module CountryCodes
  # @!attribute [r] alpha2
  #   @return [String] Returns ISO-3166-1 alpha-2 country code
  # @!attribute [r] alpha3
  #   @return [String] Returns ISO-3166-1 alpha-3 country code
  # @!attribute [r] nationality
  #   @return [String] Returns a human-readable nationality name for this country
  # @!attribute [r] sovereignty
  #   @return [String, nil] Return country code for a country that this country is affiliated to (if any).
  # @!attribute [r] official_name
  #   @return [String] Returns an official name of this country
  # @!attribute [r] other_names
  #   @return [Array<String>] Returns an array of alternative names for this country
  class Country
    include Internal::Helpers

    # Wraps an instance of `io.wunderschild.country_codes.Country` to be used in Ruby.
    #
    # @note This code is not supposed to be used externally!
    # @param [JavaLangObject] java_country An instance of `io.wunderschild.country_codes.Country`
    def initialize(java_country)
      @country = java_country
    end

    delegate :alpha2, :alpha3, :official_name, to: :country
    delegate :nationality, :sovereignty, to: :country, interceptors: :unpack_some
    delegate :other_names, to: :country, interceptors: %i[unpack_some unpack_seq array_no_nil]

    private

    attr_reader :country

    def array_no_nil(array)
      array.nil? ? [] : array
    end
  end
end
