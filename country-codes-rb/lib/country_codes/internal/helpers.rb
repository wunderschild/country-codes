# frozen_string_literal: true

require 'java'
require 'country-codes-rb_jars'

module CountryCodes
  # @note This code is not supposed to be used externally!
  module Internal
    # @note This code is not supposed to be used externally!
    module Helpers
      java_import 'scala.collection.JavaConverters'
      java_import 'java.util.ArrayList'

      # rubocop:disable Metrics/CyclomaticComplexity
      def self.included(obj)
        obj.class_eval do
          # Defines a method(s) that redirect calls to an underlying object (receiver).
          # Allows passing a list of interceptors that will be applied sequentially
          # with the result of redirected call as the first (and only) argument.
          # An interceptor can be either a Proc (the proc itself will be `#call`-ed),
          # or a Symbol as a name of an instance method to be used.
          #
          # @param [Array<Symbol>] methods Name of methods to be defined
          # @param [Object] to Name of an instance method used to retrieve the receiver
          # @param [Array<Proc, Symbol>] interceptors A list of interceptors.
          def self.delegate(*methods, to:, interceptors: [])
            unless methods.reject { |m| m.is_a? Symbol }.empty?
              raise ArgumentError, 'expected method reference to be a Symbol!'
            end

            interceptors = [interceptors] unless interceptors.is_a?(Array)

            methods.each do |method|
              define_method(method) do |*args|
                result = send(to).send(method, *args)

                interceptors.reduce(result) do |memo, interceptor|
                  case interceptor
                  when Proc
                    interceptor.call(memo)
                  when Symbol
                    send(interceptor, memo)
                  else
                    memo
                  end
                end
              end
            end
          end
        end
      end
      # rubocop:enable Metrics/CyclomaticComplexity

      # Unpacks a scala.Some Java object to a value or `nil` (if an object is empty).
      #
      # @param [JavaLangObject] some A `scala.Some` object to unpack
      # @return [Object] A value contained in the object, or nil, if the object is empty
      def unpack_some(some)
        if some.empty?
          nil
        else
          some.get
        end
      end

      # Converts a `scala.collection.Seq` Java object to a Ruby array
      #
      # @param [JavaLangObject] seq An object to convert
      # @return [JavaLangObject] Extracted array
      def unpack_seq(seq)
        JavaConverters.seq_as_java_list(seq).to_a
      end

      # Converts a given enumerable to a `scala.collection.Seq` Java object
      #
      # @param [Enumerable] enumerable An enumerable to wrap
      # @return [JavaLangObject] A wrapped Seq object
      def pack_seq(enumerable)
        list_iter = ArrayList.new(enumerable.to_a).iterator

        JavaConverters.as_scala_iterator_converter(list_iter).as_scala.to_seq
      end
    end
  end
end
