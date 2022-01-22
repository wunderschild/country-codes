# frozen_string_literal: true

require_relative 'lib/country_codes/version'

Gem::Specification.new do |spec|
  spec.name = 'country-codes-rb'
  spec.version = CountryCodes::VERSION
  spec.authors = ['Wunderschild', 'Karl F. Meinkopf']
  spec.email = ['k.meinkopf@gmail.com']

  spec.platform = 'java'

  spec.summary = 'A JRuby wrapper for country-codes Scala library'
  spec.description = ''
  spec.homepage = 'https://github.com/wunderschild/country-codes'
  spec.license = 'MIT'
  spec.required_ruby_version = '>= 2.5.0'

  spec.metadata['homepage_uri'] = spec.homepage
  spec.metadata['source_code_uri'] = spec.homepage
  spec.metadata['github_repo'] = 'https://github.com/thedeadferryman/country-codes'

  spec.files = Dir.chdir(File.expand_path(__dir__)) do
    source_files = Dir['lib/**/*'] + Dir['sig/**/*']
    misc_files = %w[Rakefile Gemfile LICENSE.txt README.md]

    source_files + misc_files
  end

  spec.bindir = 'exe'
  spec.executables = spec.files.grep(%r{\Aexe/}) { |f| File.basename(f) }
  spec.require_paths = %w[lib]

  spec.requirements << "jar io.wunderschild:country-codes_2.12, #{CountryCodes::LIB_VERSION}"

  spec.metadata['rubygems_mfa_required'] = 'true'
end
